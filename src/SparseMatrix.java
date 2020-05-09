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
    public SparseMatrix(ArrayList<String> input) throws SparseMatrixException, SentinelLLException {
        setRows(input.size());
        setColumns(input.get(0).split("").length);
        setStartingRow(0);
        setStartingColumn(0);
        setMatrix(input);
    }

    /**
     * @param startingRow
     * @param startingColumn
     * @param rows
     * @param columns
     */
    public SparseMatrix(int startingRow, int startingColumn, int rows, int columns) throws SparseMatrixException, SentinelLLException {
        setRows(rows);
        setColumns(columns);
        setStartingColumn(startingColumn);
        setStartingRow(startingRow);
        setMatrix();
    }

    /***
     *
     */
    public void setMatrix() throws SentinelLLException {
        this.matrix = new SentinelLL();
        for (int i = Math.min(this.startingRow, startingColumn); i < Math.max(this.rows, this.columns); i++) {
            this.matrix.addLast(i);
        }
    }

    /***
     *
     * @param input
     */
    @SuppressWarnings("unchecked")
    public void setMatrix(ArrayList<String> input) throws SparseMatrixException, SentinelLLException {
        setMatrix();
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < this.rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < this.columns; j++) {
                    if (line[j].equals("1")) {
                        setElement(i, j, (L) new LivingCell());
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
    public void setRows(int rows) throws SparseMatrixException {
        if (rows <= 0) {
            throw new SparseMatrixException("rows <= 0");
        }
        this.rows = rows;
    }

    /***
     *
     * @param columns
     */
    @Override
    public void setColumns(int columns) throws SparseMatrixException {
        if (columns <= 0) {
            throw new SparseMatrixException("columns <= 0");
        }
        this.columns = columns;
    }

    /***
     *
     * @param startingRow
     */
    public void setStartingRow(int startingRow) throws SparseMatrixException {
        if (startingRow >= this.rows)
            throw new SparseMatrixException("startingRow >= rows");
        this.startingRow = startingRow;
    }

    /***
     *
     * @param startingColumn
     */
    public void setStartingColumn(int startingColumn) throws SparseMatrixException {
        if (startingColumn >= this.columns)
            throw new SparseMatrixException("startingColumn >= columns");
        this.startingColumn = startingColumn;
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


    @Override
    public void setElement(int i, int j, L value) throws SentinelLLException {
        if (getElement(i, j) == null) {
            addDataNode(i, j, value);
        } else {
            this.getElement(i, j).setValue(value);
        }
    }

    /***
     * mind the return null. Dont forget to explain
     * @param i
     * @param j
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public DataNode<L> getElement(int i, int j) throws SentinelLLException {
        if (!matrix.existsSentinelNumber(i)) {
            return null;
        }
        Node currentRow = this.matrix.getSentinel(i);
        Node token = this.matrix.getSentinel(i);
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
    public List<L> getWholeColumn(int column) throws SentinelLLException {
        ArrayList<L> result = new ArrayList<>();
        Node sentinel = matrix.getSentinel(column);
        Node token = sentinel;
        while (token.getSouth() != sentinel) {
            result.add((L) token.getSouth());
            token= token.getSouth();
        }
        return result;
    }

    /***
     *
     * @param row
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<L> getWholeRow(int row) throws SentinelLLException {
        ArrayList<L> result = new ArrayList<>();
        Node sentinel = matrix.getSentinel(row);
        Node token = sentinel;
        while (token.getEast() != sentinel) {
            result.add((L) token.getEast());
            token = token.getEast();
        }
        return result;
    }

    /***
     *
     * @param i
     * @param j
     * @return
     */
    public int getNeighbours(int i, int j) throws SentinelLLException {
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
        if (i == this.startingRow - 1) {  //can only grow if in outerlay not further
            --this.startingRow;
        } else if (i == this.rows) { //can only grow if in outerlay not further
            ++this.rows;
        }
        if (j == this.startingColumn - 1) { //can only grow if in outerlay not further
            --this.startingColumn;
        } else if (j == this.columns) { //can only grow if in outerlay not further
            ++this.columns;
        }
    }

    /***
     *
     * @param i
     * @param j
     */
    @Override
    public void resize(int i, int j) throws SentinelLLException {
        if (!this.matrix.existsSentinelNumber(i)) {
            this.matrix.addFirst(i);
        }
        if (!this.matrix.existsSentinelNumber(j)) {
            this.matrix.addLast(j);
        }
        //TODO maybe add Exception
        redefineBorders(i, j);
    }

    /***
     *
     * @param i
     * @param j
     */
    @SuppressWarnings("unchecked")
    public void deleteDataNode(int i, int j) throws SparseMatrixException, SentinelLLException {
        DataNode<LivingCell> node = (DataNode<LivingCell>) getElement(i, j);
        if (node == null)
            throw new SparseMatrixException("Trying to delete, DataNode not found");
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
    public void addDataNode(int i, int j, L content) throws SentinelLLException {
        Node row = this.matrix.getSentinel(i);
        if (row.getEast() == row) {
            row.setEast(new DataNode<>(null, row, i, j, content));
        } else {
            Node data = row.east;
            while (data.getEast() != row)
                data = data.east;
            data.setEast(new DataNode<>(null, row, i, j, content));
        }
        Node column = this.matrix.getSentinel(j);
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
    public SparseMatrix<L> nextGeneration() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        SparseMatrix<L> next = (SparseMatrix<L>) this.clone();
        for (int i = this.startingRow - 1; i < this.rows + 1; i++) {
            for (int j = this.startingColumn - 1; j < this.columns + 1; j++) {
                int neighbours = this.getNeighbours(i, j);
                if (this.getElement(i, j) == null && neighbours == 3) {
                    next.resize(i, j);
                    next.addDataNode(i, j, (L) new LivingCell());
                } else if ((this.getElement(i, j) != null) && (neighbours < 2 || neighbours > 3)) {
                    next.deleteDataNode(i, j);
                }
            }
        }
        return next;
    }

    @SuppressWarnings("unchecked")
    public void showNextGenerations(int generations) throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        if (generations <= 0)
            throw new SparseMatrixException("generations must be >= 0");
        SparseMatrix<L> output = (SparseMatrix<L>) this.clone();
        for (int i = 0; i < generations - 1; i++) {
            output = output.nextGeneration();
            System.out.println(output.toString());
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
        try {
            newSparse.setMatrix();
        } catch (SentinelLLException e) {
            e.printStackTrace();
        }
        for (int i = this.startingRow; i < this.rows; i++) {
            for (int j = this.startingColumn; j < this.columns; j++) {
                try {
                    if (this.getElement(i, j) != null) {
                        newSparse.setElement(i, j, (L) "add");
                    }
                } catch (SentinelLLException e) {
                    System.out.println(e.toString());
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
                try {
                    DataNode<L> element = getElement(i,j);
                    if (element == null)
                        result = result.concat("0");
                    else  {
                        result = result.concat("1");
                    }
                } catch (SentinelLLException e) {
                    e.printStackTrace();
                }
            }
            result = result.concat("\n");
        }
        return result;
    }
}