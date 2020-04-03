public class Cell {
    private int n;
    private int m;
    private int value;

    public Cell(int n, int m,int value) {
        this.n = n;
        this.m = m;
        this.value = value;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getValue() {
        return value;
    }

    private void live() {
        this.value = 1;
    }

    private void die() {
        this.value = 0;
    }
}
