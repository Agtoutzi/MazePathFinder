package com.angelos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputRead {

    public static Maze readMazeFromFile(String inputFilePath) {
        int columns;
        List<EPointState[]> statesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line = reader.readLine();
            columns = line.length();
            while (line != null) {
                EPointState[] pointStates = new EPointState[columns];
                System.out.println(line);
                for (int i = 0; i < columns; i++) {
                    pointStates[i] = EPointState.getPointState(line.charAt(i));
                }
                statesList.add(pointStates);

                // read next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        EPointState[][] states = new EPointState[statesList.size()][];
        states = statesList.toArray(states);
        Maze maze = null;
        try {
            maze = new Maze(states);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return maze;
    }
}
