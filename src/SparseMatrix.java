import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 *
 * @param <L>
 */
public class SparseMatrix<L> implements IMatrix<DataNode<L>, L>, Cloneable {
    private SentinelLL matrix;
    private int rows;
    private int columns;
    private int startingRow;
    private int startingColumn;

    /***
     *
     * @param input
     */
    public SparseMatrix(ArrayList<String> input) {
        setStartingRow(0);
        setStartingColumn(0);
        setRows(input.size());
        setColumns(input.get(0).split("").length);
        setMatrix(input);
    }

    /**
     * @param startingRow
     * @param startingColumn
     * @param rows
     * @param columns
     */
    public SparseMatrix(int startingRow, int startingColumn, int rows, int columns) {
        setStartingColumn(startingColumn);
        setStartingRow(startingRow);
        setRows(rows);
        setColumns(columns);
        setMatrix();
    }

    /***
     *
     */
    public void setMatrix() {
        this.matrix = new SentinelLL();
        for (int i = Math.min(this.startingRow, startingColumn); i < Math.max(this.rows, this.columns); i++) {
            this.matrix.add(new SentinelLL.SentinelNode(i));
        }
    }

    /***
     *
     * @param input
     */
    @SuppressWarnings("unchecked")
    public void setMatrix(ArrayList<String> input) {
        setMatrix();
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < this.rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < this.columns; j++) {
                    if (line[j].equals("1")) {
                        setElement(i, j, (L) "add");
                    }
                }
            }
        }
    }

    /***
     *
     * @param rows
     */
    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }

    /***
     *
     * @param columns
     */
    @Override
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /***
     *
     * @param startingRow
     */
    public void setStartingRow(int startingRow) {
        this.startingRow = startingRow;
    }

    /***
     *
     * @param startingColumn
     */
    public void setStartingColumn(int startingColumn) {
        this.startingColumn = startingColumn;
    }

    /***
     *
     * @param i
     * @param j
     * @param value
     */
    @Override
    public void setElement(int i, int j, L value) {
        String arg = (String) value;
        if (arg.equals("delete"))
            deleteDataNode(i, j);
        else if (arg.equals("add"))
            addDataNode(i, j);
        else {
            //TODO Throw exception
        }
    }

    /***
     *
     * @return
     */
    public SentinelLL getMatrix() {
        return this.matrix;
    }

    /***
     *
     * @return
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /***
     *
     * @return
     */
    @Override
    public int getColumns() {
        return this.columns;
    }

    /***
     *
     * @return
     */
    public int getStartingColumn() {
        return startingColumn;
    }

    /***
     *
     * @return
     */
    public int getStartingRow() {
        return startingRow;
    }

    /***
     *
     * @param i
     * @param j
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public DataNode<L> getElement(int i, int j) {
        if (!matrix.existsSentinelNumber(i))
            return null;
        SentinelLL.SentinelNode currentRow = this.matrix.getSentinel(i);
        Node token = currentRow;
        DataNode<LivingCell> result = null;
        while (token.getEast() != currentRow) {
            DataNode<LivingCell> data = (DataNode<LivingCell>) token.getEast();
            if (data.getJ() == j) {
                result = data;
                break;
            }
            token = token.getEast();
        }
        return (DataNode<L>) result;
    }

    /***
     *
     * @param column
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<DataNode<L>> getWholeColumn(int column) {
        ArrayList<DataNode<L>> result = new ArrayList<>();
        SentinelLL.SentinelNode sentinel = matrix.getSentinel(column);
        while (sentinel.getSouth() != sentinel)
            result.add((DataNode<L>) sentinel.getSouth());
        return result;
    }

    /***
     *
     * @param row
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<DataNode<L>> getWholeRow(int row) {
        ArrayList<DataNode<L>> result = new ArrayList<>();
        SentinelLL.SentinelNode sentinel = matrix.getSentinel(row);
        while (sentinel.getSouth() != sentinel)
            result.add((DataNode<L>) sentinel.getEast());
        return result;
    }

    /***
     *
     * @param i
     * @param j
     * @return
     */
    private int getNeighbours(int i, int j) {
        int neighbours = 0;
        int minRow = i < startingRow ? startingRow : i - 1;
        int maxRow = i > (this.rows - 1) ? (this.rows - 1) : i + 1;
        int minCol = j < startingColumn ? startingColumn : j - 1;
        int maxCol = j > (this.columns - 1) ? (this.columns - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++) {
                if (getElement(k, m) != null)
                    neighbours++;
            }
        }
        if (matrix.existsSentinelNumber(i) && getElement(i, j) != null)
            neighbours--;

        return neighbours;
    }

    /***
     *
     * @param i
     * @param j
     */
    private void redefineBorders(int i, int j) {
        if (i < this.startingRow) {
            --this.startingRow;
        } else if (i > this.rows - 1) {
            ++this.rows;
        }
        if (j < this.startingColumn) {
            --this.startingColumn;
        } else if (j > this.columns - 1) {
            ++this.columns;
        }
    }

    /***
     *
     * @param i
     * @param j
     */
    @Override
    public void resize(int i, int j) {
        if (!this.matrix.existsSentinelNumber(i)) {
            this.matrix.addFirst(new SentinelLL.SentinelNode(i));
        }
        if (!this.matrix.existsSentinelNumber(j)) {
            this.matrix.add(new SentinelLL.SentinelNode(j));
        }
        redefineBorders(i, j);
    }

    /***
     *
     * @param i
     * @param j
     */
    @SuppressWarnings("unchecked")
    private void deleteDataNode(int i, int j) {
        DataNode<LivingCell> node = (DataNode<LivingCell>) getElement(i, j);
        //        if (currentRow.east == currentRow) {
//            System.exit(0);
//        } //TODO throw Exception if DataNode is not found treated in getElement.
        Node currentRow = this.matrix.getSentinel(node.getI());
        while (currentRow.getEast() != node) {
            currentRow = currentRow.east;
        }
        currentRow.setEast(node.east);
        Node currentColumn = this.matrix.getSentinel(node.getJ());
        while (currentColumn.getSouth() != node) {
            currentColumn = currentColumn.south;
        }
        currentColumn.setSouth(node.south);
    }

    /***
     *
     * @param i
     * @param j
     */
    private void addDataNode(int i, int j) {
        SentinelLL.SentinelNode row = this.matrix.getSentinel(i);
        if (row.getEast() == row) {
            row.setEast(new DataNode<>(null, row, i, j, new LivingCell()));
        } else {
            Node data = row.east;
            while (data.getEast() != row)
                data = data.east;
            data.setEast(new DataNode<>(null, row, i, j, new LivingCell()));
        }
        SentinelLL.SentinelNode column = this.matrix.getSentinel(j);
        if (column.south == column) {
            DataNode<L> result = getElement(i, j);
            column.setSouth(result);
            result.setSouth(column);
        } else {
            Node data = column.south;
            while (data.getSouth() != column)
                data = data.south;
            DataNode<L> result = getElement(i, j);
            data.setSouth(result);
            result.setSouth(column);
        }
    }

    /***
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @SuppressWarnings("unchecked")
    public SparseMatrix<L> nextGeneration() throws CloneNotSupportedException {
        SparseMatrix<L> next = (SparseMatrix<L>) this.clone();
        for (int i = this.startingRow - 1; i < this.rows + 1; i++) {
            for (int j = this.startingColumn - 1; j < this.columns + 1; j++) {
                int neighbours = this.getNeighbours(i, j);
                if (this.getElement(i, j) == null && neighbours == 3) {
                    next.resize(i, j);
                    next.setElement(i, j, (L) "add");
                } else if ((this.getElement(i, j) != null) && (neighbours < 2 || neighbours > 3)) {
                    next.setElement(i, j, (L) "delete");
                }
            }
        }
        return next;
    }

    @SuppressWarnings("unchecked")
    public void showNextGenerations(int generations) throws CloneNotSupportedException {
        SparseMatrix<L> output = (SparseMatrix<L>) this.clone();
        for (int i = 0; i < generations - 1; i++) {
            output = output.nextGeneration();
            System.out.println(output);
        }
        System.out.print(output.nextGeneration());
    }


    /***
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @SuppressWarnings("unchecked")
    protected Object clone() throws CloneNotSupportedException {
        SparseMatrix<L> newSparse = (SparseMatrix<L>) super.clone();
        newSparse.setMatrix();
        for (int i = this.startingRow; i < this.rows; i++) {
            for (int j = this.startingColumn; j < this.columns; j++) {
                if (this.getElement(i, j) != null) {
                    newSparse.setElement(i, j, (L) "add");
                }
            }
        }
        return newSparse;
    }

    /***
     *
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = this.startingRow; i < this.rows; i++) {
            for (int j = this.startingColumn; j < this.columns; j++) {
                if (this.getElement(i, j) == null)
                    result = result.concat("0");
                else if (this.getElement(i, j).getClass() == DataNode.class) {
                    result = result.concat("1");
                }
            }
            result = result.concat("\n");
        }
        return result;
    }
}