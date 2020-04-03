public class Grid {
    private Cell[][] gridDisplay;
    private int n;
    private int m;

    public Grid(Cell[][] gridDisplay, int n, int m) {
        this.gridDisplay = gridDisplay;
        this.n = n;
        this.m = m;
    }

//    private int getNeighbour(Cell a) {
//        int result = 0;
//        for (int i = a.getN() - 1; i < a.getN() + 1; i++) {
//            for (int j = a.getM() - 1; j < a.getM() + 1; j++) {
//                if ((i > 0 && i < n) && (j > 0 && j < m)) {
//                    if (gridDisplay[i][j].getValue() == 1 && (i != a.getN() && j != a.getM())) {
//                        result++;
//                    }
//                }
//            }
//        }
//        return result;
//    }

    private void resize(int n, int m) {
        this.gridDisplay = new Cell[n][m];
    }

    private void show() {
        //todo
    }

    private void clear() {
        this.gridDisplay = new Cell[n][m];
    }
}
