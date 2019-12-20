package algorithm;

import definition.Path;
import exception.InvalidInputException;

public interface IPathFinder {
    Path findPath() throws InvalidInputException;
}
