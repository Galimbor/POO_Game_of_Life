import java.util.List;

public interface IMatrix<L> {

    void setElement(int i, int j, L value) throws Exception;

    L getElement(int i, int j) throws Exception;

    int getRows();

    int getColumns();

    List<L> getWholeColumn(int column) throws Exception;

    List<L> getWholeRow(int row) throws Exception;

    void resize(int i, int j) throws Exception;

    String toString();

}
