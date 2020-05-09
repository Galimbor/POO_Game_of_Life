import java.util.ArrayList;
import java.util.LinkedList;


public class Board {
    private SparseMatrix<LivingCell> boardGame;

    public Board(ArrayList<String> input) throws SparseMatrixException, SentinelLLException {
        setBoardGame(input);
    }

    public SparseMatrix<LivingCell> getBoardGame() {
        return boardGame;
    }

    private void setBoardGame(ArrayList<String> input) throws SentinelLLException, SparseMatrixException {
        this.boardGame = new SparseMatrix<LivingCell>(input);
    }

    private void setBoardGame(SparseMatrix<LivingCell> newGen) {
        this.boardGame = newGen;
    }

    public void displayBoard() {
        System.out.println(boardGame.toString());
    }

    public void displayGeneration(int i) throws CloneNotSupportedException, SentinelLLException, SparseMatrixException {
        showNextGenerations(i);
    }

    public boolean add(int i, int j) throws SentinelLLException {
        boardGame.resize(i, j);
        boardGame.addDataNode(i, j, new LivingCell());
        return true;
    }


    /***
     *
     * @param i
     * @param j
     * @return
     */
    private int getNeighbours(int i, int j, SparseMatrix<LivingCell> tempBoard) throws SentinelLLException {
        int neighbours = 0;
        int minRow = i < tempBoard.getStartingRow() ? tempBoard.getStartingRow() : i - 1;
        int maxRow = i > (tempBoard.getRows() - 1) ? (tempBoard.getRows() - 1) : i + 1;
        int minCol = j < tempBoard.getStartingColumn() ? tempBoard.getStartingColumn() : j - 1;
        int maxCol = j > (tempBoard.getColumns() - 1) ? (tempBoard.getColumns() - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++) {
                if (tempBoard.getMatrix().existsSentinelNumber(k) && tempBoard.getDataNode(k, m) != null)
                {
                    neighbours++;
                }
            }
        }
        if (tempBoard.getMatrix().existsSentinelNumber(i) && tempBoard.getDataNode(i, j) != null)
            neighbours--;

        return neighbours;
    }

    /***
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @SuppressWarnings("unchecked")
    public SparseMatrix<LivingCell> nextGeneration(SparseMatrix<LivingCell> tempBoard) throws CloneNotSupportedException,
            SparseMatrixException,
            SentinelLLException {
        SparseMatrix<LivingCell> next = (SparseMatrix<LivingCell>) tempBoard.clone();
        for (int i = tempBoard.getStartingRow() - 1; i < tempBoard.getRows() + 1; i++) {
            for (int j = tempBoard.getStartingColumn() - 1; j < tempBoard.getColumns() + 1; j++) {
                int neighbours = getNeighbours(i, j, tempBoard);
                if ((!tempBoard.getMatrix().existsSentinelNumber(i) || tempBoard.getDataNode(i, j) == null) && neighbours == 3) {
                    next.resize(i, j);
                    next.addDataNode(i, j, new LivingCell());
                } else if (tempBoard.getMatrix().existsSentinelNumber(i) && (tempBoard.getDataNode(i, j) != null) && (neighbours < 2 || neighbours > 3)) {
                    next.deleteDataNode(i, j);
                }
            }
        }
        setBoardGame(next);
        return next;
    }

    @SuppressWarnings("unchecked")
    private void showNextGenerations(int generations) throws CloneNotSupportedException, SparseMatrixException,
            SentinelLLException {
        if (generations <= 0)
            throw new SparseMatrixException("generations must be >= 0");
        SparseMatrix<LivingCell> output = (SparseMatrix<LivingCell>) boardGame.clone();
        for (int i = 0; i < generations - 1; i++) {
            output = nextGeneration(output);
            System.out.println(output.toString());
        }
        System.out.print(nextGeneration(output));
    }



}
