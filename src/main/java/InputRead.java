import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputRead {

    public static Maze readMazeFromFile(String inputFilePath) throws InvalidInputException, IOException {
        int columns;
        List<EPointState[]> statesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line = reader.readLine();
            columns = line.length();
            while (line != null) {
                if (line.length() != columns) {
                    throw new InvalidInputException("Rows do not have the same length");
                }
                EPointState[] pointStates = new EPointState[columns];
                for (int i = 0; i < columns; i++) {
                    pointStates[i] = EPointState.getPointState(line.charAt(i));
                }
                statesList.add(pointStates);

                // read next line
                line = reader.readLine();
            }
        }

        EPointState[][] states = new EPointState[statesList.size()][];
        states = statesList.toArray(states);
        return new Maze(states);
    }
}
