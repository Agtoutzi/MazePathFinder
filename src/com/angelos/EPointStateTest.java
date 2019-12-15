package com.angelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EPointStateTest {

    @org.junit.jupiter.api.Test
    void getSymbol() {
        assertEquals('S', EPointState.START.getSymbol());
        assertEquals('G', EPointState.GOAL.getSymbol());
        assertEquals('_', EPointState.PATH.getSymbol());
        assertEquals('X', EPointState.WALL.getSymbol());
    }

    @org.junit.jupiter.api.Test
    void getPointState() {
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