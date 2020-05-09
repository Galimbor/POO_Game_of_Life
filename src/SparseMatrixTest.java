import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SparseMatrixTest {


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


    @Test
    public void testGetElement0() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getElement(1, 1).getClass(), DataNode.class);
    }

    @Test
    public void testGetElement1() throws SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertNull(test.getElement(1, 2));
        assertNull(test.getElement(2, 2));
        assertNotNull(test.getElement(0, 1));
        assertNotNull(test.getElement(1, 0));
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

    @Test
    public void testGetNeighbours0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getNeighbours(1, 0), 5);
    }

    @Test
    public void testGetNeighbours1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getNeighbours(1, 3), 1);
    }

    @Test
    public void testGetNeighbours2() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getNeighbours(-1, 1), 3);
    }

    @Test
    public void testGetNeighbours3() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getNeighbours(0, 1), 3);
    }

    @Test
    public void testGetNeighbours4() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.getNeighbours(0, 1), 0);
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
        assertEquals(test.getRows(), 3);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getColumns(), 3);
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
        assertEquals(test.getRows(), 3);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getColumns(), 4);
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
        assertEquals(test.getRows(), 3);
        assertEquals(test.getStartingColumn(), -1);
        assertEquals(test.getColumns(), 3);
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
        assertEquals(test.getRows(), 4);
        assertEquals(test.getStartingColumn(), 0);
        assertEquals(test.getColumns(), 3);
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
        assertEquals(test.getRows(), 4);
        assertEquals(test.getStartingColumn(), -1);
        assertEquals(test.getColumns(), 4);
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

    @Test(expected = SparseMatrixException.class)
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
        assertEquals(test.getElement(1, 1).getClass(), DataNode.class);
        assertEquals(test.getElement(1, 1).getValue().getClass(), LivingCell.class);
    }

    @Test
    public void testAddDataNode1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("000");
        input.add("000");
        SparseMatrix<Double> test = new SparseMatrix<>(input);
        test.addDataNode(1, 1, 2.0);
        assertEquals(test.getElement(1, 1).getClass(), DataNode.class);
        assertEquals(test.getElement(1, 1).getValue().getClass(), Double.class);
    }


    @Test
    public void testNextGeneration0() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        SparseMatrix<Integer> nextTest = test.nextGeneration();
        assertEquals(nextTest.getRows(), 3);
        assertEquals(nextTest.getStartingRow(), -1);
        assertEquals(nextTest.getStartingColumn(), 0);
        assertEquals(nextTest.getColumns(), 3);
        assertNotNull(nextTest.getElement(-1, 1));
        assertNull(nextTest.getElement(-1, 0));
    }

    @Test
    public void testNextGeneration1() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        SparseMatrix<Integer> nextTest = test.nextGeneration();
        assertEquals(nextTest.getRows(), 2);
        assertEquals(nextTest.getStartingRow(), -1);
        assertEquals(nextTest.getStartingColumn(), 0);
        assertEquals(nextTest.getColumns(), 3);
        assertNotNull(nextTest.getElement(-1, 1));
        assertNull(nextTest.getElement(0, 0));
    }


    //generation == 0
    @Test(expected = SparseMatrixException.class)
    public void testShowNextGenerations0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        SparseMatrix<Integer> test = new SparseMatrix<>(0, 0, 2, 2);
        test.showNextGenerations(0);
    }

    //generation < 0
    @Test(expected = SparseMatrixException.class)
    public void testShowNextGenerations1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        SparseMatrix<LivingCell> test = new SparseMatrix<>(0, 0, 2, 2);
        test.showNextGenerations(-5);
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
        assertNotEquals(test.getElement(1, 1), clone.getElement(1, 1));
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
        assertNotEquals(test.getElement(1, 1), clone.getElement(1, 1));
        assertNull(clone.getElement(1, 1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testClone2() throws CloneNotSupportedException, SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        SparseMatrix<Integer> test = new SparseMatrix<>(input);
        SparseMatrix<Integer> clone = (SparseMatrix<Integer>) test.clone();
        clone.addDataNode(0, 0, 1);
        assertNotEquals(test.getElement(0, 0), clone.getElement(0, 0));
        assertNotEquals(clone.getElement(0, 0), null);
    }

    @Test
    public void testToStringInput0_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput0_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("111");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "010\n010\n010\n");
    }

    @Test
    public void testToStringInput0_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }


    @Test
    public void testToStringInput1_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        input.add("1");
        input.add("1");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput1_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("111");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "010\n010\n010\n");
    }

    @Test
    public void testToStringInput1_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput2_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "010\n110\n101\n000\n");
    }

    @Test
    public void testToStringInput2_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("110");
        input.add("101");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "110\n101\n100\n000\n");
    }

    @Test
    public void testToStringInput2_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("101");
        input.add("100");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "0110\n1100\n0010\n0000\n");
    }

    @Test
    public void testToStringInput2_3() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("0110");
        input.add("1100");
        input.add("0010");
        input.add("0000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "1110\n1000\n0100\n0000\n");
    }

    @Test
    public void testToStringInput3_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("101");
        input.add("000");
        input.add("101");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n000\n000\n");
    }

    @Test
    public void testToStringInput4_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("11");
        input.add("11");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "11\n11\n");
    }


    @Test
    public void testToStringInput5_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("001");
        input.add("110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "010\n001\n010\n");
    }

    @Test
    public void testToStringInput5_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("001");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n011\n000\n");
    }

    @Test
    public void testToStringInput5_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("011");
        input.add("000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n000\n000\n");
    }

    @Test
    public void testToStringInput6_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000000");
        input.add("001100");
        input.add("010000");
        input.add("001100");
        input.add("000000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000000\n001000\n010000\n001000\n000000\n");
    }

    @Test
    public void testToStringInput6_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000000");
        input.add("001000");
        input.add("010000");
        input.add("001000");
        input.add("000000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000000\n000000\n011000\n000000\n000000\n");
    }

    @Test
    public void testToStringInput6_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000000");
        input.add("000000");
        input.add("011000");
        input.add("000000");
        input.add("000000");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000000\n000000\n000000\n000000\n000000\n");
    }

    @Test
    public void testToStringInput7_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("001");
        input.add("111");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n101\n011\n010\n");
    }

    @Test
    public void testToStringInput7_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("101");
        input.add("011");
        input.add("010");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "000\n001\n101\n011\n");
    }

    @Test
    public void testToStringInput7_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("001");
        input.add("101");
        input.add("011");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "0000\n0100\n0011\n0110\n");
    }

    @Test
    public void testToStringInput7_3() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("0000");
        input.add("0100");
        input.add("0011");
        input.add("0110");
        SparseMatrix<LivingCell> test = new SparseMatrix<>(input);
        assertEquals(test.nextGeneration().toString(), "0000\n0010\n0001\n0111\n");
    }

}
