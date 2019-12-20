package algorithm;

import definition.EPointState;
import definition.Maze;
import definition.Path;
import definition.Point;
import exception.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathFinderTest {

    @Test
    public void findPath() {
        Maze maze = buildValidMaze();
        Path path = new Path();
        try {
            PathFinder pf = new PathFinder(maze);
            path = pf.findPath();
        } catch (InvalidInputException e) {
        }
        assertEquals("(3:1 (S)), (4:1), (5:1), (6:1), (6:2), (6:3), (6:4), (6:5), (6:6 (G))", path.toString());
    }

    private Maze buildValidMaze() {
        Maze maze = new Maze();
        maze.setGoalPoint(new Point(5, 5));
        maze.setStartPoint(new Point(2, 0));

        EPointState[][] pointStates = new EPointState[6][6];
        for (int i = 0; i < pointStates.length; i++) {
            for (int j = 0; j < pointStates[0].length; j++) {
                pointStates[i][j] = EPointState.PATH;
            }
        }
        pointStates[0][4] = EPointState.WALL;
        pointStates[1][3] = EPointState.WALL;
        pointStates[1][4] = EPointState.WALL;
        pointStates[1][5] = EPointState.WALL;
        pointStates[3][2] = EPointState.WALL;
        pointStates[3][3] = EPointState.WALL;
        pointStates[3][4] = EPointState.WALL;
        pointStates[3][5] = EPointState.WALL;
        pointStates[4][3] = EPointState.WALL;

        pointStates[2][0] = EPointState.START;
        pointStates[5][5] = EPointState.GOAL;

        maze.setMatrix(pointStates);
        return maze;
    }

    @Test(expected = InvalidInputException.class)
    public void findPath_Unreachable() throws InvalidInputException {
        Maze maze = buildInValidMaze();
        PathFinder pf = new PathFinder(maze);
        pf.findPath();
    }

    private Maze buildInValidMaze() {
        Maze maze = new Maze();
        maze.setGoalPoint(new Point(5, 5));
        maze.setStartPoint(new Point(2, 0));

        EPointState[][] pointStates = new EPointState[6][6];
        for (int i = 0; i < pointStates.length; i++) {
            for (int j = 0; j < pointStates[0].length; j++) {
                pointStates[i][j] = EPointState.PATH;
            }
        }
        pointStates[0][4] = EPointState.WALL;
        pointStates[1][3] = EPointState.WALL;
        pointStates[1][4] = EPointState.WALL;
        pointStates[1][5] = EPointState.WALL;
        pointStates[3][2] = EPointState.WALL;
        pointStates[3][3] = EPointState.WALL;
        pointStates[3][4] = EPointState.WALL;
        pointStates[3][5] = EPointState.WALL;
        pointStates[4][3] = EPointState.WALL;
        pointStates[5][3] = EPointState.WALL;

        pointStates[2][0] = EPointState.START;
        pointStates[5][5] = EPointState.GOAL;

        maze.setMatrix(pointStates);
        return maze;
    }
}