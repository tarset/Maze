import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

class Maze {
    private static final int CELL = 1;
    private static final int WRONG_WAY = 2;
    private static final int WAY = 3;
    private static String pathToFile = "res//";
    private static String GeneratedFile = pathToFile + "Generated maze.png";
    private static String SolvedFile = pathToFile + "Solved maze.png";

    static int[][] substituteForSolving(int[][] maze, int height, int width) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == WAY) {
                    maze[i][j] = CELL;
                }
            }
        }

        return maze;
    }

    static int[][] substituteForRemoveWrongWay(int[][] maze, int height, int width) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == WRONG_WAY) {
                    maze[i][j] = CELL;
                }
            }
        }

        return maze;
    }

    static void outputGeneratedMazeImage(int[][] maze, int height, int width) {
        maze[0][1] = WAY;
        maze[height - 1][width - 2] = WAY;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        try {
            File f = new File(GeneratedFile);
            f.mkdirs();

            for (int x = 0; x < height; x++) {

                for (int y = 0; y < width; y++) {

                    if (maze[x][y] == WAY) {
                        Color temp = new Color(255, 255, 255);
                        img.setRGB(y, x, temp.getRGB());
                    }

                }

            }

            ImageIO.write(enlarge(img, 5), "PNG", f);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void outputSolvingMazeImage(int[][] maze, int height, int width) {
        maze[0][1] = WAY;
        maze[height - 1][width - 2] = WAY;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        try {
            File f = new File(SolvedFile);
            f.mkdirs();

            for (int x = 0; x < height; x++) {

                for (int y = 0; y < width; y++) {

                    if (maze[x][y] == WAY) {
                        Color temp = new Color(255, 0, 0);
                        img.setRGB(y, x, temp.getRGB());
                    } else if (maze[x][y] == WRONG_WAY || maze[x][y] == CELL) {
                        Color temp = new Color(255, 255, 255);
                        img.setRGB(y, x, temp.getRGB());
                    }

                }

            }

            ImageIO.write(enlarge(img, 5), "PNG", f);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static BufferedImage enlarge(BufferedImage image, int n) {
        int w = n * image.getWidth();
        int h = n * image.getHeight();

        BufferedImage enlargedImage = new BufferedImage(w, h, image.getType());

        for (int y = 0; y < h; ++y) {

            for (int x = 0; x < w; ++x) {
                enlargedImage.setRGB(x, y, image.getRGB(x / n, y / n));
            }

        }

        return enlargedImage;
    }

}

class Cell {
    int x = 0;
    int y = 0;
}

class CellString {
    Cell[] cells = new Cell[3];
    int size = 0;
}