package fileread;

import definition.EPointState;
import definition.Maze;
import definition.Point;
import exception.InvalidInputException;
import org.junit.Test;

import java.io.IOException;

public class InputValidationTest {

    @Test
    public void validateMaze() throws InvalidInputException, IOException {
        Maze maze = InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        InputValidation.validateMaze(maze);
    }

    @Test(expected = InvalidInputException.class)
    public void validateMaze_Invalid() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/mazeNoStart.txt");
        InputRead.readMazeFromFile("src/test/resources/mazeNoGoal.txt");

        Maze maze = InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        maze.setMatrix(null);
        InputValidation.validateMaze(maze);
        InputValidation.validateMaze(null);
    }

    @Test(expected = InvalidInputException.class)
    public void validateMaze_EqualStartGoal() throws InvalidInputException, IOException {
        Maze maze = InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        maze.setStartPoint(new Point(maze.getGoalPoint().getX(), maze.getGoalPoint().getY()));
        InputValidation.validateMaze(maze);
    }

    @Test
    public void validateFileSize() throws InvalidInputException {
        InputValidation.validateFileSize(14);
    }

    @Test(expected = InvalidInputException.class)
    public void validateFileSize_Invalid() throws InvalidInputException {
        InputValidation.validateFileSize(-4);
        InputValidation.validateFileSize(0);
        InputValidation.validateFileSize(1);
    }

    @Test
    public void validateLineLength() throws InvalidInputException {
        String line = "123456";
        InputValidation.validateLineLength(line, 6);
    }

    @Test(expected = InvalidInputException.class)
    public void validateLineLength_Invalid() throws InvalidInputException {
        String line = "";
        InputValidation.validateLineLength(line, 0);
        line = null;
        InputValidation.validateLineLength(line, 5);
        line = "_";
        InputValidation.validateLineLength(line, 1);
        line = "123456";
        InputValidation.validateLineLength(line, 10);
    }

    @Test
    public void validateChar() throws InvalidInputException, IOException {
        Maze maze = InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        InputValidation.validateChar(EPointState.WALL.getSymbol(), maze);
        InputValidation.validateChar(EPointState.PATH.getSymbol(), maze);
    }

    @Test(expected = InvalidInputException.class)
    public void validateChar_doubleChar() throws InvalidInputException, IOException {
        Maze maze = InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        InputValidation.validateChar(EPointState.START.getSymbol(), maze);
        InputValidation.validateChar(EPointState.GOAL.getSymbol(), maze);
    }

    @Test(expected = InvalidInputException.class)
    public void validateChar_InvalidChar() throws InvalidInputException, IOException {
        Maze maze = InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        InputValidation.validateChar('A', maze);
        InputValidation.validateChar('/', maze);
    }
}