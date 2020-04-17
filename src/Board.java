import java.util.ArrayList;
import java.util.List;

//public class Board {
//    private int rows;
//    private int columns;
//    private Cell[][] grid;
//
//
//    public Board(int[][] input, int i, int j) {
//        this.rows = i;
//        this.columns = j;
//        Cell[][] provGrid = new Cell[rows][columns];
//        for (int k = 0; k < rows; k++)
//            for (int m = 0; m < columns; m++)
//                provGrid[k][m] = new Cell(k, m, input[k][m]);
//        this.grid = cloneGrid(provGrid);
//        List test = new ArrayList();
//    }
//
//    private static Cell[][] cloneGrid(Cell[][] grid) {
//        Cell[][] copiedGrid = new Cell[grid.length][grid[0].length];
//        for (Cell[] cells : grid) System.arraycopy(cells, 0, copiedGrid[0], 0, grid[0].length);
//        return copiedGrid;
//    }
//
//
//}
