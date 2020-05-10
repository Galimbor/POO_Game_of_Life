import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SMBoardTest {

    @Test (expected = SparseMatrixException.class)
    public void testContructor0() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        SMBoard test = new SMBoard(input);
    }

    @Test
    public void testContructor1() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        SparseMatrix<LivingCell> matrixTest= new SparseMatrix<>(input);
        SMBoard test = new SMBoard(matrixTest);
        assertEquals(test.getMatrix(),matrixTest);
    }

    @Test
    public void testAdd0() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        SMBoard test = new SMBoard(input);
        test.add(3,3);
        assertEquals(test.getMatrix().getElement(3,3).getClass(),LivingCell.class);
    }

    @Test
    public void testAdd1() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        SMBoard test = new SMBoard(input);
        test.add(20,20);
        assertEquals(test.getMatrix().getElement(20,20).getClass(),LivingCell.class);
    }

    @Test
    public void testAdd2() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("11");
        input.add("01");
        SMBoard test = new SMBoard(input);
        test.add(-2,-2);
        assertEquals(test.getMatrix().getElement(-2,-2).getClass(),LivingCell.class);
    }

    @Test
    public void testRemove0() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.remove(0,1);
        assertNull(test.getMatrix().getDataNode(0,1));
    }

    @Test (expected = SparseMatrixException.class)
    public void testRemove1() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        SMBoard test = new SMBoard(input);
        test.remove(1,1);
    }


    @Test (expected = SparseMatrixException.class)
    public void testRemove2() throws SparseMatrixException,SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        SMBoard test = new SMBoard(input);
        test.remove(0,0);
        test.getMatrix().getElement(0,0);
    }



    @Test
    public void testGetNeighbours0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SMBoard board = new SMBoard(input);
        assertEquals(board.getNeighbours(1, 0), 5);
    }

    @Test
    public void testGetNeighbours1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SMBoard board = new SMBoard(input);
        assertEquals(board.getNeighbours(1, 3), 1);
    }

    @Test
    public void testGetNeighbours2() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SMBoard board = new SMBoard(input);
        assertEquals(board.getNeighbours(-1, 1), 3);
    }

    @Test
    public void testGetNeighbours3() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        SMBoard board = new SMBoard(input);
        assertEquals(board.getNeighbours(0, 1), 3);
    }

    @Test
    public void testGetNeighbours4() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        SMBoard board = new SMBoard(input);
        assertEquals(board.getNeighbours(0, 1), 0);
    }

    @Test
    public void testNextGeneration0() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.getMatrix().getRows(), 4);
        assertEquals(test.getMatrix().getStartingRow(), -1);
        assertEquals(test.getMatrix().getStartingColumn(), 0);
        assertEquals(test.getMatrix().getColumns(), 3);
        assertNotNull(test.getMatrix().getElement(-1, 1));
    }

    @Test
    public void testNextGeneration1() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.getMatrix().getElement(1,1).getClass(),LivingCell.class);
        assertNull(test.getMatrix().getDataNode(0,0));
        assertEquals(test.getMatrix().getRows(), 3);
        assertEquals(test.getMatrix().getStartingRow(), -1);
        assertEquals(test.getMatrix().getStartingColumn(), 0);
        assertEquals(test.getMatrix().getColumns(), 3);
        assertNotNull(test.getMatrix().getElement(-1, 1));
    }


    //generation == 0
    @Test(expected = SMBoardException.class)
    public void testShowNextGenerations0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException, SMBoardException {
        SparseMatrix<LivingCell> test = new SparseMatrix<>(0, 0, 2, 2);
        SMBoard boardTest = new SMBoard(test);
        boardTest.displayGenerations(0);
    }

    //generation < 0
    @Test(expected = SMBoardException.class)
    public void testShowNextGenerations1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException, SMBoardException {
        SparseMatrix<LivingCell> test = new SparseMatrix<>(0, 0, 2, 2);
        SMBoard boardTest = new SMBoard(test);
        boardTest.displayGenerations(-5);
    }

    @Test
    public void testToStringInput0_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput0_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("111");
        input.add("000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "010\n010\n010\n");
    }

    @Test
    public void testToStringInput0_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n111\n000\n");
    }


    @Test
    public void testToStringInput1_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        input.add("1");
        input.add("1");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput1_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("111");
        input.add("000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "010\n010\n010\n");
    }

    @Test
    public void testToStringInput1_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput2_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "010\n110\n101\n000\n");
    }

    @Test
    public void testToStringInput2_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("110");
        input.add("101");
        input.add("000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "110\n101\n100\n000\n");
    }

    @Test
    public void testToStringInput2_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("101");
        input.add("100");
        input.add("000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "0110\n1100\n0010\n0000\n");
    }

    @Test
    public void testToStringInput2_3() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("0110");
        input.add("1100");
        input.add("0010");
        input.add("0000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "1110\n1000\n0100\n0000\n");
    }

    @Test
    public void testToStringInput3_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("101");
        input.add("000");
        input.add("101");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n000\n000\n");
    }

    @Test
    public void testToStringInput4_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("11");
        input.add("11");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "11\n11\n");
    }


    @Test
    public void testToStringInput5_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("001");
        input.add("110");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "010\n001\n010\n");
    }

    @Test
    public void testToStringInput5_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("001");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n011\n000\n");
    }

    @Test
    public void testToStringInput5_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("011");
        input.add("000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n000\n000\n");
    }

    @Test
    public void testToStringInput6_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000000");
        input.add("001100");
        input.add("010000");
        input.add("001100");
        input.add("000000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000000\n001000\n010000\n001000\n000000\n");
    }

    @Test
    public void testToStringInput6_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000000");
        input.add("001000");
        input.add("010000");
        input.add("001000");
        input.add("000000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000000\n000000\n011000\n000000\n000000\n");
    }

    @Test
    public void testToStringInput6_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000000");
        input.add("000000");
        input.add("011000");
        input.add("000000");
        input.add("000000");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000000\n000000\n000000\n000000\n000000\n");
    }

    @Test
    public void testToStringInput7_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("001");
        input.add("111");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n101\n011\n010\n");
    }

    @Test
    public void testToStringInput7_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("101");
        input.add("011");
        input.add("010");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "000\n001\n101\n011\n");
    }

    @Test
    public void testToStringInput7_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("001");
        input.add("101");
        input.add("011");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "0000\n0100\n0011\n0110\n");
    }

    @Test
    public void testToStringInput7_3() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("0000");
        input.add("0100");
        input.add("0011");
        input.add("0110");
        SMBoard test = new SMBoard(input);
        test.nextGeneration();
        assertEquals(test.toString(), "0000\n0010\n0001\n0111\n");
    }
}
