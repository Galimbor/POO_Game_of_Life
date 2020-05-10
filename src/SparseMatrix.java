import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 *
 * @param <L>
 */
public class SparseMatrix<L> implements IMatrix<L>, Cloneable {
    private SentinelLL matrix;
    private int endRow;
    private int endColumn;
    private int startingRow;
    private int startingColumn;

    /***
     *
     * @param input
     */
    public SparseMatrix(ArrayList<String> input) throws SparseMatrixException, SentinelLLException {
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i).split("").length != input.get(i + 1).split("").length)
                throw new SparseMatrixException("Matrix format is incorrect");
        }
        setEndRow(input.size());
        setEndColumn(input.get(0).split("").length);
        setStartingRow(0);
        setStartingColumn(0);
        setMatrix(input);
    }

    /**
     * @param startingRow
     * @param startingColumn
     * @param endRow
     * @param endColumn
     */
    public SparseMatrix(int startingRow, int startingColumn, int endRow, int endColumn) throws SparseMatrixException, SentinelLLException {
        setEndRow(endRow);
        setEndColumn(endColumn);
        setStartingColumn(startingColumn);
        setStartingRow(startingRow);
        setMatrix();
    }

    /***
     *
     */
    public void setMatrix() throws SentinelLLException {
        this.matrix = new SentinelLL();
        for (int i = Math.min(this.startingRow, startingColumn); i <= Math.max(this.endRow, this.endColumn); i++) {
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
            for (int i = 0; i < this.endRow + 1; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < this.endColumn + 1; j++) {
                    if (line[j].equals("1")) {
                        setElement(i, j, (L) new LivingCell());
                    }
                }
            }
        }
    }

    /***
     *
     * @param endRow
     */
    public void setEndRow(int endRow) throws SparseMatrixException {
        if (endRow <= 0) {
            throw new SparseMatrixException("rows <= 0");
        }
        this.endRow = endRow - 1;
    }

    /***
     *
     * @param column
     */
    public void setEndColumn(int column) throws SparseMatrixException {
        if (column <= 0) {
            throw new SparseMatrixException("columns <= 0");
        }
        this.endColumn = column - 1;
    }

    /***
     *
     * @param startingRow
     */
    public void setStartingRow(int startingRow) throws SparseMatrixException {
        if (startingRow > this.endRow)
            throw new SparseMatrixException("startingRow >= rows");
        this.startingRow = startingRow;
    }

    /***
     *
     * @param startingColumn
     */
    public void setStartingColumn(int startingColumn) throws SparseMatrixException {
        if (startingColumn > this.endColumn)
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
        return this.endRow - this.startingColumn + 1;
    }

    /***
     *
     * @return
     */
    @Override
    public int getColumns() {
        return this.endColumn - this.startingColumn + 1;
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
        if (getDataNode(i, j) == null) {
            addDataNode(i, j, value);
        } else {
            this.getDataNode(i, j).setValue(value);
        }
    }

    @SuppressWarnings("unchecked")
    public L getElement(int i, int j) throws SentinelLLException, SparseMatrixException {
        DataNode<L> result = getDataNode(i, j);
        if (result == null)
            throw new SparseMatrixException("Trying to get element, element does not exist");
        return result.getValue();
    }

    /***
     * mind the return null. Dont forget to explain
     * @param i
     * @param j
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataNode<L> getDataNode(int i, int j) throws SentinelLLException {
        if (!matrix.existsSentinelNumber(i)) {
            throw new SentinelLLException("Sentinel Node does not exist!");
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
            token = token.getSouth();
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
     */
    private void redefineBorders(int i, int j) {
        if (i < this.startingRow) {  //can only grow if in outerlay not further
            this.startingRow = i;
        } else if (i > this.endRow) { //can only grow if in outerlay not further
            this.endRow = i;
        }
        if (j < this.startingColumn) { //can only grow if in outerlay not further
            this.startingColumn = j;
        } else if (j > this.endColumn) { //can only grow if in outerlay not further
            this.endColumn = j;
        }
    }

    /*
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
    */


    //TODO
    private void addRemainingSentinels(int n, int m) {
        if (n > endRow) {
            for (int i = endRow + 1; i <= n; i++) {
                this.matrix.addLast(i);
            }
        } else if (n < startingRow) {
            for (int i = startingRow - 1; i >= n; i--) {
                this.matrix.addFirst(i);
            }
        }
        //TODO
        if (m > endColumn) {
            for (int i = endColumn + 1; i <= m; i++) {
                if (!this.matrix.existsSentinelNumber(i))
                    this.matrix.addLast(i);
            }
        } else if (m < startingColumn) {
            for (int i = startingColumn - 1; i >= m; i--) {
                if (!this.matrix.existsSentinelNumber(i))
                    this.matrix.addFirst(i);
            }
        }
    }

    /***
     *
     * @param i
     * @param j
     */
    @Override
    public void resize(int i, int j) throws SentinelLLException {
        addRemainingSentinels(i, j);
        redefineBorders(i, j);
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    /***
     *
     * @param i
     * @param j
     */
    @SuppressWarnings("unchecked")
    public void deleteDataNode(int i, int j) throws SparseMatrixException, SentinelLLException {
        DataNode<LivingCell> node = (DataNode<LivingCell>) getDataNode(i, j);
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
            DataNode<L> result = getDataNode(i, j);
            column.setSouth(result);
            result.setSouth(column);
        } else {
            Node data = column.south;
            while (data.getSouth() != column)
                data = data.south;
            DataNode<L> result = getDataNode(i, j);
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
    protected Object clone() throws CloneNotSupportedException {
        SparseMatrix<L> newSparse = (SparseMatrix<L>) super.clone();
        try {
            newSparse.setMatrix();
        } catch (SentinelLLException e) {
            e.printStackTrace();
        }
        for (int i = this.startingRow; i < this.endRow + 1; i++) {
            for (int j = this.startingColumn; j < this.endColumn + 1; j++) {
                try {
                    if (this.getDataNode(i, j) != null) {
                        newSparse.setElement(i, j, (L) new LivingCell()); //TODO
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
        for (int i = this.startingRow; i < this.endRow + 1; i++) {
            for (int j = this.startingColumn; j < this.endColumn + 1; j++) {
                try {
                    DataNode<L> element = getDataNode(i, j);
                    if (element == null)
                        result = result.concat("0");
                    else {
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


    /*******************************************************
     *                                                     *
     *              The Data Node class                    *
     *                                                     *
     *******************************************************/

    /***
     * Our DataNode Class holds information such as the int "i" and "j" that represents the position of the DataNode in
     * the sparse matrix, a generic type stored in "value" and store as well two more important information, which are
     * the Node "south" and "east". For these last two data members, we have the behavior that is the following:
     *  - "are references to either the next non zero data node or to a sentinel node. The E of the last data node in a
     *  given row points back to the sentinel node of its row. Similarly, S of the last data node in a given column
     *  points back to the sentinel node of its column." - Professor Joao Valente Oliveira.
     *  Where S and E indicates "south" and "east" respectively.
     * @param <L>
     */
    private static class DataNode<L> extends Node {

        private L value;
        private int i;
        private int j;

        /***
         * @param south
         * @param east
         * @param i
         * @param j
         * @param value
         */
        public DataNode(Node south, Node east, int i, int j, L value) {
            super(south, east);
            setI(i);
            setJ(j);
            setValue(value);
        }

        /***
         *
         * @return
         */
        public L getValue() {
            return value;
        }

        /***
         *
         * @param value
         */
        public void setValue(L value) {
            this.value = value;
        }

        /***
         *
         * @param i
         */
        public void setI(int i) {
            this.i = i;
        }

        /***
         *
         * @param j
         */
        public void setJ(int j) {
            this.j = j;
        }

        /***
         *
         * @return
         */
        public int getI() {
            return i;
        }

        /***
         *
         * @return
         */
        public int getJ() {
            return j;
        }

        /***
         *
         * @param south
         */
        @Override
        public void setSouth(Node south) {
            super.setSouth(south);
        }

        /***
         *
         * @param east
         */
        @Override
        public void setEast(Node east) {
            super.setEast(east);
        }

        /***
         *
         * @return
         */
        @Override
        public String toString() {
            return "DataNode{" +
                    "value=" + value +
                    '}';
        }
    }


}