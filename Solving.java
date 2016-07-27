import java.util.Stack;

class Solving {
    private static final int VISITED = 1;
    private static final int WRONG_WAY = 2;
    private static final int WAY = 3;

    static int[][] solvingMaze(int[][] maze, int height, int width) {
        Stack<Cell> st2 = new Stack<>();
        Cell neighbourCell;

        Cell currentCell = new Cell();
        currentCell.y = 1;
        currentCell.x = 1;

        do {
            CellString Neighbours = getNeighbours(width, height, maze, currentCell, 1);

            if (Neighbours.size != 0) {
                st2.push(currentCell);
                maze[currentCell.y][currentCell.x] = WAY;
                neighbourCell = Neighbours.cells[0];
                currentCell = neighbourCell;
            } else if (st2.size() > 0) {
                maze[currentCell.y][currentCell.x] = WRONG_WAY;
                currentCell = (st2.pop());
            }

        } while (maze[height - 2][width - 2] == VISITED);

        return maze;
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

                if (mazeCellCurrent == VISITED) {
                    cells.cells[size] = aD;
                    size++;
                }

            }

        }

        cells.size = size;

        return cells;
    }
}