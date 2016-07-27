import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        int height = inputHeight();
        int width = inputWidth();

        int[][] maze = new int[height][width];

        maze = Generation.creationMaze(height, width, maze);
        Maze.outputGeneratedMazeImage(maze, height, width);

        maze = Maze.substituteForSolving(maze, height, width);
        maze = Solving.solvingMaze(maze, height, width);
        maze = Maze.substituteForRemoveWrongWay(maze, height, width);
        Maze.outputSolvingMazeImage(maze, height, width);
    }

    private static int inputHeight() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int height = 0;
        String s = null;

        System.out.print("Enter height maze (min 7): ");

        try {
            s = reader.readLine();
        } catch (Exception e) {
            System.err.println("\nNecessary enter number, enter again.");
            inputHeight();
        }

        assert s != null;
        height = Integer.parseInt(s);

        if (height % 2 == 0) {
            height--;
        }

        if (height <= 6) {
            System.err.println("\nNecessary enter number more 6, enter again.");
            inputHeight();
        }

        return height;
    }

    private static int inputWidth() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int width = 0;
        String s = null;

        System.out.print("Enter width maze (min 7): ");

        try {
            s = reader.readLine();
        } catch (Exception e) {
            System.err.println("\nNecessary enter number, enter again.");
            inputWidth();
        }

        assert s != null;
        width = Integer.parseInt(s);

        if (width % 2 == 0) {
            width--;
        }

        if (width <= 6) {
            System.err.println("\nNecessary enter number more 6, enter again.");
            inputWidth();
        }

        return width;
    }

}