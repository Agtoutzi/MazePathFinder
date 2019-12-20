package main;

import algorithm.IPathFinder;
import algorithm.PathFinder;
import definition.Maze;
import definition.Path;
import exception.InvalidInputException;
import fileread.InputRead;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new InvalidInputException("File not provided");
            }
            Maze maze = InputRead.readMazeFromFile(args[0]);
            IPathFinder pathFinder = new PathFinder(maze);
            Path path = pathFinder.findPath();
            System.out.println(path.toString());
        } catch (InvalidInputException e) {
            System.out.println("Invalid Maze input: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Maze could not be read from file: " + e);
        }
    }
}
