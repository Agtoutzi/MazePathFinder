package algorithm;

import definition.EPointState;
import definition.Maze;
import definition.Path;
import definition.Point;
import exception.InvalidInputException;
import fileread.InputValidation;

/**
 * Implementation of a maze path-finding algorithm. This implementation builds a 2D-array(matrix) of the
 * distances(in steps) from each point to the GOAL point of the given {@link Maze} and then it calculates the path from
 * the START point to the GOAL point of the maze.
 */
public class PathFinder implements IPathFinder {
    private Maze maze;

    /**
     * @param maze the {@link Maze} to find the path from START to GOAL point
     * @throws InvalidInputException if the given {@link Maze} is invalid
     */
    public PathFinder(Maze maze) throws InvalidInputException {
        InputValidation.validateMaze(maze);
        this.maze = maze;
    }

    /**
     * Calculates/Finds a {@link Path} from a maze.
     *
     * @return the calculated {@link Path}
     * @throws InvalidInputException if the input is invalid(or a valid path does not exist)
     */
    @Override
    public Path findPath() throws InvalidInputException {
        int[][] distanceMatrix = buildDistanceMatrix();
        if (distanceMatrix[maze.getStartPoint().getX()][maze.getStartPoint().getY()] == -1) {
            throw new InvalidInputException("Goal Point not reachable from Start Point");
        }
        return calculatePath(distanceMatrix);
    }

    // Creates and populates a distance-matrix(int[][]) for the maze. The matrix has the same size as the maze. Each
    // point in the matrix represents the point with the same coordinates in the maze matrix. After calculation and
    // population of the distance-matrix, each point(except wall points) contains the distance(in steps) from the point
    // to the GOAL point of the maze.
    private int[][] buildDistanceMatrix() {
        // initialize the distance-matrix with the default values for each type of point
        int[][] distanceMatrix = initDistanceMatrix();

        boolean changed;
        do {
            changed = false;

            // Iterate all distance-matrix
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix[0].length; j++) {

                    // Calculate the new distance to GOAL point
                    int newDistance = calculatePointDistance(distanceMatrix, i, j);

                    // If the new calculated distance is different than the old one, set it and mark that the
                    // distance-matrix has been changed
                    if (distanceMatrix[i][j] != newDistance) {
                        changed = true;
                        distanceMatrix[i][j] = newDistance;
                    }
                }
            }
        } while (changed);      // Repeat process until there are no changes in the distance-matrix
        return distanceMatrix;
    }

    // Calculates and returns the minimum distance of a point in the distance-matrix according to it's neighbouring points.
    private int calculatePointDistance(int[][] distanceMatrix, int rowIndex, int columnIndex) {
        // If the point contains a wall return the same distance
        if (maze.getPointState(rowIndex, columnIndex) == EPointState.WALL) {
            return distanceMatrix[rowIndex][columnIndex];
        }
        // Get the new minimum path distance for the point
        int minDistance = getMinNeighbourDistance(distanceMatrix, rowIndex, columnIndex);

        // Check if new minimum path is valid and lesser than the existing distance value of the point
        if (minDistance >= 0 &&
                (distanceMatrix[rowIndex][columnIndex] < 0 || minDistance + 1 < distanceMatrix[rowIndex][columnIndex])) {
            return minDistance + 1;
        }
        return distanceMatrix[rowIndex][columnIndex];
    }

    // Calculates and returns the minimum value of a distance-matrix point's neighbouring points.
    private int getMinNeighbourDistance(int[][] distanceMatrix, int i, int j) {
        int north = getPositionNumber(distanceMatrix, i - 1, j);
        int south = getPositionNumber(distanceMatrix, i + 1, j);
        int east = getPositionNumber(distanceMatrix, i, j + 1);
        int west = getPositionNumber(distanceMatrix, i, j - 1);

        return getMinPositiveOfFour(north, south, east, west);
    }

    // Returns the number of a position in the distance matrix, or -1 in cases of out-of bounds coordinates or WALL points.
    private int getPositionNumber(int[][] distanceMatrix, int rowIndex, int columnIndex) {
        int number;
        try {
            if (maze.getPointState(rowIndex, columnIndex) != EPointState.WALL) {
                number = distanceMatrix[rowIndex][columnIndex];
            } else {
                number = -1;
            }
        } catch (IndexOutOfBoundsException e) {
            number = -1;
        }
        return number;
    }

    // Returns the minimum positive integer between the given integers.
    private int getMinPositiveOfFour(int first, int second, int third, int fourth) {
        int min = -1;
        if (first >= 0) {
            min = first;
        }
        if (second >= 0 && (min < 0 || second < min)) {
            min = second;
        }
        if (third >= 0 && (min < 0 || third < min)) {
            min = third;
        }
        if (fourth >= 0 && (min < 0 || fourth < min)) {
            min = fourth;
        }
        return min;
    }

    // Builds a distance-matrix (int[][]) according to maze. The points in the matrix will be set according to the
    // value at their corresponding points' state in the maze matrix. For START, PATH points, value is set to -1. For
    // GOAL point value is set to 0. For Wall points value is set to -2.
    private int[][] initDistanceMatrix() {
        int[][] distanceMatrix = new int[maze.getRowLength()][maze.getColumnLength()];
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                switch (maze.getPointState(i, j)) {
                    case START:
                    case PATH:
                        distanceMatrix[i][j] = -1;
                        break;
                    case GOAL:
                        distanceMatrix[i][j] = 0;
                        break;
                    case WALL:
                        distanceMatrix[i][j] = -2;
                        break;
                    default:
                        break;
                }
            }
        }
        return distanceMatrix;
    }

    // Calculates the path from the given distance-matrix. The path starts from START point, and in each step it moves
    // to the adjoined point with the lower distance value(NORTH, SOUTH, EAST, WEST in that priority). Stops after
    // reaching GOAL point.
    private Path calculatePath(int[][] distanceMatrix) {
        Path shortestPath = new Path();
        shortestPath.addPoint(maze.getStartPoint());

        int i = maze.getStartPoint().getX();
        int j = maze.getStartPoint().getY();
        while (distanceMatrix[i][j] != 0) {
            int north = getPositionNumber(distanceMatrix, i - 1, j);
            int south = getPositionNumber(distanceMatrix, i + 1, j);
            int east = getPositionNumber(distanceMatrix, i, j + 1);
            int west = getPositionNumber(distanceMatrix, i, j - 1);

            int minDest = getMinPositiveOfFour(north, south, east, west);
            Point nextPoint;
            if (minDest == north) {
                nextPoint = new Point(i - 1, j);
            } else if (minDest == south) {
                nextPoint = new Point(i + 1, j);
            } else if (minDest == east) {
                nextPoint = new Point(i, j + 1);
            } else {
                nextPoint = new Point(i, j - 1);
            }
            shortestPath.addPoint(nextPoint);
            i = nextPoint.getX();
            j = nextPoint.getY();
        }
        return shortestPath;
    }
}
