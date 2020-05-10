public interface IBoardGoL<M extends IMatrix> {


    M getMatrix();


    void setMatrix(M matrix);


    void displayBoard();


    void displayGenerations(int generations) throws Exception;


    boolean add(int i, int j) throws Exception;


    boolean remove(int i, int j) throws Exception;


    int getNeighbours(int i, int j) throws Exception;


    void nextGeneration() throws Exception;
}
