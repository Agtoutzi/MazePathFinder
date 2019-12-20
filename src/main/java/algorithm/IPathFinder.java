package algorithm;

import definition.Path;
import exception.InvalidInputException;

/**
 * Interface for path-finding.
 */
public interface IPathFinder {
    /**
     * Finds/Calculates a {@link Path}.
     *
     * @return the calculated {@link Path}
     * @throws InvalidInputException if the input is invalid(or a valid path does not exist)
     */
    Path findPath() throws InvalidInputException;
}
