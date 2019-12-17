package com.angelos;

/**
 * Class representing a Maze definition.
 */
public class Maze {
    private EPointState[][] matrix;

    private Point startPoint;
    private Point goalPoint;

    public Maze(EPointState[][] matrix) throws Exception {
        this.matrix = matrix;
        startPoint = getPoint(EPointState.START);
        goalPoint = getPoint(EPointState.GOAL);
    }

    private Point getPoint(EPointState pointState) throws Exception {
        Point point = null;
        boolean found = false;
        for (int i = 0; i < matrix.length && !found; i++) {
            for (int j = 0; j < matrix[i].length && !found; j++) {
                if (getPointState(i, j) == pointState) {
                    point = new Point(i,j);
                    found = true;
                }
            }
        }
        if (!found) {
            throw new Exception(pointState.toString() + " point not found in Maze.");
        }
        return point;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getGoalPoint() {
        return goalPoint;
    }

    public EPointState getPointState(int pointX, int pointY) {
//        if (pointX >= matrix.length) {
//            throw new IndexOutOfBoundsException("");
//        }

        return matrix[pointX][pointY];
    }

    public int getRowLength() {
        return matrix.length;
    }

    public int getColumnLength() {
        return matrix[0].length;
    }
}
