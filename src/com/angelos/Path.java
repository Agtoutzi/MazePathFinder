package com.angelos;

import java.util.List;

public class Path {
    private List<Point> pointList;

    public Path(List<Point> pointList) {
        if (pointList == null || pointList.size() < 2) {
            throw new IllegalArgumentException("Point-list parameter is invalid");
        }
        this.pointList = pointList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Point point = pointList.get(0);
        sb.append("(")
                .append(point.getX())
                .append(":")
                .append(point.getY())
                .append(" (")
                .append(EPointState.START.getSymbol())
                .append("))");
        for (int i = 1; i < pointList.size() - 1; i++) {
            point = pointList.get(i);
            sb.append(", (")
                    .append(point.getX())
                    .append(":")
                    .append(point.getY())
                    .append(")");
        }
        point = pointList.get(pointList.size() - 1);
        sb.append(", (")
                .append(point.getX())
                .append(":")
                .append(point.getY())
                .append(" (")
                .append(EPointState.GOAL.getSymbol())
                .append("))");
        return sb.toString();
    }
}
