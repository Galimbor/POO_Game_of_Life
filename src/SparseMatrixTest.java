import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SparseMatrixTest {


    //invalid format
    @Test(expected = SparseMatrixException.class)
    public void testConstructorString0() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("10");
        input.add("000");
        new SparseMatrix<>(input);
    }


    //invalid format
    @Test(expected = SparseMatrixException.class)
    public void testConstructorString2() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("1");
        input.add("000");
        new SparseMatrix<>(input);
    }

    //rows <= 0
    @Test(expected = SparseMatrixException.class)
    public void testConstructor0() throws SparseMatrixException, SentinelLLException {
        new SparseMatrix<String>(0, 0, -1, 1);
    }

    //columns <= 0
    @Test(expected = SparseMatrixException.class)
    public void testConstructor1() throws SparseMatrixException, SentinelLLException {
        new SparseMatrix<String>(0, 0, 1, -1);
    }

    //rows <=0 && columns <= 0
    @Test(expected = SparseMatrixException.class)
    public void testConstructor2() throws SparseMatrixException, SentinelLLException {
        new SparseMatrix<String>(0, 0, -1, -1);
    }

    //startingRow >= rows
    @Test(expected = SparseMatrixException.class)
    public void testConstructor3() throws SparseMatrixException, SentinelLLException {
        new SparseMatrix<String>(2, 0, 1, 1);
    }

    //startingColumn >= columns
    @Test(expected = SparseMatrixException.class)
    public void testConstructor4() throws SparseMatrixException, SentinelLLException {
        new SparseMatrix<String>(0, 2, 1, 1);
    }


    @Test(expected = SparseMatrixException.class)
    public void testGetElementEx0() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.getElement(1, 2);
    }

    @Test(expected = SparseMatrixException.class)
    public void testGetElementEx1() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.getElement(1, 1);
    }


    @Test
    public void testGetElement0() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getElement(1, 1).getClass(), LivingCell.class);
        assertEquals(test.getElement(0, 0).getClass(), LivingCell.class);
    }

    @Test
    public void testGetElement1() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getElement(0, 1).getClass(), LivingCell.class);
        assertEquals(test.getElement(1, 0).getClass(), LivingCell.class);
    }

    @Test
    public void testGetWholeColum0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        ArrayList<LivingCell> column1 = (ArrayList<LivingCell>) test.getWholeColumn(1);
        assertEquals(column1.size(), 2);
    }

    @Test
    public void testGetWholeColum1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        ArrayList<LivingCell> column2 = (ArrayList<LivingCell>) test.getWholeColumn(2);
        assertEquals(column2.size(), 0);
    }

    @Test
    public void testGetWholeRow0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        ArrayList<LivingCell> row0 = (ArrayList<LivingCell>) test.getWholeRow(0);
        assertEquals(row0.size(), 3);
    }

    @Test
    public void testGetWholeRow1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("000");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        ArrayList<LivingCell> row1 = (ArrayList<LivingCell>) test.getWholeRow(1);
        assertEquals(row1.size(), 0);
    }


    @Test //resize 1 up
    public void testResize0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.resize(-1, 0);
        assertEquals(test.getStartingRow(), -1);
        assertEquals(test.getEndRow(), 2);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getEndColumn(), 2);
        assertEquals(test.getRows(),4);
        assertEquals(test.getColumns(),3);
    }

    @Test // resize 1 right
    public void testResize1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.resize(0, 3);
        assertEquals(test.getStartingRow(), 0);
        assertEquals(test.getEndRow(), 2);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getEndColumn(), 3);
        assertEquals(test.getRows(),3);
        assertEquals(test.getColumns(),4);
    }

    @Test //resize 1 left
    public void testResize2() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.resize(0, -1);
        assertEquals(test.getStartingRow(), 0);
        assertEquals(test.getEndRow(), 2);
        assertEquals(test.getStartingColumn(), -1);
        assertEquals(test.getEndColumn(), 2);
        assertEquals(test.getRows(),3);
        assertEquals(test.getColumns(),4);
    }

    @Test // resize 1 bottom
    public void testResize3() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.resize(3, 2);
        assertEquals(test.getStartingRow(), 0);
        assertEquals(test.getEndRow(), 3);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getEndColumn(), 2);
        assertEquals(test.getRows(),4);
        assertEquals(test.getColumns(),3);
    }


    @Test //resize 1 left 1 right 1 up and 1 bottom
    public void testResize4() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.resize(-1, 0);
        test.resize(3, -1);
        test.resize(2, 3);
        assertEquals(test.getStartingRow(), -1);
        assertEquals(test.getEndRow(), 3);
        assertEquals(test.getStartingColumn(), -1);
        assertEquals(test.getEndColumn(), 3);
        assertEquals(test.getRows(),5);
        assertEquals(test.getColumns(),5);
    }

    @Test //does not resize
    public void testResize5() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.resize(0, 0);
        assertEquals(test.getStartingRow(), 0);
        assertEquals(test.getRows(), 3);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getColumns(), 3);
    }


    @Test(expected = SparseMatrixException.class)
    public void testDeleteDataNode3() throws SparseMatrixException, SentinelLLException {
        SparseMatrix<Integer> test = new SparseMatrix<>(0, 0, 2, 2);
        test.deleteDataNode(0, 0);
    }

    @Test(expected = SentinelLLException.class)
    public void testDeleteDataNode1() throws SparseMatrixException, SentinelLLException {
        SparseMatrix<Integer> test = new SparseMatrix<>(0, 0, 2, 2);
        test.deleteDataNode(3, 3);
    }

    @Test(expected = SparseMatrixException.class)
    public void testDeleteDataNodeInput0() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("101");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        test.deleteDataNode(0, 1);
    }

    @Test(expected = SparseMatrixException.class)
    public void testDeleteDataNodeInput1() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1111");
        input.add("1010");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        test.deleteDataNode(1, 3);
    }

    @Test(expected = SparseMatrixException.class)
    public void testDeleteDataNodeInput2() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1111");
        input.add("1010");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        test.deleteDataNode(1, 1);
    }

    @Test
    public void testAddDataNode0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("000");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        test.addDataNode(1, 1, new LivingCell());
        assertEquals(test.getElement(1, 1).getClass(), LivingCell.class);
    }

    @Test
    public void testAddDataNode1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("000");
        input.add("000");
        SparseMatrix<Double> test = new SparseMatrix<>(input);
        test.addDataNode(1, 1, 2.0);
        assertEquals(test.getElement(1, 1).getClass(), Double.class);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void testClone0() throws CloneNotSupportedException, SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        SparseMatrix<Integer> clone = (SparseMatrix<Integer>) test.clone();
        clone.addDataNode(1, 1, 1);
        assertNotEquals(test.getDataNode(1, 1), clone.getDataNode(1, 1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testClone1() throws CloneNotSupportedException, SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("11");
        input.add("11");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        SparseMatrix<Integer> clone = (SparseMatrix<Integer>) test.clone();
        clone.deleteDataNode(1, 1);
        assertNotEquals(test.getDataNode(1, 1), clone.getDataNode(1, 1));
        assertNull(clone.getDataNode(1, 1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testClone2() throws CloneNotSupportedException, SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        SparseMatrix<Integer> clone = (SparseMatrix<Integer>) test.clone();
        clone.addDataNode(0, 0, 1);
        assertNotEquals(test.getDataNode(0, 0), clone.getDataNode(0, 0));
        assertNotEquals(clone.getDataNode(0, 0), null);
    }

    @Test
    public void testToString0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("000");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.toString(),"");
    }

    @Test
    public void testToString1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("010");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        assertEquals(test.toString(),"LivingCell on position 0,0\n" +
                "LivingCell on position 0,1\n" +
                "LivingCell on position 1,1\n" +
                "LivingCell on position 2,1\n");
    }

    @Test
    public void testToString2() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("101");
        input.add("010");
        SparseMatrix<Double> test = new SparseMatrix<>(input);
        assertEquals(test.toString(),"LivingCell on position 1,0\n" +
                "LivingCell on position 0,1\n" +
                "LivingCell on position 2,1\n");
    }

    @Test
    public void testToString3() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("01");
        input.add("10");
        SparseMatrix<String> test = new SparseMatrix<>(input);
        assertEquals(test.toString(),"LivingCell on position 1,0\n");
    }
}
