
public class SentinelLL {

    public SentinelNode head;
    public SentinelNode tail;
    int size;

    public SentinelLL()
    {
        this.head = null;
        this.tail = null;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }


    /**
     * Links e as last element of the list.
     */
    void linkLast(SentinelNode e) {
        final SentinelNode l = tail;
        tail = e;
        if (l == null)
            head = e;
        else {
            l.next = e;
            tail.next = head;
        }
        size++;
    }


    /**
     * Appends the specified SentinelNode to the end of this list.
     * @param node node to be appended to this list
     */

    public void add(SentinelNode node)
    {
        linkLast(node);
    }


    /**
     * Checks if the passed index is the index of an element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }


    /**
     * The same as isElementIndex.
     * Throws @IndexOutOfBoundsException() if index is is not an element index.
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException();
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index of the SentinelNode to return
     * @return the SentinelNode at the specified position in this list
     * @throws IndexOutOfBoundsException if index is not valid.
     */

    public SentinelNode get(int index) {
        checkElementIndex(index);
        return returnSentinelNode(index);
    }


    /**
     * Returns the (non-null) SentinelNodeNode at the specified element index.
     */
    SentinelNode returnSentinelNode(int index) {
        // assert isElementIndex(index);
        SentinelNode x = head;
            for (int i = 0; i < index; i++)
                x =  x.next;
            return x;

    }


    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index of the SentinelNode to replace
     * @param element to be stored at the specified position
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, SentinelNode element) {
        checkElementIndex(index);
        SentinelNode x = returnSentinelNode(index);
        x.east = element.south;
        x.south = element.south;
    }



    /*******************************************************
     *
     *  The  Sentinel Node class
     *
     ********************************************************/
    public static class SentinelNode extends Node {

        private SentinelNode next;
        private int number;

        public SentinelNode(Node south, Node east , SentinelNode next, int number) {
            super(south,east);
            this.next = next;
            this.number = number;
        }
    }

}
