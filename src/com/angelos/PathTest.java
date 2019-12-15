package com.angelos;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PathTest {

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void instance() {
        new Path(null);
        new Path(new ArrayList<>());
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(1,1));
        new Path(list);
    }

    @org.junit.Test
    public void testToString() {
        List<Point> list = new ArrayList();
        list.add(new Point(1, 1));
        list.add(new Point(2, 1));
        list.add(new Point(3, 1));
        list.add(new Point(4, 1));
        list.add(new Point(5, 1));
        list.add(new Point(5, 2));
        list.add(new Point(5, 3));
        list.add(new Point(4, 3));
        list.add(new Point(4, 4));
        list.add(new Point(4, 5));
        Path path = new Path(list);
        assertEquals("(1:1 (S)), (2:1), (3:1), (4:1), (5:1), (5:2), (5:3), (4:3), (4:4), (4:5 (G))",
                path.toString());
    }
}