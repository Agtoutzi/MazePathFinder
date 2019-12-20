package definition;

/**
 * Enum describing the state of a point in the matrix.
 */
public enum EPointState {
    START('S'),
    GOAL('G'),
    PATH('_'),
    WALL('X');

    private char symbol;

    EPointState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    /**
     * Finds and returns the {@link EPointState} instance that matches a given character symbol
     *
     * @param symbol the character symbol to be matched
     * @return the {@link EPointState} that matches the given character symbol or null if not found
     */
    public static EPointState getPointState(char symbol) {
        for (EPointState EPointState : EPointState.values()) {
            if (EPointState.getSymbol() == symbol) {
                return EPointState;
            }
        }
        return null;
    }
}
