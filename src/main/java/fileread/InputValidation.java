package fileread;

import definition.EPointState;
import definition.Maze;
import exception.InvalidInputException;

/**
 * Contains methods for validating a maze read from a text file.
 */
public class InputValidation {
    private InputValidation() {
    }

    static void validateMaze(Maze maze) throws InvalidInputException {
        if (maze.getStartPoint() == null) {
            throw new InvalidInputException("Maze does not contain a Start point");
        }
        if (maze.getGoalPoint() == null) {
            throw new InvalidInputException("Maze does not contain a Goal point");
        }
        if (maze.getGoalPoint().equals(maze.getStartPoint())) {
            throw new InvalidInputException("Maze Start and Goal point are identical");
        }
    }

    static void validateFileSize(int length) throws InvalidInputException {
        if (length < 0) {
            throw new InvalidInputException("File size is more than lower-limit");
        } else if (length < 2) {
            throw new InvalidInputException("File size is less than lower-limit");
        }
    }

    static void validateLineLength(String line, int firstLineLength) throws InvalidInputException {
        if (line.length() != firstLineLength) {
            throw new InvalidInputException("Rows do not have the same length");
        }
    }

    static void validateChar(char mazeCharacter, Maze maze) throws InvalidInputException {
        EPointState pointState = EPointState.getPointState(mazeCharacter);
        if (pointState == null) {
            throw new InvalidInputException("Character '" + mazeCharacter + "' is not supported");
        } else if (pointState == EPointState.START && maze.getStartPoint() != null ||
                pointState == EPointState.GOAL && maze.getGoalPoint() != null) {
            throw new InvalidInputException("More than 1 " + pointState.toString() + " points found in Maze.");
        }
    }
}
