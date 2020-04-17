import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class BidimensionalMatrix implements IMatrix {

    Integer[][] matrix;
    int rows;
    int columns;


    public BidimensionalMatrix(ArrayList<String> input) {
        this.rows = input.size();
        this.columns = input.get(0).split("").length;
        this.matrix = new Integer[rows][columns];
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }

        }
    }

    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append((int) matrix[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void setElement(int i, int j) {

    }

    @Override
    public Integer getElement(int i, int j) {
        return null;
    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public int getColumns() {
        return 0;
    }

    @Override
    public List<Integer> getWholeColumn(int column) {
        return null;
    }

    @Override
    public List<Integer> getWholeRow(int row) {
        return null;
    }

    @Override
    public void resize(int rows, int columns) {

    }
//        private Integer[][] cloneMatrix (Integer[][]matrix)
//        {
//            @SuppressWarnings("unchecked")
//            Integer[][] copiedMatrix = (Integer[][]) new Object[matrix.length][matrix[0].length];
//            for (Integer[] clonableMatrix : matrix) System.arraycopy(clonableMatrix, 0, copiedMatrix[0], 0, matrix[0].length);
//            System.out.println(copiedMatrix[0][0]);
//            return copiedMatrix;
//        }



    }
