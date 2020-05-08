import org.junit.Test;

import static org.junit.Assert.*;

public class SparseMatrixTest {

    //rows <= 0
    @Test(expected = SparseMatrixException.class)
    public void testConstructor0() throws SparseMatrixException {
        new SparseMatrix<String>(0,0,-1,1);
    }

    //columns <= 0
    @Test(expected = SparseMatrixException.class)
    public void testConstructor1() throws SparseMatrixException {
        new SparseMatrix<String>(0,0,1,-1);
    }


    //rows <=0 && columns <= 0
    @Test(expected = SparseMatrixException.class)
    public void testConstructor2() throws SparseMatrixException {
        new SparseMatrix<String>(0,0,-1,-1);
    }

    //startingRow >= rows
    @Test(expected = SparseMatrixException.class)
    public void testConstructor3() throws SparseMatrixException {
        new SparseMatrix<String>(2,0,1,1);
    }

    //startingColumn >= columns
    @Test(expected = SparseMatrixException.class)
    public void testConstructor4() throws SparseMatrixException {
        new SparseMatrix<String>(0,2,1,1);
    }

}
