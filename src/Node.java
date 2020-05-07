/***
 *
 */
abstract class Node {
    protected Node south;
    protected Node east;

    /***
     *
     * @param south
     * @param east
     */
    public Node(Node south, Node east) {
        this.south = south;
        this.east = east;
    }

    /***
     *
     */
    public Node() {

    }

    /**
     *
     * @param south
     */
    public void setSouth(Node south) {
        this.south = south;
    }

    /***
     *
     * @param east
     */
    public void setEast(Node east) {
        this.east = east;
    }

    /***
     *
     * @return
     */
    public Node getSouth() {
        return south;
    }

    /***
     *
     * @return
     */
    public Node getEast() {
        return east;
    }

    /***
     *
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
