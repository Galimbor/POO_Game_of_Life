import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SparseMatrix<T> implements IMatrix<DataNode<T>> {

    private SentinelLL matrix;
    private int rows;
    private int columns;

    private int startingRow;
    private int startingColumn;



    public SparseMatrix(ArrayList<String> input) {
        this.startingColumn = 0;
        this.startingRow = 0;
        this.rows = input.size();
        this.columns = input.get(0).split("").length;
        this.matrix = new SentinelLL();
        for (int i = 0; i < Math.max(this.rows, this.columns); i++) {
            matrix.add(new SentinelLL.SentinelNode(i));
            matrix.get(i).setSouth(matrix.get(i));
            matrix.get(i).setEast(matrix.get(i));

        }
        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            for (int i = 0; i < rows; i++) {
                String[] line = iterator.next().split("");
                for (int j = 0; j < columns; j++) {
                    if (line[j].equals("1")) {
                        addDataNode(i, j);
                    }
                }
            }

        }
    }


    public SparseMatrix(int startingRow, int startingColumn, int rows, int columns) {
        this.startingColumn = startingColumn;
        this.startingRow = startingRow;
        this.rows = rows;
        this.columns = columns;
        this.matrix = new SentinelLL();
        for (int i = Math.min(this.startingRow,this.startingColumn); i < Math.max(this.rows, this.columns); i++) {
            matrix.add(new SentinelLL.SentinelNode(i));
            matrix.get(i).setSouth(matrix.get(i));
            matrix.get(i).setEast(matrix.get(i));
        }
    }


