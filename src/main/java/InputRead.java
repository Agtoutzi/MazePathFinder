import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputRead {

    public static Maze readMazeFromFile(String inputFilePath) throws InvalidInputException, IOException {
        List<EPointState[]> statesList = new ArrayList<>();
        Maze maze = new Maze();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line = reader.readLine();
            int firstLineLength = line.length();
            validateFileSize(firstLineLength);

            int lineIndex = 0;
            while (line != null) {
                validateLineLength(line, firstLineLength);
                statesList.add(createPointStateRow(lineIndex, line, maze));

                // read next line
                line = reader.readLine();
                lineIndex++;
            }
            validateFileSize(lineIndex - 1);
        }
        maze.setMatrix(convertListTo2DArray(statesList));
        return maze;
    }

    private static void validateFileSize(int length) throws InvalidInputException {
        if (length < 0) {
            throw new InvalidInputException("File size is more than lower-limit");
        }
        else if (length < 2) {
            throw new InvalidInputException("File size is less than lower-limit");
        }
    }

    private static void validateLineLength(String line, int firstLineLength) throws InvalidInputException {
        if (line.length() != firstLineLength) {
            throw new InvalidInputException("Rows do not have the same length");
        }
    }

    private static void validateChar(char mazeCharacter, Maze maze) throws InvalidInputException {
        EPointState pointState = EPointState.getPointState(mazeCharacter);
        if (pointState == null) {
            throw new InvalidInputException("Character '" + mazeCharacter + "' is not supported");
        } else if (pointState == EPointState.START && maze.getStartPoint() != null ||
                pointState == EPointState.GOAL && maze.getGoalPoint() != null) {
            throw new InvalidInputException("More than 1 " + pointState.toString() + " points found in Maze.");
        }
    }

    private static EPointState[] createPointStateRow(int rowIndex, String line, Maze maze) throws InvalidInputException {
        EPointState[] pointStates = new EPointState[line.length()];
        for (int i = 0; i < line.length(); i++) {
            char nextChar = line.charAt(i);
            validateChar(nextChar, maze);

            EPointState pointState = EPointState.getPointState(nextChar);
            //Store point-state in the array
            pointStates[i] = pointState;
            //Set start/goal point in maze
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
