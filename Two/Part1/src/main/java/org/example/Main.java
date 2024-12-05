package org.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        int safeReports = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] levels = line.split("\s");

                if (isSafeReport(levels)) {
                    ++safeReports;
                }

                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(safeReports);
    }

    public static boolean isSafeDifference(int num1, int num2) {
        return Math.abs(num1 - num2) >= 1 && Math.abs(num1 - num2) <= 3;
    }

    public static boolean isSafeReport(String[] levels) {
        int first = Integer.parseInt(levels[0]);
        int second = Integer.parseInt(levels[1]);
        boolean isAllIncreasing = first - second < 0;

        for (int i = 1; i < levels.length; ++i) {
            int previous = Integer.parseInt(levels[i - 1]);
            int current = Integer.parseInt(levels[i]);
            boolean isCurrentIncreasing = previous - current < 0;

            if (!isSafeDifference(current, previous) || (isAllIncreasing && !isCurrentIncreasing) || (!isAllIncreasing && isCurrentIncreasing)) {
                return false;
            }
        }

        return true;
    }
}