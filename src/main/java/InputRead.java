import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputRead {

    public static Maze readMazeFromFile(String inputFilePath) throws InvalidInputException, IOException {
        int firstLineLength;
        List<EPointState[]> statesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line = reader.readLine();
            firstLineLength = line.length();
            while (line != null) {
                if (line.length() != firstLineLength) {
                    throw new InvalidInputException("Rows do not have the same length");
                }
                statesList.add(createPointStateRow(line, firstLineLength));

                // read next line
                line = reader.readLine();
            }
        }
        return new Maze(convertListTo2DArray(statesList));
    }

    private static EPointState[] createPointStateRow(String line, int rowLength) {
        EPointState[] pointStates = new EPointState[rowLength];
        for (int i = 0; i < rowLength; i++) {
            pointStates[i] = EPointState.getPointState(line.charAt(i));
        }
        return pointStates;
    }

    private static EPointState[][] convertListTo2DArray(List<EPointState[]> statesList){
        EPointState[][] states = new EPointState[statesList.size()][];
        states = statesList.toArray(states);
        return states;
    }
}
