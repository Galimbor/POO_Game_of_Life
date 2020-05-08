//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class BidimensionalMatrix<T> implements IMatrix<T> {
//
//    T[][] matrix;
//    int rows;
//    int columns;
//    List<LivingCell> livingCells;
//
//    @SuppressWarnings("unchecked")
//    public BidimensionalMatrix(ArrayList<String> input) {
//        this.rows = input.size();
//        this.columns = input.get(0).split("").length;
//        this.matrix = (T[][]) new Object[rows][columns];
//        this.livingCells = new ArrayList<>();
//        Iterator<String> iterator = input.iterator();
//        while (iterator.hasNext()) {
//            for (int i = 0; i < rows; i++) {
//                String[] line = iterator.next().split("");
//                for (int j = 0; j < columns; j++) {
//                    matrix[i][j] = (T) line[j];
//                    if (matrix[i][j] == "1")
//                        livingCells.add(new LivingCell());
//                }
//            }
//        }
//
//    }
//
//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                result.append(matrix[i][j]);
//            }
//            result.append("\n");
//        }
//        return result.toString();
//    }
//
//
//    @Override
//    public void setElement(int i, int j, T value) {
//
//    }
//
//    @Override
//    public T getElement(int i, int j) {
//        return null;
//    }
//
//    @Override
//    public int getRows() {
//        return rows;
//    }
//
//    @Override
//    public int getColumns() {
//        return columns;
//    }
//
//    @Override
//    public List<T> getWholeColumn(int column) {
//        return null;
//    }
//
//    @Override
//    public List<T> getWholeRow(int row) {
//        return null;
//    }
//
//    @Override
//    public void resize(int i, int j) {
//
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public void resize(int srcRPos, int srcCPos, int destRPos, int destCPos, int rows, int columns) {
//        this.rows = rows;
//        this.columns = columns;
//        this.matrix = cloneMatrixv2(matrix, srcRPos, srcCPos, destRPos, destCPos, this.rows, this.columns);
//
//    }
//
//    //TODO resize para os lados
//
//    //resize extende linha de baixo this.matrix = cloneMatrixv2(matrix,0,0,rows+1,columns);
//    //resize extende linha de cima  this.matrix = cloneMatrixv2(matrix,0,1,rows+1,columns);
//    //resize extende coluna da direita this.matrix = cloneMatrixv2(matrix,0,0,rows,columns+1);
//    //resize extende coluna da esquerda this.matrix = cloneMatrixv2(matrix,1,0,rows,columns+1);
//    //resize suprime linha de baixo this.matrix = cloneMatrixv2(matrix,0,0,rows-1,columns);
//    //resize suprime linha de cima  this.matrix = cloneMatrixv2(matrix,0,1,rows-1,columns);
//    //resize suprime coluna da direita this.matrix = cloneMatrixv2(matrix,0,0,rows,columns-1);
//    //resize suprime coluna da esquerda this.matrix = cloneMatrixv2(matrix,1,0,rows,columns-1);
//
//
//    public int getNeighboorsCountv2(T[][] m, int row, int column) {
//        int neighboors = 0;
//        int minRow = row == 0 ? 0 : row - 1;
//        int maxRow = row == (m.length - 1) ? (m.length - 1) : row + 1;
//        int minCol = column == 0 ? 0 : column - 1;
//        int maxCol = column == (m[0].length - 1) ? (m[0].length - 1) : column + 1;
//        for (int i = minRow; i <= maxRow; i++) {
//            for (int j = minCol; j <= maxCol; j++) {
//                neighboors += Integer.parseInt((String) m[i][j]);
//            }
//        }
//        neighboors -= Integer.parseInt((String) m[row][column]);
//        return neighboors;
//    }
//
//    public int getNeighboorsCount(int row, int column) {
//        int neighboors = 0;
//        int minRow = row == 0 ? 0 : row - 1;
//        int maxRow = row == (this.rows - 1) ? (this.rows - 1) : row + 1;
//        int minCol = column == 0 ? 0 : column - 1;
//        int maxCol = column == (this.columns - 1) ? (this.columns - 1) : column + 1;
//        for (int i = minRow; i <= maxRow; i++) {
//            for (int j = minCol; j <= maxCol; j++)
//                neighboors += Integer.parseInt((String) matrix[row][j]);
//        }
//        neighboors -= Integer.parseInt((String) matrix[row][column]);
//        return neighboors;
//    }
//
//    @SuppressWarnings("unchecked")
//    private T[][] cloneMatrix(T[][] matrix, int index, int srcPos, int destPos, int height, int width) {
//        T[][] copiedMatrix = (T[][]) new Object[height][width];
//        for (T[] clonableMatrix : matrix) {
//            System.arraycopy(clonableMatrix, 0, copiedMatrix[index++], 0, matrix[0].length);
//        }
//        return copiedMatrix;
//    }
//
//    @SuppressWarnings("unchecked")
//    private T[][] cloneMatrixv2(T[][] matrix, int srcRPos, int srcCPos, int destRPos, int destCPos, int height, int width) {
//        T[][] copiedMatrix = (T[][]) new Object[height][width];
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if ((i >= destCPos && i < matrix.length + destRPos) && (j >= destRPos && j < matrix[0].length + destCPos)) {
//                    //System.out.println("i = " + i + ", j = " + j);
//                    copiedMatrix[i][j] = matrix[i + srcRPos - destRPos][j + srcCPos - destCPos];
////                    System.out.println(copiedMatrix[i][j]);
//                } else {
////                    System.out.println("!");
//                    copiedMatrix[i][j] = (T) "0";
//                }
//            }
//        }
//        return copiedMatrix;
//    }
//
//    @SuppressWarnings("unchecked")
//    public void resizev2(T[][] matrix, int srcRPos, int srcCPos, int destRPos, int destCPos, int rows, int columns) {
//        this.rows = rows;
//        this.columns = columns;
//        this.matrix = cloneMatrixv2(matrix, srcRPos, srcCPos, destRPos, destCPos, this.rows, this.columns);
//
//    }
//

//    @SuppressWarnings("unchecked")
//    public void getNextGeneration() {
//        T[][] expanded = cloneMatrixv2(this.matrix, 0, 0, 1, 1, this.rows + 2, this.columns + 2);
//        T[][] result = cloneMatrixv2(this.matrix, 0, 0, 1, 1, this.rows + 2, this.columns + 2);
//        int addBotLine = 1;
//        int addUpLine = 1;
//        int addRightColumn = 1;
//        int addLeftColumn = 1;
//        for (int i = 0; i < expanded.length; i++) {
//            for (int j = 0; j < expanded[0].length; j++) {
//                int neighboors = getNeighboorsCountv2(expanded, i, j);
//                if (String.valueOf(expanded[i][j]).equals("1") && (neighboors < 2 || neighboors > 3)) {
//                    //System.out.println("i was alive, my position is " + i + " " + j + " and I have " + neighboors + " neighboors");
//                    result[i][j] = (T) "0";
//                } else if (String.valueOf(expanded[i][j]).equals("0") && neighboors == 3) {
//                    //System.out.println("i was dead, my position is " + i + " " + j + " and I have " + neighboors + " neighboors");
//                    result[i][j] = (T) "1";
//                    if (i == 0)
//                        addUpLine = 0;
//                    else if (i == expanded.length - 1)
//                        addBotLine = 0;
//                    if (j == 0)
//                        addLeftColumn = 0;
//                    else if (j == expanded[0].length - 1)
//                        addRightColumn = 0;
//                } else {
//                }
//            }
//        }
////        System.out.println("addBotLIne = " + addBotLine);
////        System.out.println("addUpLIne = " + addUpLine);
////        System.out.println("addRightColumn = " + addRightColumn);
////        System.out.println("addLeftColumn = " + addLeftColumn);
//        this.rows = result.length - addUpLine - addBotLine;
//        this.columns = result[0].length - addRightColumn - addLeftColumn;
//        this.matrix = cloneMatrixv2(result, addUpLine, addLeftColumn, 0, 0, this.rows, this.columns);
//        //this.resizev2(result,addUpLine,addLeftColumn,0,0,result.length - addUpLine - addBotLine,result[0].length - addRightColumn - addRightColumn);
////        System.out.println("result columns = " + this.columns);
////        System.out.println("result rows = " + this.rows);
////        System.out.println("result[0].length = " + result[0].length);
//    }
//}
