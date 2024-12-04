package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

        Collections.sort(left);
        Collections.sort(right);

        int distance = 0;
        for (int i = 0; i < left.size(); ++i) {
            distance += Math.abs(left.get(i) - right.get(i));
        }

        System.out.println(distance);
    }
}