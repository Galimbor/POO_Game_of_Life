/***
 * Abstract class. Super class of SentinelNode and DataNode. It has the constructor to set the Node south and Node
 * east.
 */
abstract class Node {
    protected Node south;
    protected Node east;

    /***
     * Constructor for Node subclasses.
     * @param south - Node that represents next non-zero value (DataNode) or SentinelNode. ("column wise")
     * @param east - Node that represents next non-zero value (DataNode) or SentinelNode. ("row wise")
     */
    public Node(Node south, Node east) {
        setSouth(south);
        setEast(east);
    }

    /***
     * Constructor without arguments.
     */
    public Node() {

    }

    /**
     * Setter for south field.
     * @pre true
     * @param south
     * @pos this.south = south
     */
    public void setSouth(Node south) {
        this.south = south;
    }

    /***
     * Setter for east field.
     * @pre true
     * @param east
     * @pos this.east = east
     */
    public void setEast(Node east) {
        this.east = east;
    }

    /***
     * Getter for south field.
     * @pre true
     * @return this.south
     */
    public Node getSouth() {
        return south;
    }

    /***
     * Getter for east field.
     * @pre true
     * @return this.east
     */
    public Node getEast() {
        return east;
    }

    /***
     * toString method of Node subclasses.
     * @return
     */
    @Override
    public String toString() {
        return "Node{" +
                "south=" + south +
                ", east=" + east +
                '}';
    }

}
