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
        System.out.println(rows);
        this.columns = input.get(0).split("").length;
        this.matrix = new SentinelLL();
        for(int i = 0; i < Math.max(this.rows,this.columns); i++)
        {
            matrix.add(new SentinelLL.SentinelNode(i));
        }
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < columns; j++) {
                    if (line[j].equals("1"))
                    {
                        SentinelLL.SentinelNode row = matrix.get(i);
                        if(row.east == null)
                            row.setEast(new DataNode<LivingCell>(null,row,new LivingCell(i,j)));
                        else
                        {
                            Node data = row.getEast();
                            while(data.east != row )
                                data = data.east;
                            data.setEast(new DataNode<LivingCell>(null,row, new LivingCell(i,j)));
                        }

                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public int getNeighboors(int i, int j)
    {
        int neightboors = 0;
        SentinelLL.SentinelNode currentRow = matrix.get(i - 1);
        Node token = currentRow;
        DataNode<LivingCell> data = null;
        while(token.east != currentRow )
        {
            data = (DataNode<LivingCell>) token.east;
            if(data.getValue().getJ() == j)
                break;
        }

        return 0;
    }



    public SentinelLL getMatrix() {
        return matrix;
    }



    @Override
    public void setElement(int i, int j) {

    }

    @Override
    public Object getElement(int i, int j) {
        return null;
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
