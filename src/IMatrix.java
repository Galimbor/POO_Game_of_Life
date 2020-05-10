import java.util.List;

/**
 * Interface IMatrix declares some "must have" methods for any kind of matrices.
 *
 * @param <L> Indicates what type the matrix will be.
 */


public interface IMatrix<L> {


    /**
     * Set element "value" of generic type to the given position i,j.
     *
     * @param i     - Position i, in the matrix ("row wise").
     * @param j     - Position j, in the matrix ("Column wise").
     * @param value - Value to be set on the given position.
     * @throws Exception if anything goes wrong
     * @pre true.
     */
    void setElement(int i, int j, L value) throws Exception;


    /**
     * Get the element on the given position i,j.
     *
     * @param i - Position i, in the matrix ("row wise").
     * @param j - Position j, in the matrix ("Column wise").
     * @return Value of the given position.
     * @throws Exception if anything goes wrong
     * @pre true.
     */
    L getElement(int i, int j) throws Exception;


    /**
     * Get the number of rows in the matrix.
     *
     * @return Number of rows, which is higher then or equal to 0.
     * @pre true.
     */
    int getRows();


    /**
     * Get the number of columns of the matrix.
     *
     * @return Number of columns, which is higher then or equal to 0.
     * @pre true.
     */
    int getColumns();

    /**
     * Get a list that contains the values of the whole column.
     *
     * @param column Represent which column it will get from.
     * @return List of values.
     * @throws Exception if contract is broken.
     * @pre true.
     */
    List<L> getWholeColumn(int column) throws Exception;


    /**
     * Get a list that contains the values of the whole row.
     *
     * @param row Represent which row it will get from.
     * @return List of values.
     * @throws Exception if contract is broken.
     * @pre true.
     */
    List<L> getWholeRow(int row) throws Exception;

    /**
     * Resize the matrix to the given arguments.
     *
     * @param i - Position i, in the matrix ("row wise").
     * @param j - Position j, in the matrix ("column wise").
     * @throws Exception if contract is broken.
     */
    void resize(int i, int j) throws Exception;

}
