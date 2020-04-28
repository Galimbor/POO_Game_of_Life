import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivingCell that = (LivingCell) o;
        return i == that.i &&
                j == that.j;
    }


}
