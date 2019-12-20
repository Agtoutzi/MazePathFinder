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

    public static EPointState getPointState(char symbol) {
        for (EPointState EPointState : EPointState.values()) {
            if (EPointState.getSymbol() == symbol) {
                return EPointState;
            }
        }
        return null;
    }
}
