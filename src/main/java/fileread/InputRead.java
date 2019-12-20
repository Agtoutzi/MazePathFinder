package fileread;

import definition.EPointState;
import definition.Maze;
import definition.Point;
import exception.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputRead {

    /**
     * Reads a text file with a maze definition and creates a {@link Maze} object.
     * Validates the maze read from the file.
     *
     * @param inputFilePath the path of the text file containing the maze definition
     * @return the created maze
     * @throws InvalidInputException when the definition of the maze is invalid. The maze must have a number of rows and
     *                               columns limited between 2 and {@link Integer#MAX_VALUE}, all rows(lines of text)
     *                               must have the same length, and the text must contain only '_', 'X' and exactly 1
     *                               'S' and 'G' character
     * @throws IOException           when a problem occurs when reading the file
     */
    public static Maze readMazeFromFile(String inputFilePath) throws InvalidInputException, IOException {
        List<EPointState[]> statesList = new ArrayList<>();
        Maze maze = new Maze();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            // Read first line of file and validate the size of it
            String line = reader.readLine();
            int firstLineLength = line.length();
            InputValidation.validateFileSize(firstLineLength);

            int lineIndex = 0;
            while (line != null) {
                // Validate the last read line
                InputValidation.validateLineLength(line, firstLineLength);

                // Add a new array of point-states from the last read line
                statesList.add(createPointStateRow(lineIndex, line, maze));

                // Read next line
                line = reader.readLine();
                lineIndex++;
            }

            // Validate number of lines read
            InputValidation.validateFileSize(lineIndex - 1);
        }
        // Set the matrix of the maze
        maze.setMatrix(convertListTo2DArray(statesList));

        // Validate maze
        InputValidation.validateMaze(maze);
        return maze;
    }

    private static EPointState[] createPointStateRow(int rowIndex, String line, Maze maze) throws InvalidInputException {
        EPointState[] pointStates = new EPointState[line.length()];
        for (int i = 0; i < line.length(); i++) {
            char nextChar = line.charAt(i);

            // Validate the last read char
            InputValidation.validateChar(nextChar, maze);

            EPointState pointState = EPointState.getPointState(nextChar);
            // Store point-state in the array
            pointStates[i] = pointState;
            // Set start/goal point in maze
            if (pointState == EPointState.START) {
                maze.setStartPoint(new Point(rowIndex, i));
            } else if (pointState == EPointState.GOAL) {
                maze.setGoalPoint(new Point(rowIndex, i));
            }
        }
        return pointStates;
    }

    private static EPointState[][] convertListTo2DArray(List<EPointState[]> statesList) {
        EPointState[][] states = new EPointState[statesList.size()][];
        states = statesList.toArray(states);
        return states;
    }
}
