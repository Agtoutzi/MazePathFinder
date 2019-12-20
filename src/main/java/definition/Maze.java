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

    /**
     * Finds a point in the point-state matrix and returns its {@link EPointState}.
     *
     * @param pointX the row-index of the point
     * @param pointY the column-index of the point
     * @return the {@link EPointState} value of the point with the given coordinates as found in matrix
     */
    public EPointState getPointState(int pointX, int pointY) {
        return matrix[pointX][pointY];
    }

    public int getRowLength() {
        return matrix.length;
    }

    public int getColumnLength() {
        return matrix[0].length;
    }

    /**
     * Sets the matrix of a {@link Maze} object.
     *
     * @param matrix the matrix to be set
     * @throws InvalidInputException if START and GOAL points of the maze are not defined, or if they do not match with
     *                               the given matrix's START and GOAL points
     */
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
