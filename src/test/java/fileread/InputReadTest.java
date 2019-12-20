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
    public void readMazeFromFile_InvalidIO1() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("");
    }

    @Test(expected = IOException.class)
    public void readMazeFromFile_InvalidIO2() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze1Line235.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_InvalidIO3() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile(null);
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid1() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze1Column.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid2() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze1Line.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid3() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze1point.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid4() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze2Goals.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid5() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/maze2Starts.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid6() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/mazeNoGoal.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid7() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/mazeNoStart.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid8() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/mazeRowsDiffLength.txt");
    }

    @Test(expected = InvalidInputException.class)
    public void readMazeFromFile_Invalid9() throws InvalidInputException, IOException {
        InputRead.readMazeFromFile("src/test/resources/mazeInvalidChars.txt");
    }
}