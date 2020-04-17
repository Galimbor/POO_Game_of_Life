import java.util.List;

public interface IMatrix {

    public void setElement(int i, int j);

    public Integer getElement(int i, int j);

    public int getRows();

    public int getColumns();

    public List<Integer> getWholeColumn(int column);

    public List<Integer> getWholeRow(int row);

    public void resize(int rows, int columns);

    public String toString();

}
