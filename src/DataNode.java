public class DataNode<T> extends Node{

    private T value;

    public DataNode(Node south, Node east, T value) {
        super(south, east);
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
