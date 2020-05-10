import java.util.List;

public interface IMatrix<L> {

    public void setElement(int i, int j, L value) throws Exception;

    public L getElement(int i, int j) throws Exception;

    public int getRows();

    public int getColumns();

    public List<L> getWholeColumn(int column) throws Exception;

    public List<L> getWholeRow(int row) throws Exception;

    public void resize(int i, int j) throws Exception;

    public String toString();

}
