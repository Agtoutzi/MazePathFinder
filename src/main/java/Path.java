import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Point> pointList;

    public Path() {
        this.pointList = new ArrayList<>();
    }

    public void addPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point added cannot be null");
        }
        pointList.add(point);
    }

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
