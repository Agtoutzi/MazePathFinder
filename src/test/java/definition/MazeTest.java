package definition;

import exception.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MazeTest {

    private Maze maze;

    @Before
    public void Before() {
        maze = new Maze();
    }

    @Test
    public void setStartPoint() {
        maze.setStartPoint(new Point(1, 2));
        assertEquals(1, maze.getStartPoint().getX());
        assertEquals(2, maze.getStartPoint().getY());
        assertNotEquals(4, maze.getStartPoint().getX());
        assertNotEquals(0, maze.getStartPoint().getY());
    }

    @Test
    public void setGoalPoint() {
        maze.setGoalPoint(new Point(1, 2));
        assertEquals(1, maze.getGoalPoint().getX());
        assertEquals(2, maze.getGoalPoint().getY());
        assertNotEquals(0, maze.getGoalPoint().getX());
        assertNotEquals(4, maze.getGoalPoint().getY());
    }

    @Test
    public void getPointState() throws InvalidInputException {
        Maze maze = buildValidMaze();
        assertEquals(EPointState.PATH, maze.getPointState(0, 0));
        assertEquals(EPointState.WALL, maze.getPointState(1, 5));
        assertEquals(EPointState.START, maze.getPointState(2, 0));
        assertEquals(EPointState.GOAL, maze.getPointState(5, 5));
    }

    @Test
    public void getRowLength() throws InvalidInputException {
        Maze maze = buildValidMaze();
        assertEquals(6, maze.getRowLength());
    }

    @Test
    public void getColumnLength() throws InvalidInputException {
        Maze maze = buildValidMaze();
        assertEquals(6, maze.getColumnLength());
    }

    @Test
    public void setMatrix() throws InvalidInputException {
        EPointState[][] matrix = buildValidMatrix();
        maze = new Maze();
        maze.setStartPoint(new Point(2, 0));
        maze.setGoalPoint(new Point(5, 5));
        maze.setMatrix(matrix);
    }

    @Test(expected = InvalidInputException.class)
    public void setMatrix_Invalid1() throws InvalidInputException {
        EPointState[][] matrix = buildValidMatrix();
        matrix[2][0] = EPointState.WALL;
        maze = new Maze();
        maze.setMatrix(matrix);
    }

    @Test(expected = InvalidInputException.class)
    public void setMatrix_Invalid2() throws InvalidInputException {
        EPointState[][] matrix = buildValidMatrix();
        matrix[5][5] = EPointState.WALL;
        maze = new Maze();
        maze.setMatrix(matrix);
    }

    @Test(expected = InvalidInputException.class)
    public void setMatrix_Invalid3() throws InvalidInputException {
        EPointState[][] matrix = buildValidMatrix();
        maze = new Maze();
        maze.setMatrix(matrix);
    }

    @Test(expected = InvalidInputException.class)
    public void setMatrix_Invalid4() throws InvalidInputException {
        EPointState[][] matrix = buildValidMatrix();
        maze = new Maze();
        maze.setStartPoint(new Point(2, 0));
        maze.setMatrix(matrix);
    }

    private Maze buildValidMaze() throws InvalidInputException {
        Maze maze = new Maze();
        maze.setGoalPoint(new Point(5, 5));
        maze.setStartPoint(new Point(2, 0));
        maze.setMatrix(buildValidMatrix());
        return maze;
    }

    private EPointState[][] buildValidMatrix() {
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

        return pointStates;
    }
}