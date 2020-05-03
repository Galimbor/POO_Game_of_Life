public class DataNode<T> extends Node {

    private T value;

    public DataNode(Node south, Node east, T value) {
        super(south, east);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setSouth(Node south) {
        super.setSouth(south);
    }

    @Override
    public void setEast(Node east) {
        super.setEast(east);
    }

    @Override
    public String toString() {
        return "DataNode{" +
                "value=" + value +
                '}';
    }
}
