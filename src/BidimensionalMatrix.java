import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BidimensionalMatrix<T> implements IMatrix<T> {

    T[][] matrix;
    int rows;
    int columns;
    List<LivingCell> livingCells;

    @SuppressWarnings("unchecked")
    public BidimensionalMatrix(ArrayList<String> input) {
        this.rows = input.size();
        this.columns = input.get(0).split("").length;
        this.matrix = (T[][]) new Object[rows][columns];
        this.livingCells = new ArrayList<>();
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = (T) line[j];
                    if (Integer.parseInt((String) matrix[i][j]) == 1)
                        livingCells.add(new LivingCell(i, j));
                }
            }
        }

//        T[][] clone;
//        clone = cloneMatrix(matrix,rows+2,columns+2);
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < rows+2; i++) {
//            for (int j = 0; j < columns+2; j++) {
//                result.append(clone[i][j]);
//            }
//            result.append("\n");
//        }
//        System.out.println(result.toString());
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(matrix[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void setElement(int i, int j) {

    }

    @Override
    public T getElement(int i, int j) {
        return null;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public List<T> getWholeColumn(int column) {
        return null;
    }

    @Override
    public List<T> getWholeRow(int row) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void resize(int rows, int columns) {                    //TODO ter em conta ao lado que quer expandir
        if (rows >= this.rows && columns >= this.columns) {
            this.rows = rows;
            this.columns = columns;
            this.matrix = cloneMatrix(matrix,rows,columns);
        } else {//Exception}
        }
    }


    public int getNeighboorsCountv2(int row, int column) {              //TODO implementar
        int neighboors = 0;
        int minRow = row == 0 ? 0 : row - 1;
        int maxRow = row == (this.rows - 1) ? (this.rows - 1) : row + 1;
        int minCol = column == 0 ? 0 : column - 1;
        int maxCol = column == (this.columns - 1) ? (this.columns - 1) : column + 1;
        for (int i = minRow; i < maxRow; i++) {
            for (int j = minCol; j < maxCol; j++)
                neighboors += (int) matrix[row][j];
        }
        neighboors -= (int) matrix[row][column];
        return neighboors;
    }

    public int getNeighboorsCount(int row, int column) {
        int neighboors = 0;
        int minRow = row == 0 ? 0 : row - 1;
        int maxRow = row == (this.rows - 1) ? (this.rows - 1) : row + 1;
        int minCol = column == 0 ? 0 : column - 1;
        int maxCol = column == (this.columns - 1) ? (this.columns - 1) : column + 1;
        for (int i = minRow; i < maxRow; i++) {
            for (int j = minCol; j < maxCol; j++)
                neighboors += (int) matrix[row][j];
        }
        neighboors -= (int) matrix[row][column];
        return neighboors;
    }

    private T[][] cloneMatrix(T[][] matrix,int height, int width) {
        @SuppressWarnings("unchecked")
        T[][] copiedMatrix = (T[][]) new Object[height][width];
        int i = 0;
        for (T[] clonableMatrix : matrix) {
            System.arraycopy(clonableMatrix, 0, copiedMatrix[i++], 0, matrix[0].length);
        }
        return copiedMatrix;
    }
}
