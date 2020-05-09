import java.util.ArrayList;


public class Board {
    private SparseMatrix<LivingCell> boardGame;

    public Board(ArrayList<String> input) throws SparseMatrixException, SentinelLLException {
        setBoardGame(input);
    }

    public Board(SparseMatrix<LivingCell> matrix) throws SparseMatrixException, SentinelLLException {
        setBoardGame(matrix);
    }

    public SparseMatrix<LivingCell> getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(ArrayList<String> input) throws SentinelLLException, SparseMatrixException {
        this.boardGame = new SparseMatrix<>(input);
    }


    public void setBoardGame(SparseMatrix<LivingCell> matrix) {
        this.boardGame = matrix;
    }

    public void displayBoard() {
        System.out.println(boardGame.toString());
    }

    @SuppressWarnings("unchecked")
    public void displayGenerations(int generations) throws CloneNotSupportedException, SentinelLLException, SparseMatrixException {
        if (generations <= 0)
            throw new SparseMatrixException("generations must be >= 0");
        for (int i = 0; i < generations - 1; i++) {
            nextGeneration();
            System.out.println(boardGame.toString());
        }
        System.out.print(nextGeneration());
    }


    public boolean add(int i, int j) throws SentinelLLException {
        boardGame.resize(i, j);
        boardGame.setElement(i, j, new LivingCell());
        return true;
    }

    public boolean remove(int i, int j) throws SentinelLLException, SparseMatrixException {
        boardGame.resize(i, j);
        if (boardGame.getElement(i, j) != null)
            boardGame.deleteDataNode(i, j);
        return true;
    }

    /***
     *
     * @param i
     * @param j
     * @return
     */
    public int getNeighbours(int i, int j) throws SentinelLLException {
        int neighbours = 0;
        int minRow = i < this.boardGame.getStartingRow() ? this.boardGame.getStartingRow() : i - 1;
        int maxRow = i > (this.boardGame.getRows() - 1) ? (this.boardGame.getRows() - 1) : i + 1;
        int minCol = j < this.boardGame.getStartingColumn() ? this.boardGame.getStartingColumn() : j - 1;
        int maxCol = j > (this.boardGame.getColumns() - 1) ? (this.boardGame.getColumns() - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++) {
                if (this.boardGame.getMatrix().existsSentinelNumber(k) && this.boardGame.getDataNode(k, m) != null) {
                    neighbours++;
                }
            }
        }
        if (this.boardGame.getMatrix().existsSentinelNumber(i) && this.boardGame.getDataNode(i, j) != null)
            neighbours--;

        return neighbours;
    }

    /***
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @SuppressWarnings("unchecked")
    public SparseMatrix<LivingCell> nextGeneration() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        SparseMatrix<LivingCell> next = (SparseMatrix<LivingCell>) this.boardGame.clone();
        for (int i = this.boardGame.getStartingRow() - 1; i <= this.boardGame.getEndRow() + 1; i++) {
            for (int j = this.boardGame.getStartingColumn() - 1; j <= this.boardGame.getEndColumn() + 1; j++) {
                int neighbours = getNeighbours(i, j);
                if ((!this.boardGame.getMatrix().existsSentinelNumber(i) || this.boardGame.getDataNode(i, j) == null) && neighbours == 3) {
                    next.resize(i, j);
                    next.addDataNode(i, j, new LivingCell());
                } else if (this.boardGame.getMatrix().existsSentinelNumber(i) && (this.boardGame.getDataNode(i, j) != null) && (neighbours < 2 || neighbours > 3)) {
                    next.deleteDataNode(i, j);
                }
            }
        }

        setBoardGame(next);
        return next;
    }

}
