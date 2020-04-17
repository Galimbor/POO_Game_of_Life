public class LivingCell {

    private int i;
    private int j;

    public LivingCell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "LivingCell{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

}
