import java.util.List;

public interface IMatrix<T> {

    public void setElement(int i, int j);

    public T getElement(int i, int j);

    public int getRows();

    public int getColumns();

    public List<T> getWholeColumn(int column);

    public List<T> getWholeRow(int row);

    public void resize(int rows, int columns);

    public String toString();

}
