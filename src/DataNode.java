/***
 *
 * @param <T>
 */
public class DataNode<T> extends Node {

    private T value;
    private int i;
    private int j;

    /***
     *
     * @param south
     * @param east
     * @param i
     * @param j
     * @param value
     */
    public DataNode(Node south, Node east, int i, int j, T value) {
        super(south, east);
        setI(i);
        setJ(j);
        setValue(value);
    }

    /***
     *
     * @return
     */
    public T getValue() {
        return value;
    }

    /***
     *
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /***
     *
     * @param i
     */
    public void setI(int i) {
        this.i = i;
    }

    /***
     *
     * @param j
     */
    public void setJ(int j) {
        this.j = j;
    }

    /***
     *
     * @return
     */
    public int getI() {
        return i;
    }

    /***
     *
     * @return
     */
    public int getJ() {
        return j;
    }

    /***
     *
     * @param south
     */
    @Override
    public void setSouth(Node south) {
        super.setSouth(south);
    }

    /***
     *
     * @param east
     */
    @Override
    public void setEast(Node east) {
        super.setEast(east);
    }

    /***
     *
     * @return
     */
    @Override
    public String toString() {
        return "DataNode{" +
                "value=" + value +
                '}';
    }
}
