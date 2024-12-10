package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        Map<Integer, List<Integer>> pageRules = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (!line.isEmpty()) {
                List<Integer> pages = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toList();
                if (!pageRules.containsKey(pages.get(0))) {
                    pageRules.put(pages.get(0), new ArrayList<>());
                }

                pageRules.get(pages.get(0)).add(pages.get(1));

                line = reader.readLine();
            }

            line = reader.readLine();
            while (line != null) {
                Integer[] update = Arrays.stream(line.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

                if (!previousPagesSafe(update, pageRules)) {
                    update = sortUpdate(update, pageRules);
                    sum += update.length % 2 == 0 ? update[update.length / 2 - 1] : update[update.length / 2];
                }

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(sum);
    }

    public static boolean previousPagesSafe(Integer[] update, Map<Integer, List<Integer>> pageRules) {
        for (int i = update.length - 1; i > 0; --i) {
            if (!pageRules.containsKey(update[i])) {
                continue;
            }

            for (int j = i - 1; j >= 0; --j) {
                if (pageRules.get(update[i]).contains(update[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Integer[] sortUpdate(Integer[] update, Map<Integer, List<Integer>> pageRules) {
        if (previousPagesSafe(update, pageRules)) {
            return update;
        }

        Map<Integer, Integer> swapIndex = new HashMap<>();
        for (int i = update.length - 1; i > 0; --i) {
            if (!pageRules.containsKey(update[i])) {
                continue;
            }

            int unsafeSwapIndex = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (pageRules.get(update[i]).contains(update[j])) {
                    unsafeSwapIndex = j;
                }
            }

            if (unsafeSwapIndex >= 0) {
                swapIndex.put(update[i], unsafeSwapIndex);
            }
        }

        Integer[] sorted = swap(update, swapIndex);

        return sortUpdate(sorted, pageRules);
    }

    public static Integer[] swap(Integer[] update, Map<Integer, Integer> swapIndex) {
        if (swapIndex.isEmpty()) {
            return update;
        }

        List<Integer> sorted = new ArrayList<>(Arrays.stream(update).toList());
        for (Map.Entry<Integer, Integer> entry : swapIndex.entrySet()) {
            sorted.remove(entry.getKey());
            sorted.add(entry.getValue(), entry.getKey());
        }

        return sorted.toArray(Integer[]::new);
    }
}