import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EPointStateTest {

    @org.junit.Test
    public void getSymbol() {
        assertEquals('S', EPointState.START.getSymbol());
        assertEquals('G', EPointState.GOAL.getSymbol());
        assertEquals('_', EPointState.PATH.getSymbol());
        assertEquals('X', EPointState.WALL.getSymbol());
    }

    @org.junit.Test
    public void getPointState() {
        assertEquals(EPointState.START, EPointState.getPointState('S'));
        assertEquals(EPointState.GOAL, EPointState.getPointState('G'));
        assertEquals(EPointState.PATH, EPointState.getPointState('_'));
        assertEquals(EPointState.WALL, EPointState.getPointState('X'));
        assertNull(EPointState.getPointState('A'));
        assertNull(EPointState.getPointState('B'));
        assertNull(EPointState.getPointState('1'));
        assertNull(EPointState.getPointState('/'));
    }
}