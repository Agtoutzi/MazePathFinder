package definition;

import exception.InvalidInputException;

/**
 * Class representing a Maze definition.
 */
public class Maze {
    private EPointState[][] matrix;

    private Point startPoint;
    private Point goalPoint;

    public Maze() {

    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getGoalPoint() {
        return goalPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setGoalPoint(Point goalPoint) {
        this.goalPoint = goalPoint;
    }

    public EPointState getPointState(int pointX, int pointY) {
        return matrix[pointX][pointY];
    }

    public int getRowLength() {
        return matrix.length;
    }

    public int getColumnLength() {
        return matrix[0].length;
    }

    public void setMatrix(EPointState[][] matrix) throws InvalidInputException {
        this.matrix = matrix;

        if (startPoint == null) {
            throw new InvalidInputException("Maze Start-Point is not defined");
        } else if (goalPoint == null) {
            throw new InvalidInputException("Maze Goal-Point is not defined");
        }

        if (matrix[startPoint.getX()][startPoint.getY()] != EPointState.START) {
            throw new IllegalArgumentException("Matrix Start-Point and Maze Start-Point do not match");
        } else if (matrix[goalPoint.getX()][goalPoint.getY()] != EPointState.GOAL) {
            throw new IllegalArgumentException("Matrix Goal-Point and Maze Goal-Point do not match");
        }
    }
}
