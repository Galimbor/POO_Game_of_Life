public class SentinelNode extends Node {
    private Node next;

    public SentinelNode(Node south, Node east , Node next) {
        super(south,east);
        this.next = next;
    }
}