//    @SuppressWarnings("unchecked")
//    public Object clone() throws CloneNotSupportedException {
//        SparseMatrix<T> newSparse = (SparseMatrix<T>) super.clone();
//        newSparse.matrix = (SentinelLL) getMatrix().clone();
//        return newSparse;
//    }

    @SuppressWarnings("unchecked")
    @Override
    public DataNode<T> getElement(int i, int j) {
        //System.out.println("I'm trying to find " + i);
        if(!matrix.existsSentinelNumber(i)) return null;
        SentinelLL.SentinelNode currentRow = matrix.get(i);
        Node token = currentRow;
        DataNode<LivingCell> result = null;
        while (token.east != currentRow) {
            DataNode<LivingCell> data = (DataNode<LivingCell>) token.getEast();
            if (data.getValue().getJ() == j) {
                result = data;
                break;
            }
            token = token.east;
        }
        return (DataNode<T>) result;
    }

    @SuppressWarnings("unchecked")
    public void deleteDataNode(DataNode<T> dataNode) {
        DataNode<LivingCell> node = (DataNode<LivingCell>) dataNode;
        //        if (currentRow.east == currentRow) {
//            System.exit(0);
//        } //TODO throw Exception if DataNode is not found
        Node current = this.getMatrix().get(node.getValue().getI());
        while (current.getEast() != node) {
            current = current.getEast();
        }
        Node deleteNode = current.getEast();
        current.setEast(deleteNode.east);
    }

    @SuppressWarnings("unchecked")
    public void addDataNode(int i, int j) {
        SentinelLL.SentinelNode row = getMatrix().get(i);
        //System.out.println("My i is " + i + " and my sentinelNode is" + row.getNumber());
        if (row.getEast() == row) {
            //System.out.println("Adding one with" + i + " " + j);
            row.setEast(new DataNode<>(null, row, new LivingCell(i, j)));
        }
        else {
            //System.out.println("Adding on existing one with" + i + " " + j);
            //System.out.println(getElement(i,j));
            Node data = row.getEast();
            while (data.getEast() != row)
                data = data.east;
            data.setEast(new DataNode<>(null, row, new LivingCell(i, j)));
        }
        SentinelLL.SentinelNode column = getMatrix().get(j);
        if (column.south == column) {
            DataNode<T> result = getElement(i, j);
            column.setSouth(result);
            result.setSouth(column);
        } else {
            Node data = column.getSouth();
            while (data.getSouth() != column)
                data = data.getSouth();
            DataNode<T> result = getElement(i, j);
            data.setSouth(result);
            result.setSouth(column);
        }
    }

    public int getNeighbours(int i, int j) {
        //System.out.println("My coordinates are " + i + " " + j);
        int neighbours = 0;
        int minRow = i < startingRow ? startingRow : i - 1;
        int maxRow = i > (this.rows - 1) ? (this.rows - 1) : i + 1;
        int minCol = j < startingColumn ? startingColumn : j - 1;
        int maxCol = j > (this.columns - 1) ? (this.columns - 1) : j + 1;

        for (int k = minRow; k <= maxRow; k++) {
            for (int m = minCol; m <= maxCol; m++) {
                //System.out.println(k + " " + m);
                if (getElement(k, m) != null)
                    neighbours++;
            }
        }

        if (matrix.existsSentinelNumber(i) && getElement(i, j) != null)
            neighbours--;

        return neighbours;
    }

    private void redefineBorders(int i, int j)
    {
        if(i < this.startingRow  )
        {
            --this.startingRow;
        }
        if(i > this.rows - 1  )
        {
            ++this.rows;
        }
        if(j < this.startingColumn)
        {
            --this.startingColumn;
        }
        if( j > this.columns - 1  )
        {
            ++this.columns;
        }
    }

    private void resize(int i, int j)
    {
       // System.out.println("Number of startingrows is " + this.startingRow + " number of startingcolumns is " + this.startingColumn);
        //System.out.println("My position is " + i + " " + j + "and my rows/colums is " + this.rows + " " + this.columns );
        if( !this.matrix.existsSentinelNumber(i) )
        {
            this.matrix.addFirst(new SentinelLL.SentinelNode(i));
            this.matrix.get(i).setSouth(this.matrix.get(i));
            this.matrix.get(i).setEast(this.matrix.get(i));
        }
        if( !this.matrix.existsSentinelNumber(j) )
        {

            this.matrix.add(new SentinelLL.SentinelNode(j));
            this.matrix.get(j).setSouth(matrix.get(j));
            this.matrix.get(j).setEast(matrix.get(j));
        }
    }



    public SparseMatrix<T> nextGen() {
        SparseMatrix<T> next = new SparseMatrix<>(this.startingRow,this.startingColumn,this.rows, this.columns);
        for (int i = this.startingRow - 1; i < this.rows + 1; i++) {
            for (int j = this.startingColumn - 1; j < this.columns + 1; j++) {
                //System.out.println("My i is " + i + "and my j is "+ j);
                int neighbours = this.getNeighbours(i, j);
                //System.out.println("I'm stuck with ashamd" + i + " and " + j );
                if (this.getElement(i, j) == null && neighbours == 3) {
                    //System.out.println("Found 0 with " + neighbours);
                    next.redefineBorders(i,j);
                    next.resize(i,j);
                  //  System.out.println("I'm stuck between Resize");
                    next.addDataNode(i, j);
                   // System.out.println(next.getElement(i,j));
                    //System.out.println(next.getElement(1,-1));
                    //System.out.println(next.getElement(1,1));
                   // System.out.println("I'm stuck at DataNode");
                } else if ((this.getElement(i, j) != null) && (neighbours >= 2 && neighbours <= 3)) {
                   // System.out.println("Found 1 with " + neighbours);
                    //next.resize(i,j);
                    next.addDataNode(i, j);
                    //System.out.println(next.getElement(1,-1));
                    //System.out.println(next.getElement(1,1));
                } else {
                    //do nothing
                }
            }


        }
        //DataNode<LivingCell> test = (DataNode<LivingCell>) next.getMatrix().get(1).getEast().getEast();
        //System.out.println(test);
        //System.out.println(next.getMatrix().size);
       // System.out.println(this.matrix.existsSentinelNumber(1));
//        Iterator<SentinelLL.SentinelNode> test = next.getMatrix().iterator();
//        while (test.hasNext()) {
//            //test
//            SentinelLL.SentinelNode currentSentinel = test.next();
//            Node token = currentSentinel;
//            while (token.east != currentSentinel) {
//                System.out.print(" " + token.east + " ");
//                token = token.east;
//            }
//            System.out.println();
//        }
        //System.out.println("Testing: " + next.toString());
        //System.out.println("Valid point is " + next.getElement(1,-1));
        return next;
    }


    public SentinelLL getMatrix() {
        return this.matrix;
    }


    @Override
    public void setElement(int i, int j) {

    }


    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getColumns() {
        return this.columns;
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

    public int getStartingColumn() {
        return startingColumn;
    }

    public int getStartingRow() {
        return startingRow;
    }

    @Override
    public String toString() {
        String result = "";
       // System.out.println(getElement(1,-1));
       // System.out.println("In toString : Starting column is: " + this.startingColumn + "and Column is~: " + this.columns );
        for (int i = this.startingRow; i < this.getRows(); i++) {
            for (int j = this.startingColumn; j < this.getColumns(); j++) {
                //System.out.println(this.getElement(i,j));
                if (this.getElement(i, j) == null)
                    result = result.concat("0");
                else if (this.getElement(i, j).getClass() == DataNode.class) {
                    result = result.concat("1");
                } else { //do nothing
                }
            }
            result = result.concat("\n");
        }
        return result;
    }
}
