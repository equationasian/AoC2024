package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    Pattern mulPattern = Pattern.compile("\\d+");
                    Matcher mulMatcher = mulPattern.matcher(matcher.group());

                    int product = 1;
                    while (mulMatcher.find()) {
                        product *= Integer.parseInt(mulMatcher.group());
                    }
                    sum += product;
                }

                line = reader.readLine();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(sum);
    }
}