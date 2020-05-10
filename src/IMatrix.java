import java.util.List;

/**
 * Interface IMatrix has some of must have methods for any kind of matrices.
 * @param <L> Indicates what type the matrix will be.
 */

public interface IMatrix<L> {

    /**
     * Set element "value" of generic type to the given position i,j.
     * @pre true.
     * @param i - Position i, in the matrix ("row wise").
     * @param j - Position j, in the matrix ("Column wise").
     * @param value - Value to be set on the given position.
     * @throws Exception if anything goes wrong
     */
    void setElement(int i, int j,L value) throws Exception;


    /**
     * Get the element on the given position i,j.
     * @pre true.
     * @param i - Position i, in the matrix ("row wise").
     * @param j - Position j, in the matrix ("Column wise").
     * @return Value of the given position.
     * @throws Exception if anything goes wrong
     */
    L getElement(int i, int j) throws Exception;

    /**
     * Get the number of rows in the matrix.
     * @pre true.
     * @return Number of rows, which is higher then or equal to 0.
     */
    int getRows();

    /**
     * Get the number of columns of the matrix.
     * @pre true.
     * @return Number of columns, which is higher then or equal to 0.
     */
    int getColumns();

    /**
     * Get a list that contains the values of the whole column.
     * @pre true.
     * @param column Represent which column it will get from.
     * @return List of values.
     * @throws Exception if contract is broken.
     */
    List<L> getWholeColumn(int column) throws Exception;

    /**
     * Get a list that contains the values of the whole row.
     * @pre true.
     * @param row Represent which row it will get from.
     * @return List of values.
     * @throws Exception if contract is broken.
     */
    List<L> getWholeRow(int row) throws Exception;

    /**
     * Resize the matrix to the given arguments.
     * @param i - Position i, in the matrix ("row wise").
     * @param j - Position j, in the matrix ("column wise").
     * @throws Exception if contract is broken.
     */
    void resize(int i, int j) throws Exception;

    /**
     * Converts the matrix to string.
     * @pre true
     * @return Representation of matrix.
     */
    String toString();
}
