package fileread;

import exception.InvalidInputException;
import org.junit.Test;

import java.io.IOException;

public class InputReadTest {

    @Test
    public void readMazeFromFile() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze1.txt");
        InputRead.readMazeFromFile("src/test/resources/maze2.txt");
    }

    @Test(expected = IOException.class)
    public void readMazeFromFile_InvalidIO() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("");
        InputRead.readMazeFromFile("src/test/resources/maze1Line235.txt");
        InputRead.readMazeFromFile(null);
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze1Column.txt");
        InputRead.readMazeFromFile("src/test/resources/maze1Line.txt");
        InputRead.readMazeFromFile("src/test/resources/maze1point.txt");
        InputRead.readMazeFromFile("src/test/resources/maze2Goals.txt");
        InputRead.readMazeFromFile("src/test/resources/maze2Starts.txt");
        InputRead.readMazeFromFile("src/test/resources/mazeNoGoal.txt");
        InputRead.readMazeFromFile("src/test/resources/mazeNoStart.txt");
        InputRead.readMazeFromFile("src/test/resources/mazeRowsDiffLength.txt");
        InputRead.readMazeFromFile("src/test/resources/mazeInvalidChars.txt");
    }
}