package definition;

/**
 * Definition of a point's coordinates in a 2D Matrix.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Invalid Point coordinates");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        return ((Point) obj).getX() == x && ((Point) obj).getY() == y;
    }
}
