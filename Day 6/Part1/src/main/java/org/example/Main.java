package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<String>> board = fillBoard();
        Queue<String> directionsQueue = new ArrayDeque<>();
        directionsQueue.add("left");
        directionsQueue.add("up");
        directionsQueue.add("right");
        directionsQueue.add("down");

        List<Integer> startCoordinates = findStartCoordinate(board);
        board.get(startCoordinates.get(0)).set(startCoordinates.get(1), "X");

        int count = solve(board, directionsQueue, startCoordinates) + 1;

        System.out.println(count);
    }

    public static List<List<String>> fillBoard() {
        List<List<String>> board = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                List<String> row = new ArrayList<>(Arrays.asList(line.split("")));
                board.add(row);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    public static List<Integer> findStartCoordinate(List<List<String>> board) {
        for (int row = 0; row < board.size(); ++row) {
            for (int column = 0; column < board.get(row).size(); ++column) {
                if (board.get(row).get(column).equals("^")) {
                    return new ArrayList<>(Arrays.asList(row, column));
                }
            }
        }

        return new ArrayList<>();
    }

    public static int solve(List<List<String>> board, Queue<String> directions, List<Integer> coordinates) {
        int startingRow = coordinates.get(0);
        int startingColumn = coordinates.get(1);

        if (isFinished(board, startingRow, startingColumn, directions.element())) {
            return 0;
        }
        else {
            String previousDirection = directions.remove();
            directions.add(previousDirection);
        }

        int endingRow = startingRow;
        int endingColumn = startingColumn;
        int count = 0;

        if (directions.element().equals("up")) {
            for (int row = startingRow; row >= 0; --row) {
                if (board.get(row).get(startingColumn).equals(".")) {
                    board.get(row).set(startingColumn, "X");
                    ++count;
                }
                else if (board.get(row).get(startingColumn).equals("#")) {
                    break;
                }

                endingRow = row;
            }
        }
        else if (directions.element().equals("right")) {
            for (int column = startingColumn; column < board.get(startingRow).size(); ++column) {
                if (board.get(startingRow).get(column).equals(".")) {
                    board.get(startingRow).set(column, "X");
                    ++count;
                }
                else if (board.get(startingRow).get(column).equals("#")) {
                    break;
                }

                endingColumn = column;
            }
        }
        else if (directions.element().equals("down")) {
            for (int row = startingRow; row < board.size(); ++row) {
                if (board.get(row).get(startingColumn).equals(".")) {
                    board.get(row).set(startingColumn, "X");
                    ++count;
                }
                else if (board.get(row).get(startingColumn).equals("#")) {
                    break;
                }

                endingRow = row;
            }
        }
        else {
            for (int column = startingColumn; column >= 0; --column) {
                if (board.get(startingRow).get(column).equals(".")) {
                    board.get(startingRow).set(column, "X");
                    ++count;
                }
                else if (board.get(startingRow).get(column).equals("#")) {
                    break;
                }

                endingColumn = column;
            }
        }

        List<Integer> endingCoordinates = new ArrayList<>(Arrays.asList(endingRow, endingColumn));

        return count + solve(board, directions, endingCoordinates);
    }

    public static boolean isFinished(List<List<String>> board, int row, int column, String direction) {
        boolean up = row == 0 && direction.equals("up");
        boolean down = row == board.size() - 1 && direction.equals("down");
        boolean left = column == 0 && direction.equals("left");
        boolean right = column == board.get(row).size() && direction.equals("right");

        return up || down || left || right;
    }
}