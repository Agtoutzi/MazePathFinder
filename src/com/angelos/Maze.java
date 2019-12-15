package com.angelos;

/**
 * Class representing a Maze definition.
 */
public class Maze {
    private EPointState[][] matrix;

    private int[] startPoint;
    private int[] goalPoint;

    public Maze(EPointState[][] matrix) throws Exception {
        this.matrix = matrix;
        startPoint = getPoint(EPointState.START);
        goalPoint = getPoint(EPointState.GOAL);
    }

    private int[] getPoint(EPointState pointState) throws Exception {
        int[] point = new int[2];
        boolean found = false;
        for (int i = 0; i < matrix.length && !found; i++) {
            for (int j = 0; j < matrix[i].length && !found; j++) {
                if (getPointState(i, j) == pointState) {
                    point[0] = i;
                    point[1] = j;
                    found = true;
                }
            }
        }
        if (!found) {
            throw new Exception(pointState.toString() + " point not found in Maze.");
        }
        return point;
    }

    public int[] getStartPoint() {
        return startPoint;
    }

    public int[] getGoalPoint() {
        return goalPoint;
    }

    public EPointState getPointState(int pointX, int pointY) {
//        if (pointX >= matrix.length) {
//            throw new IndexOutOfBoundsException("");
//        }

        return matrix[pointX][pointY];
    }
}
