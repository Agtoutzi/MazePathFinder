import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest {

    @Test
    public void getX() {
        Point point = new Point(5, 5);
        assertEquals(5, point.getX());
    }

    @Test
    public void getY() {
        Point point = new Point(1, 100);
        assertEquals(100, point.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getX_Negative() {
        new Point(-5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getY_Negative() {
        new Point(1, -100);
    }
}