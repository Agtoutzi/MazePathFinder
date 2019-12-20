package definition;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathTest {
    private Path path;

    @org.junit.Before
    public void Before() {
        path = new Path();
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testToString_EmptyPath() {
        path.toString();
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testToString_Invalid() {
        path.addPoint(new Point(1, 1));
        path.toString();
    }

    @org.junit.Test
    public void testToString() {
        path.addPoint(new Point(0, 0));
        path.addPoint(new Point(1, 0));
        path.addPoint(new Point(2, 0));
        path.addPoint(new Point(3, 0));
        path.addPoint(new Point(4, 0));
        path.addPoint(new Point(4, 1));
        path.addPoint(new Point(4, 2));
        path.addPoint(new Point(3, 2));
        path.addPoint(new Point(3, 3));
        path.addPoint(new Point(3, 4));
        assertEquals("(1:1 (S)), (2:1), (3:1), (4:1), (5:1), (5:2), (5:3), (4:3), (4:4), (4:5 (G))",
                path.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPoint_invalid() {
        path.addPoint(null);
    }
}