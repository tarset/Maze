import java.util.Random;
import java.util.Stack;

class Generation {
    private static final int WALL = 0;
    private static final int CELL = 1;
    private static final int VISITED = 3;

    static int[][] creationMaze(int height, int width, int[][] maze) {
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                if ((i % 2 != 0 && j % 2 != 0) &&
                        (i < height - 1 && j < width - 1)) {
                    maze[i][j] = CELL;
                } else {
                    maze[i][j] = WALL;
                }

            }

        }

        Generation.generationMaze(maze, height, width);

        return maze;
    }

    private static void generationMaze(int[][] maze, int height, int width) {
        Stack<Cell> st = new Stack<>();
        Random random = new Random();
        Cell neighbourCell;

        Cell currentCell = new Cell();
        currentCell.x = 1;
        currentCell.y = 1;

        do {
            CellString Neighbours = getNeighbours(width, height, maze, currentCell, 2);

            if (Neighbours.size != 0) {
                String rand = String.valueOf(random.nextInt(Neighbours.size));
                int randNum = Integer.parseInt(rand);
                neighbourCell = Neighbours.cells[randNum];
                st.push(currentCell);
                maze = removeWall(currentCell, neighbourCell, maze);
                currentCell = neighbourCell;
                maze[currentCell.y][currentCell.x] = VISITED;
            } else if (st.size() > 0) {
                currentCell = st.pop();
            }

        }
        while (unvisitedCount(maze, height, width) > 0);

    }

    private static CellString getNeighbours(int width, int height, int[][] maze, Cell c, int distance) {
        int size = 0;
        int x = c.x;
        int y = c.y;

        Cell up = new Cell();
        up.x = x;
        up.y = y - distance;
        Cell rt = new Cell();
        rt.x = x + distance;
        rt.y = y;
        Cell dw = new Cell();
        dw.x = x;
        dw.y = y + distance;
        Cell lt = new Cell();
        lt.x = x - distance;
        lt.y = y;

        Cell[] d = new Cell[4];
        d[0] = dw;
        d[1] = rt;
        d[2] = lt;
        d[3] = up;

        CellString cells = new CellString();

        for (Cell aD : d) {

            if (aD.x > 0 && aD.x < width && aD.y > 0 && aD.y < height) {
                int mazeCellCurrent = maze[aD.y][aD.x];

                if (mazeCellCurrent == CELL) {
                    cells.cells[size] = aD;
                    size++;
                }

            }

        }

        cells.size = size;

        return cells;
    }

    private static int[][] removeWall(Cell first, Cell second, int[][] maze) {

        if (second.x - first.x == 2) {
            maze[second.y][second.x - 1] = VISITED;
        } else if (second.x - first.x == -2) {
            maze[second.y][second.x + 1] = VISITED;
        } else if (second.y - first.y == 2) {
            maze[second.y - 1][second.x] = VISITED;
        } else if (second.y - first.y == -2) {
            maze[second.y + 1][second.x] = VISITED;
        }

        return maze;
    }

    private static int unvisitedCount(int[][] maze, int height, int width) {
        int c = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == CELL) {
                    c++;
                }
            }
        }
        return c;
    }

}