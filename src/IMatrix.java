import java.util.List;

public interface IMatrix<D,L> {

    public void setElement(int i, int j,L value) throws Exception;

    public D getElement(int i, int j) throws SentinelLLException;

    public int getRows();

    public int getColumns();

    public void setRows(int rows) throws Exception;

    public void setColumns(int columns) throws Exception;

    public List<L> getWholeColumn(int column) throws SentinelLLException;

    public List<L> getWholeRow(int row) throws SentinelLLException;

    public void resize(int i, int j) throws SentinelLLException;

    public String toString();

}
