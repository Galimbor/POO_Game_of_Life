import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SparseMatrix<T> implements IMatrix<DataNode<T>> {

    private SentinelLL matrix;
    private int rows;
    private int columns;


    public SparseMatrix(ArrayList<String> input) {
        this.rows = input.size();
        this.columns = input.get(0).split("").length;
        this.matrix = new SentinelLL();
        for (int i = 0; i < Math.max(this.rows, this.columns); i++) {
            matrix.add(new SentinelLL.SentinelNode(i));
            matrix.get(i).setSouth(matrix.get(i));
            matrix.get(i).setEast(matrix.get(i));

        }
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < columns; j++) {
                    if (line[j].equals("1")) {
                        addDataNode(i, j);
                    }
                }
            }

        }
    }


    public SparseMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new SentinelLL();
        for (int i = 0; i < Math.max(this.rows, this.columns); i++) {
            matrix.add(new SentinelLL.SentinelNode(i));
            matrix.get(i).setSouth(matrix.get(i));
            matrix.get(i).setEast(matrix.get(i));
        }
    }


//    @SuppressWarnings("unchecked")
//    public Object clone() throws CloneNotSupportedException {
//        SparseMatrix<T> newSparse = (SparseMatrix<T>) super.clone();
//        newSparse.matrix = (SentinelLL) getMatrix().clone();
//        return newSparse;
//    }

    @SuppressWarnings("unchecked")
    @Override
    public DataNode<T> getElement(int i, int j) {
        SentinelLL.SentinelNode currentRow = matrix.get(i);
        Node token = currentRow;
        DataNode<LivingCell> result = null;
        while (token.east != currentRow) {
            DataNode<LivingCell> data = (DataNode<LivingCell>) token.getEast();
            if (data.getValue().getJ() == j) {
                result = data;
                break;
            }
            token = token.east;
        }
        return (DataNode<T>) result;
    }

    @SuppressWarnings("unchecked")
    public void deleteDataNode(DataNode<T> dataNode) {
        DataNode<LivingCell> node = (DataNode<LivingCell>) dataNode;
        //        if (currentRow.east == currentRow) {
//            System.exit(0);
//        } //TODO throw Exception if DataNode is not found
        Node current = this.getMatrix().get(node.getValue().getI());
        while (current.getEast() != node) {
            current = current.getEast();
        }
        Node deleteNode = current.getEast();
        current.setEast(deleteNode.east);
    }

    @SuppressWarnings("unchecked")
    public void addDataNode(int i, int j) {
        SentinelLL.SentinelNode row = getMatrix().get(i);
        if (row.getEast() == row)
            row.setEast(new DataNode<>(null, row, new LivingCell(i, j)));
        else {
            Node data = row.getEast();
            while (data.getEast() != row)
                data = data.east;
            data.setEast(new DataNode<>(null, row, new LivingCell(i, j)));
        }
        SentinelLL.SentinelNode column = getMatrix().get(j);
        if (column.south == column) {
            DataNode<T> result = getElement(i, j);
            column.setSouth(result);
            result.setSouth(column);
        } else {
            Node data = column.getSouth();
            while (data.getSouth() != column)
                data = data.getSouth();
            DataNode<T> result = getElement(i, j);
            data.setSouth(result);
            result.setSouth(column);
        }
    }

    public int getNeighbours(int i, int j) {
        int neighbours = 0;
        int minRow = i == 0 ? 0 : i - 1;
        int maxRow = i == (this.rows - 1) ? (this.rows - 1) : i + 1;
        int minCol = j == 0 ? 0 : j - 1;
        int maxCol = j == (this.columns - 1) ? (this.columns - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++)
                if (getElement(k, m) != null)
                    neighbours++;
        }

        if (getElement(i, j) != null)
            neighbours--;

        return neighbours;
    }

    public SparseMatrix<T> nextGen() {
        SparseMatrix<T> next = new SparseMatrix<>(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int neighbours = this.getNeighbours(i, j);
                if (this.getElement(i, j) == null && neighbours == 3) {
                    next.addDataNode(i, j);
                } else if ((this.getElement(i, j) != null) && (neighbours >= 2 && neighbours <= 3)) {
                    next.addDataNode(i, j);
                } else {
                    //do nothing
                }
            }

        }
        return next;
    }


    public SentinelLL getMatrix() {
        return this.matrix;
    }


    @Override
    public void setElement(int i, int j) {

    }


    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getColumns() {
        return this.columns;
    }

    @Override
    public List getWholeColumn(int column) {
        return null;
    }

    @Override
    public List getWholeRow(int row) {
        return null;
    }

    @Override
    public void resize(int srcRPos, int srcCPos, int destRPos, int destCPos, int rows, int columns) {

    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (this.getElement(i, j) == null)
                    result = result.concat("0");
                else if (this.getElement(i, j).getClass() == DataNode.class) {
                    result = result.concat("1");
                } else { //do nothing
                }
            }
            result = result.concat("\n");
        }
        return result;
    }
}
