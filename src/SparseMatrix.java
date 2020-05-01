import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SparseMatrix implements IMatrix {

    private SentinelLL matrix;
    private int rows;
    private int columns;


    public SparseMatrix(ArrayList<String> input) {
        this.rows = input.size();
        this.columns = input.get(0).split("").length;
        this.matrix = new SentinelLL();
        for(int i = 0; i < Math.max(this.rows,this.columns); i++)
        {
            matrix.add(new SentinelLL.SentinelNode(i));
            matrix.get(i).setSouth(matrix.get(i));
            matrix.get(i).setEast(matrix.get(i));

        }
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < columns; j++) {
                    if (line[j].equals("1"))
                    {
                        SentinelLL.SentinelNode row = matrix.get(i);
                        if(row.east == row)
                            row.setEast(new DataNode<LivingCell>(null,row,new LivingCell(i,j)));
                        else
                        {
                            Node data = row.getEast();
                            while(data.east != row )
                                data = data.east;
                            data.setEast(new DataNode<LivingCell>(null,row, new LivingCell(i,j)));
                        }
                        SentinelLL.SentinelNode column = matrix.get(j);
                        if(column.south == column) {
                            DataNode result = (DataNode) getElement(i, j);
                            column.setSouth(result);
                            result.setSouth(column);
                        }
                        else
                        {
                            Node data = column.getSouth();
                            while(data.south != column)
                                data = data.south;
                            DataNode result = (DataNode) getElement(i,j);
                            data.setSouth(result);
                            result.setSouth(column);
                        }

                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public DataNode<LivingCell> getElement(int i, int j) {
        SentinelLL.SentinelNode currentRow = matrix.get(i);
        Node token = currentRow;
        DataNode<LivingCell> result = null;
        while(token.east != currentRow)
        {
            DataNode<LivingCell> data = (DataNode<LivingCell>) token.getEast();
            if(data.getValue().getJ() == j) {
                result = data;
                break;
            }
            token = token.east;
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    public int getNeighboors(int i, int j)
    {
        int neighbours = 0;
        int minRow = i== 0 ? 0 : i - 1;
        int maxRow = i == (this.rows - 1) ? (this.rows - 1) : i + 1;
        int minCol = j == 0 ? 0 : j - 1;
        int maxCol = j == (this.columns - 1) ? (this.columns - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++)
                if(getElement(k,m).getValue().getClass() == LivingCell.class) neighbours++;
        }

        if(getElement(i,j).getValue().getClass() == LivingCell.class) neighbours--;

        return neighbours;
    }



    public SentinelLL getMatrix() {
        return matrix;
    }



    @Override
    public void setElement(int i, int j) {

    }



    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public int getColumns() {
        return 0;
    }

    @Override
    public List getWholeColumn(int column) {
        return null;
    }

    @Override
    public List getWholeRow(int row) {
        return null;
    }

    @Override
    public void resize(int srcRPos, int srcCPos, int destRPos, int destCPos, int rows, int columns) {

    }
}
