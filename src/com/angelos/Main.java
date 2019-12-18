package com.angelos;

public class Main {

    public static void main(String[] args) {
        try {
            Maze maze = InputRead.readMazeFromFile(args[0]);
            IPathFinder pathFinder = new PathFinder(maze);
            Path path = pathFinder.findPath();
            System.out.println(path.toString());
        } catch (Exception e) {
            System.out.println("Invalid Maze input: " + e.getMessage());
        }
    }
}
