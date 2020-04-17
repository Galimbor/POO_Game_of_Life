public class DataNode extends Node{

    private Cell value;

    public DataNode(Cell value, Node east, Node south)
    {
        super(south, east);
        this.value = value;
    }

    public Cell getValue() {
        return value;
    }
}
