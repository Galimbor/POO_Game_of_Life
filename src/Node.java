abstract class Node {
    protected Node south;
    protected Node east;

    public Node(Node south, Node east) {
        this.south = south;
        this.east = east;
    }

    public Node() {

    }

    public void setSouth(Node south) {
        this.south = south;
    }

    public void setEast(Node east) {
        this.east = east;
    }

    public Node getSouth() {
        return south;
    }

    public Node getEast() {
        return east;
    }

    @Override
    public String toString() {
        return "Node{" +
                "south=" + south +
                ", east=" + east +
                '}';
    }

}
