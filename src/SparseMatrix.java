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
                        SentinelLL.SentinelNode row = matrix.get(i);
                        if (row.east == row)
                            row.setEast(new DataNode<>(null, row, new LivingCell(i, j)));
                        else {
                            Node data = row.getEast();
                            while (data.east != row)
                                data = data.east;
                            data.setEast(new DataNode<>(null, row, new LivingCell(i, j)));
                        }
                        SentinelLL.SentinelNode column = matrix.get(j);
                        if (column.south == column) {
                            DataNode<T> result = getElement(i, j);
                            column.setSouth(result);
                            result.setSouth(column);
                        } else {
                            Node data = column.getSouth();
                            while (data.south != column)
                                data = data.south;
                            DataNode<T> result = getElement(i, j);
                            data.setSouth(result);
                            result.setSouth(column);
                        }

                    }
                }
            }
        }

    }

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
        SentinelLL.SentinelNode currentRow = matrix.get(node.getValue().getI());
//        if (currentRow.east == currentRow) {
//            System.exit(0);
//        } //TODO throw Exception if DataNode is not found
        Node current = currentRow;
        while (current.east != node) {
            current = current.getEast();
        }
        Node deleteNode = current.getEast();
        current.setEast(deleteNode.east);
    }


    public int getNeighboors(int i, int j) {
        int neighbours = 0;
        int minRow = i == 0 ? 0 : i - 1;
        int maxRow = i == (this.rows - 1) ? (this.rows - 1) : i + 1;
        int minCol = j == 0 ? 0 : j - 1;
        int maxCol = j == (this.columns - 1) ? (this.columns - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++)
                if (getElement(k, m).getValue().getClass() == LivingCell.class)
                    neighbours++;
        }

        if (getElement(i, j).getValue().getClass() == LivingCell.class)
            neighbours--;

        return neighbours;
    }


    public SentinelLL getMatrix() {
        return matrix;
    }


    @Override
    public void setElement(int i, int j) {

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
