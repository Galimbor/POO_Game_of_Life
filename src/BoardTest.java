import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {


    @Test
    public void testGetNeighbours0() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        Board board = new Board(input);
        assertEquals(board.getNeighbours(1, 0), 5);
    }

    @Test
    public void testGetNeighbours1() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        Board board = new Board(input);
        assertEquals(board.getNeighbours(1, 3), 1);
    }

    @Test
    public void testGetNeighbours2() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        Board board = new Board(input);
        assertEquals(board.getNeighbours(-1, 1), 3);
    }

    @Test
    public void testGetNeighbours3() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("010");
        input.add("110");
        Board board = new Board(input);
        assertEquals(board.getNeighbours(0, 1), 3);
    }

    @Test
    public void testGetNeighbours4() throws SentinelLLException, SparseMatrixException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("000");
        input.add("110");
        Board board = new Board(input);
        assertEquals(board.getNeighbours(0, 1), 0);
    }

    @Test
    public void testNextGeneration0() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        Board test = new Board(input);
        SparseMatrix<LivingCell> nextTest = test.nextGeneration();
        assertEquals(nextTest.getRows(), 4);
        assertEquals(nextTest.getStartingRow(), -1);
        assertEquals(nextTest.getStartingColumn(), 0);
        assertEquals(nextTest.getColumns(), 3);
        assertNotNull(nextTest.getElement(-1, 1));
    }

    @Test
    public void testNextGeneration1() throws CloneNotSupportedException, SparseMatrixException, SentinelLLException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        Board test = new Board(input);
        SparseMatrix<LivingCell> nextTest = test.nextGeneration();
        assertEquals(nextTest.getRows(), 3);
        assertEquals(nextTest.getStartingRow(), -1);
        assertEquals(nextTest.getStartingColumn(), 0);
        assertEquals(nextTest.getColumns(), 3);
        assertNotNull(nextTest.getElement(-1, 1));
    }


    //generation == 0
    @Test(expected = BoardException.class)
    public void testShowNextGenerations0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException, BoardException {
        SparseMatrix<LivingCell> test = new SparseMatrix<>(0, 0, 2, 2);
        Board boardTest = new Board(test);
        boardTest.displayGenerations(0);
    }

    //generation < 0
    @Test(expected = BoardException.class)
    public void testShowNextGenerations1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException, BoardException {
        SparseMatrix<LivingCell> test = new SparseMatrix<>(0, 0, 2, 2);
        Board boardTest = new Board(test);
        boardTest.displayGenerations(-5);
    }

    @Test
    public void testToStringInput0_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput0_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("111");
        input.add("000");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "010\n010\n010\n");
    }

    @Test
    public void testToStringInput0_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }


    @Test
    public void testToStringInput1_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("1");
        input.add("1");
        input.add("1");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput1_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("111");
        input.add("000");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "010\n010\n010\n");
    }

    @Test
    public void testToStringInput1_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("010");
        input.add("010");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n111\n000\n");
    }

    @Test
    public void testToStringInput2_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("111");
        input.add("100");
        input.add("010");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "010\n110\n101\n000\n");
    }

    @Test
    public void testToStringInput2_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("110");
        input.add("101");
        input.add("000");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "110\n101\n100\n000\n");
    }

    @Test
    public void testToStringInput2_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("101");
        input.add("100");
        input.add("000");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "0110\n1100\n0010\n0000\n");
    }

    @Test
    public void testToStringInput2_3() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("0110");
        input.add("1100");
        input.add("0010");
        input.add("0000");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "1110\n1000\n0100\n0000\n");
    }

    @Test
    public void testToStringInput3_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("101");
        input.add("000");
        input.add("101");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n000\n000\n");
    }

    @Test
    public void testToStringInput4_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("11");
        input.add("11");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "11\n11\n");
    }


    @Test
    public void testToStringInput5_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("110");
        input.add("001");
        input.add("110");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "010\n001\n010\n");
    }

    @Test
    public void testToStringInput5_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("001");
        input.add("010");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n011\n000\n");
    }

    @Test
    public void testToStringInput5_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("011");
        input.add("000");
        Board test = new Board(input);
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
        Board test = new Board(input);
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
        Board test = new Board(input);
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
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000000\n000000\n000000\n000000\n000000\n");
    }

    @Test
    public void testToStringInput7_0() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("010");
        input.add("001");
        input.add("111");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n101\n011\n010\n");
    }

    @Test
    public void testToStringInput7_1() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("101");
        input.add("011");
        input.add("010");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "000\n001\n101\n011\n");
    }

    @Test
    public void testToStringInput7_2() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("000");
        input.add("001");
        input.add("101");
        input.add("011");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "0000\n0100\n0011\n0110\n");
    }

    @Test
    public void testToStringInput7_3() throws SentinelLLException, SparseMatrixException, CloneNotSupportedException {
        ArrayList<String> input = new ArrayList<>();
        input.add("0000");
        input.add("0100");
        input.add("0011");
        input.add("0110");
        Board test = new Board(input);
        assertEquals(test.nextGeneration().toString(), "0000\n0010\n0001\n0111\n");
    }
}
