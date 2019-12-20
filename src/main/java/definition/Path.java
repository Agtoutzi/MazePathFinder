package definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a path as a list of {@link Point} objects.
 */
public class Path {
    private List<Point> pointList;

    /**
     * Constructor. Path is initially empty.
     */
    public Path() {
        this.pointList = new ArrayList<>();
    }

    /**
     * Adds a {@link Point} at the end of the path.
     *
     * @param point the point to be added
     */
    public void addPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point added cannot be null");
        }
        pointList.add(point);
    }

    /**
     * Generates a representation of the path, as a sequence of coordinates. First point in the path is set as START
     * and has an S symbol and last point in the path is set as GOAL and has a G symbol.
     *
     * @return the representation of the path
     */
    @Override
    public String toString() {
        if (pointList.size() < 2) {
            throw new IllegalArgumentException("Point-list too small");
        }

        StringBuilder sb = new StringBuilder();
        Point point = pointList.get(0);
        sb.append("(")
                .append(point.getX() + 1)
                .append(":")
                .append(point.getY() + 1)
                .append(" (")
                .append(EPointState.START.getSymbol())
                .append("))");
        for (int i = 1; i < pointList.size() - 1; i++) {
            point = pointList.get(i);
            sb.append(", (")
                    .append(point.getX() + 1)
                    .append(":")
                    .append(point.getY() + 1)
                    .append(")");
        }
        point = pointList.get(pointList.size() - 1);
        sb.append(", (")
                .append(point.getX() + 1)
                .append(":")
                .append(point.getY() + 1)
                .append(" (")
                .append(EPointState.GOAL.getSymbol())
                .append("))");
        return sb.toString();
    }
}
