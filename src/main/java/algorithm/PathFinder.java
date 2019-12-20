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

        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix[0].length; j++) {
                    if (maze.getPointState(i, j) == EPointState.WALL) {
                        continue;
                    }
                    int minPath = getMinPathLength(distanceMatrix, i, j);
                    if (minPath >= 0 && (distanceMatrix[i][j] < 0 || minPath + 1 < distanceMatrix[i][j])) {
                        changed = true;
                        distanceMatrix[i][j] = minPath + 1;
                    }
                }
            }
        }
        return distanceMatrix;
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
            int minDestRowIndex;
            int minDestColumnIndex;
            if (minDest == north) {
                minDestRowIndex = i - 1;
                minDestColumnIndex = j;
            } else if (minDest == south) {
                minDestRowIndex = i + 1;
                minDestColumnIndex = j;
            } else if (minDest == east) {
                minDestRowIndex = i;
                minDestColumnIndex = j + 1;
            } else {
                minDestRowIndex = i;
                minDestColumnIndex = j - 1;
            }
            shortestPath.addPoint(new Point(minDestRowIndex, minDestColumnIndex));
            i = minDestRowIndex;
            j = minDestColumnIndex;
        }
        return shortestPath;
    }
}
