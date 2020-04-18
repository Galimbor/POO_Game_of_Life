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
    public void resize(int rows, int columns) {
        if (rows >= this.rows && columns >= this.columns) {

            this.rows = rows;
            this.columns = columns;
            this.matrix = cloneMatrixv2(matrix, 1, 1, this.rows, this.columns);

        } else {//Exception}
        }
    }

    //TODO resize para os lados

    //resize extende linha de baixo this.matrix = cloneMatrixv2(matrix,0,0,rows+1,columns);
    //resize extende linha de cima  this.matrix = cloneMatrixv2(matrix,0,1,rows+1,columns);
    //resize extende coluna da direita this.matrix = cloneMatrixv2(matrix,0,0,rows,columns+1);
    //resize extende coluna da esquerda this.matrix = cloneMatrixv2(matrix,1,0,rows,columns+1);


    public int getNeighboorsCountv2(T[][] m, int row, int column) {
        int neighboors = 0;
        int minRow = row == 0 ? 0 : row - 1;
        int maxRow = row == (m[0].length - 1) ? (m[0].length - 1) : row + 1;
        int minCol = column == 0 ? 0 : column - 1;
        int maxCol = column == (m.length - 1) ? (m.length - 1) : column + 1;
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                neighboors += Integer.parseInt((String) m[i][j]);
            }
        }
        neighboors -= Integer.parseInt((String) m[row][column]);
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
                neighboors += Integer.parseInt((String) matrix[row][j]);
        }
        neighboors -= Integer.parseInt((String) matrix[row][column]);
        return neighboors;
    }

    @SuppressWarnings("unchecked")
    private T[][] cloneMatrix(T[][] matrix, int index, int srcPos, int destPos, int height, int width) {
        T[][] copiedMatrix = (T[][]) new Object[height][width];
        for (T[] clonableMatrix : matrix) {
            System.arraycopy(clonableMatrix, 0, copiedMatrix[index++], 0, matrix[0].length);
        }
        return copiedMatrix;
    }

    @SuppressWarnings("unchecked")
    private T[][] cloneMatrixv2(T[][] matrix, int destRPos, int destCPos, int height, int width) {
        T[][] copiedMatrix = (T[][]) new Object[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i >= destCPos && i < matrix[0].length + destCPos && j >= destRPos && j < matrix.length + destRPos) {
                    copiedMatrix[i][j] = matrix[i - destCPos][j - destRPos];
                } else {
                    copiedMatrix[i][j] = (T) "0";
                }
            }
        }
        return copiedMatrix;
    }

    //TODO check board method using getNeighboorsCount and resize
}
