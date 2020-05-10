/**
 * Interface IBoardGoL. Methods that will be needed for the board of game of life.
 * @param <M extends IMatrix> IBoardGoL accepts type where the IMatrix is the superior limit.
 */
public interface IBoardGoL<M extends IMatrix> {


    /**
     * Getter of the matrix.
     * @return matrix of type M.
     */
    M getMatrix();


    /**
     * Setter for the matrix
     * @param matrix Represents the board of game of life.
     */
    void setMatrix(M matrix);


    /**
     * Display the generation till the end generation that is given in the argument.
     * @param generations Represents the last generation to be displayed.
     * @throws Exception in case anything goes wrong.
     */
    void displayGenerations(int generations) throws Exception;

    /**
     * Add an element to the given position.
     * @param i     Represents the position i, in the sparse matrix ("row wise").
     * @param j     Represents the position j, in the sparse matrix ("column wise").
     * @return true if the operation was done successfully.
     * @throws Exception in case anything goes wrong.
     */
    boolean add(int i, int j) throws Exception;


    /**
     * Remove an element of the given position.
     * @param i     Represents the position i, in the sparse matrix ("row wise").
     * @param j     Represents the position j, in the sparse matrix ("column wise").
     * @return true if the operation was done successfully.
     * @throws Exception in case anything goes wrong.
     */
    boolean remove(int i, int j) throws Exception;


    /**
     * Get the numbers of living neighbours of a given cell.
     * @param i     Represents the position i, in the sparse matrix ("row wise").
     * @param j     Represents the position j, in the sparse matrix ("column wise").
     * @return true if the operation was done successfully.
     * @throws Exception in case anything goes wrong.
     */
    int getNeighbours(int i, int j) throws Exception;


    /**
     * Advance one generation in the game.
     * @throws Exception in case anything goes wrong.
     */
    void nextGeneration() throws Exception;

}
