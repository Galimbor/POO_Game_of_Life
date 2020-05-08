import java.util.List;

public interface IMatrix<D,L> {

    public void setElement(int i, int j,L value) throws Exception;

    public D getElement(int i, int j);

    public int getRows();

    public int getColumns();

    public void setRows(int rows) throws Exception;

    public void setColumns(int columns) throws Exception;

    public List<D> getWholeColumn(int column);

    public List<D> getWholeRow(int row);

    public void resize(int i, int j);

    public String toString();

}
