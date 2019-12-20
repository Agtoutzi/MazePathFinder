package algorithm;

import definition.EPointState;
import definition.Maze;
import definition.Path;
import definition.Point;
import exception.InvalidInputException;

public class PathFinder implements IPathFinder {
    private Maze maze;

    public PathFinder(Maze maze) {
        this.maze = maze;
    }

    @Override
    public Path findPath() throws InvalidInputException {
        int[][] distanceMatrix = buildDistanceMatrix();
        if (distanceMatrix[maze.getStartPoint().getX()][maze.getStartPoint().getY()] == -1) {
            throw new InvalidInputException("Goal Point not reachable from Start Point");
        }
        return calculatePath(distanceMatrix);
    }

    private int[][] buildDistanceMatrix() {
        int[][] distanceMatrix = initDistanceMatrix();

        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix[0].length; j++) {
                    int newDistance = calculatePointDistance(distanceMatrix, i, j);
                    if (distanceMatrix[i][j] != newDistance) {
                        changed = true;
                        distanceMatrix[i][j] = newDistance;
                    }
                }
            }
        } while (changed);
        return distanceMatrix;
    }

    private int calculatePointDistance(int[][] distanceMatrix, int rowIndex, int columnIndex) {
        // If the point contains a wall return the same distance
        if (maze.getPointState(rowIndex, columnIndex) == EPointState.WALL) {
            return distanceMatrix[rowIndex][columnIndex];
        }
        // Get the new minimum path distance for the point
        int minPath = getMinPathLength(distanceMatrix, rowIndex, columnIndex);

        // Check if new minimum path is valid and lesser than the existing distance value of the point
        if (minPath >= 0 &&
                (distanceMatrix[rowIndex][columnIndex] < 0 || minPath + 1 < distanceMatrix[rowIndex][columnIndex])) {
            return minPath + 1;
        }
        return distanceMatrix[rowIndex][columnIndex];
    }

    private int getMinPathLength(int[][] distanceMatrix, int i, int j) {
        int north = getPositionNumber(distanceMatrix, i - 1, j);
        int south = getPositionNumber(distanceMatrix, i + 1, j);
        int east = getPositionNumber(distanceMatrix, i, j + 1);
        int west = getPositionNumber(distanceMatrix, i, j - 1);

        return getMinPositiveOfFour(north, south, east, west);
    }

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
