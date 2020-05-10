import java.util.ArrayList;

/**
 * Board of the game with the implementation of a Sparse Matrix.
 * The board has one field and that is the matrix.
 * The user will be able to resize the board, add and remove new cells and
 * simulate a game of "n" generations.
 */

public class SMBoard implements IBoardGoL<SparseMatrix<LivingCell>> {

    private SparseMatrix<LivingCell> matrix;

    /**
     * Constructor for the Board given an ArrayList as the argument. It will take the ArrayList of strings and create
     * the sparse matrix from it.
     *
     * @pre Each string of the array list must contain only "0" or "1".
     * @param input ArrayList of Strings.
     * @pos this.matrix is set.
     * @throws SentinelLLException   if there is no SentinelNode at the given position.
     * @throws SparseMatrixException if there is no DataNode at the given position.
     */
    public SMBoard(ArrayList<String> input) throws SparseMatrixException, SentinelLLException {
        setMatrix(input);
    }

    /**
     * Constructor for the Board with an sparse matrix as argument.
     * @pre true.
     * @param matrix SparseMatrix of living cells.
     * @ppos this.matrix is set.
     */
    public SMBoard(SparseMatrix<LivingCell> matrix) {
        setMatrix(matrix);
    }

    /**
     * Getter for the matrix field.
     * @return this.matrix.
     */
    public SparseMatrix<LivingCell> getMatrix() {
        return matrix;
    }

    /**
     * Setter for the matrix field.
     *
     * @param input ArrayList of strings
     * @pre Each string of the array list must contain only "0" or "1".
     * @pos this.matrix is set.
     * @throws SentinelLLException if there is no SentinelNode at the given position.
     * @throws SparseMatrixException if there is no DataNode at the given position.
     */
    public void setMatrix(ArrayList<String> input) throws SentinelLLException, SparseMatrixException {
        this.matrix = new SparseMatrix<>(input);
    }


    /**
     * Setter for the matrix field with a SparseMatrix as argument.
     *
     * @param matrix SparseMatrix of living cells
     * @pre true.
     * @pos this.matrix is set.
     */
    public void setMatrix(SparseMatrix<LivingCell> matrix) {
        this.matrix = matrix;
    }


    /**
     * Simulate and display "n" generations. Given a generation in the argument it will simulate and display all the
     * generations of the game until then.
     * @pre generations higher then or equal to 0
     * @param generations Represents the end generation.
     * @throws CloneNotSupportedException if it fails to create to create a deep copy of the matrix.
     * @throws SentinelLLException        if there is no SentinelNode at the given position.
     * @throws SparseMatrixException      if there is no DataNode at the given position.
     * @throws BoardException             if generations less than 0.
     */
    public void displayGenerations(int generations) throws CloneNotSupportedException, SentinelLLException, SparseMatrixException, SMBoardException {
        if (generations <= 0)
            throw new SMBoardException("Generations must be > 0");
        this.nextGeneration();
        for (int i = 0; i < generations - 1; i++) {
            System.out.println(this);
            this.nextGeneration();
        }
        System.out.print(this);
    }

    /**
     * Add a DataNode of living cell to the given position.
     *
     * @param i Represents the position i, in the sparse matrix ("row wise").
     * @param j Represents the position j, in the sparse matrix ("column wise").
     * @return true if the operation was done successfully.
     * @throws SentinelLLException if there is no SentinelNode at the given position.
     * @pre true
     */
    public boolean add(int i, int j) throws SentinelLLException {
        matrix.resize(i, j);
        matrix.setElement(i, j, new LivingCell());
        return true;
    }

    /**
     * Remove a DataNode of the given position.
     *
     * @param i Represents the position i, in the sparse matrix ("row wise").
     * @param j Represents the position j, in the sparse matrix ("column wise").
     * @return true if the operation was successfully.
     * @throws SentinelLLException   if there is no SentinelNode at the given position.
     * @throws SparseMatrixException if there is no DataNode at the given position.
     * @pre true
     */
    public boolean remove(int i, int j) throws SentinelLLException, SparseMatrixException {
        matrix.resize(i, j);
        if (matrix.getElement(i, j) != null)
            matrix.deleteDataNode(i, j);
        return true;
    }

    /***
     * Method is used to check the neighbours of a given position. It will return an int that represents how many
     * living neighbours a DataNode has. We will be calculating the superior and inferior limit so we dont have any
     * indexOutOfBound error.
     * @param i Represents the position i, in the sparse matrix ("row wise").
     * @param j Represents the position j, in the sparse matrix ("column wise").
     * @return How many living neighbours a DataNode has.
     */
    public int getNeighbours(int i, int j) throws SentinelLLException {
        int neighbours = 0;
        int minRow = i < this.matrix.getStartingRow() ? this.matrix.getStartingRow() : i - 1;
        int maxRow = i > (this.matrix.getEndRow()) ? (this.matrix.getEndRow()) : i + 1;
        int minCol = j < this.matrix.getStartingColumn() ? this.matrix.getStartingColumn() : j - 1;
        int maxCol = j > (this.matrix.getEndColumn()) ? (this.matrix.getEndColumn()) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++) {
                if (this.matrix.getSentinelLL().contains(k) && this.matrix.getDataNode(k, m) != null) {
                    neighbours++;
                }
            }
        }
        if (this.matrix.getSentinelLL().contains(i) && this.matrix.getDataNode(i, j) != null)
            neighbours--; //neighbours does not count for the datanode of the given position.

        return neighbours;
    }

    /***
     * Simulates the next generation of the game. Decides which DataNode must be deleted our added.
     *  - IF there is a DataNode and the number of living neighbours is 2 or 3, then the cell lives in
     *  the new generation, it will be deleted otherwise.
     *  - If there is not a DataNode and the number of living neighbours is 3 then will be added a new DataNode in the
     *  new generation, it remains without a DataNode otherwise.
     *  It will update the state of the matrix.
     * @throws CloneNotSupportedException if if fails to do the deep copy.
     * @throws SparseMatrixException if there is no DataNode at the given position.
     * @throws SentinelLLException if there is no SentinelNode at the given position.
     */
    @SuppressWarnings("unchecked")
    public void nextGeneration() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        SparseMatrix<LivingCell> next = (SparseMatrix<LivingCell>) this.matrix.clone();
        for (int i = this.matrix.getStartingRow() - 1; i <= this.matrix.getEndRow() + 1; i++) {
            for (int j = this.matrix.getStartingColumn() - 1; j <= this.matrix.getEndColumn() + 1; j++) {
                int neighbours = getNeighbours(i, j);
                if ((!this.matrix.getSentinelLL().contains(i) || this.matrix.getDataNode(i, j) == null) && neighbours == 3) {
                    next.resize(i, j);
                    next.addDataNode(i, j, new LivingCell());
                } else if (this.matrix.getSentinelLL().contains(i) && (this.matrix.getDataNode(i, j) != null) && (neighbours < 2 || neighbours > 3)) {
                    next.deleteDataNode(i, j);
                }
            }
        }

        setMatrix(next);
    }


    /**
     * Converts the board to a string representation.
     * @return String that represents the board composition much like as a matrix[rows][columns] with "0"s indicating
     * dead cells and "1"s living cells.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = this.getMatrix().getStartingRow(); i < this.getMatrix().getEndRow() + 1; i++) {
            for (int j = this.getMatrix().getStartingColumn(); j < this.getMatrix().getEndColumn() + 1; j++) {
                try {
                    Node element = this.getMatrix().getDataNode(i, j);
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
}

