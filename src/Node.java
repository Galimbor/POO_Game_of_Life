/***
 * Abstract and Super class of SentinelNode and DataNode. Setters and getters of south and east node will be "given"
 * to it's subclasses.
 */
abstract class Node {
    protected Node south;
    protected Node east;

    /***
     * Constructor for Node subclasses.
     * @pre true.
     * @param south - Represents next non-zero value (DataNode) or SentinelNode. ("column wise").
     * @param east - Represents next non-zero value (DataNode) or SentinelNode. ("row wise").
     * @pos Node south and Node east are set.
     */
    public Node(Node south, Node east) {
        setSouth(south);
        setEast(east);
    }

    /***
     * Constructor without arguments.
     * @pre true
     * @pos a instance of a Node subclass is initialized.
     */
    public Node() {

    }

    /**
     * Setter for south field.
     * @pre true.
     * @param south Points to the next non-zero value (DataNode) or SentinelNode. ("column wise").
     * @pos this.south = south.
     */
    public void setSouth(Node south) {
        this.south = south;
    }

    /***
     * Setter for east field.
     * @pre true.
     * @param east Points to the next non-zero value (DataNode) or SentinelNode. ("row wise").
     * @pos this.east = east.
     */
    public void setEast(Node east) {
        this.east = east;
    }

    /***
     * Getter for south field.
     * @pre true.
     * @return this.south
     */
    public Node getSouth() {
        return south;
    }

    /***
     * Getter for east field.
     * @pre true.
     * @return this.east.
     */
    public Node getEast() {
        return east;
    }

    /***
     * Converts the node to a string.
     * @return Representation of a node in a string.
     */
    @Override
    public String toString() {
        return "Node{" +
                "south=" + south +
                ", east=" + east +
                '}';
    }

}
