public class Cell {

    private int i;

    private int j;
    private int value;

    public Cell(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public boolean isAlive()
    {
        return value != 0;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getValue() {
        return value;
    }


    public void live()
    {
        this.value = 1;
    }

    public void die()
    {
        this.value = 0;
    }
}
