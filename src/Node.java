abstract class Node {
    protected Node south;
    protected Node east;

    public Node(Node south, Node east) {
        this.south = south;
        this.east = east;
    }

    public Node getSouth() {
        return south;
    }

    public Node getEast() {
        return east;
    }
}
