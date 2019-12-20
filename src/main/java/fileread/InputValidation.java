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

    /**
     * Validates a given {@link Maze}.
     *
     * @param maze the {@link Maze} to be validated
     * @throws InvalidInputException if given maze is null, maze's START/GOAL point is null or equal to each-other, and
     *                               the size of the maze is within limits
     */
    public static void validateMaze(Maze maze) throws InvalidInputException {
        if (maze == null) {
            throw new InvalidInputException("Maze is null");
        }
        if (maze.getStartPoint() == null) {
            throw new InvalidInputException("Maze does not contain a Start point");
        }
        if (maze.getGoalPoint() == null) {
            throw new InvalidInputException("Maze does not contain a Goal point");
        }
        if (maze.getGoalPoint().equals(maze.getStartPoint())) {
            throw new InvalidInputException("Maze Start and Goal point are identical");
        }
        try {
            validateFileSize(maze.getRowLength());
            validateFileSize(maze.getColumnLength());
        } catch (NullPointerException e) {
            throw new InvalidInputException("Maze is invalid");
        }
    }

    /**
     * Validates the size of the read text file.
     *
     * @param length the length value to be validated
     * @throws InvalidInputException if the given length value is not within limits
     */
    static void validateFileSize(int length) throws InvalidInputException {
        if (length < 0) {
            throw new InvalidInputException("File size is more than upper-limit");
        } else if (length < 2) {
            throw new InvalidInputException("File size is less than lower-limit");
        }
    }

    /**
     * Validates a given line for its length.
     *
     * @param line        the line to be validated
     * @param validLength the length that is considered valid
     * @throws InvalidInputException if the given line is null or line length is not within limits or line length does not
     *                               match the given valid length
     */
    static void validateLineLength(String line, int validLength) throws InvalidInputException {
        if (line == null) {
            throw new InvalidInputException("Line is null");
        }
        validateFileSize(line.length());
        if (line.length() != validLength) {
            throw new InvalidInputException("Rows do not have the same length");
        }
    }

    /**
     * Validates a given character. Checks whether the character is supported and if the character is START/GOAL
     * character, checks the given {@link Maze} to see if the START/GOAL character is already defined.
     *
     * @param mazeCharacter the character to validate
     * @param maze          the maze to check for existing START/GOAL point
     * @throws InvalidInputException if the given character is invalid
     */
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
