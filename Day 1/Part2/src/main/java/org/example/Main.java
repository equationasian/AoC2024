package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] nums = line.split("\s+");

                left.add(Integer.parseInt(nums[0]));
                right.add(Integer.parseInt(nums[1]));

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        int score = 0;
        for (Integer leftNum : left) {
            if (!count.containsKey(leftNum)) {
                count.put(leftNum, 0);

                for (Integer rightNum : right) {
                    if (leftNum.equals(rightNum)) {
                        count.put(leftNum, count.get(leftNum) + 1);
                    }
                }
            }

            score += leftNum * count.get(leftNum);
        }

        System.out.println(score);
    }
}