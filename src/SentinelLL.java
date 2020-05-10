import java.util.Iterator;

/***
 * Data Structure named Sentinel Linked List. It is a circular linked list of Sentinel Nodes. Has the following fields:
 * - SentinelNode head: Represents the first SentinelNode of the current list
 * - SentinelNode tail: Represents the last SentinelNode of the current list
 * - size: Represents the actual size of the list
 */
public class SentinelLL {


    private SentinelNode head;
    private SentinelNode tail;
    private int size;

    /***
     * @pre true.
     * Constructor for the linked list.
     * @pos SentinelLL is initialized.
     */
    public SentinelLL() {

    }

    /***
     * Setter for the head field.
     * @pre true.
     * @param head SentinelNode that will be the head of the list.
     * @pos head field is set.
     */
    public void setHead(SentinelNode head) {
        this.head = head;
    }

    /***
     * Setter for the tail field.
     * @pre true.
     * @param tail SentinelNode that will be the tail of the list.
     * @pos tail field is set.
     */
    public void setTail(SentinelNode tail) {
        this.tail = tail;
    }


    /***
     * Getter for the head field.
     * @pre true.
     * @return this.head.
     */
    public SentinelNode getHead() {
        return head;
    }

    /***
     * Getter for the tail field.
     * @pre true.
     * @return this.tail.
     */
    public SentinelNode getTail() {
        return tail;
    }

    /***
     * Getter for the size property.
     * @pre true.
     * @return this.size
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns the element at the specified position in the list.
     *
     * @param number Number of the SentinelNode to be returned.
     * @return the SentinelNode at the specified position in this list.
     * @throws SentinelLLException if there is no SentinelNode at the given position.
     */

    public SentinelNode getNode(int number) throws SentinelLLException {
        return returnNode(number);
    }


    /**
     * Returns the (non-null) SentinelNodeNode at the specified element index.
     * @pre true
     * @param number Represents the index of which SentinelNode of the list it will return.
     * @return SentinelNode with the number that is passed as the argument.
     * @pre true
     */
    private SentinelNode returnNode(int number) throws SentinelLLException {
        SentinelNode x = head;
        for (int i = 0; i < this.size; i++) {
            if (x.getNumber() == number)
                return x;
            x = x.next;
        }
        throw new SentinelLLException("SentinelNode not found");
    }

    /**
     * Verify if there is or there is not a sentinel node with the given number.
     * @pre true.
     * @param number Represents the index of a SentinelNode
     * @return boolean that represents true or false, if exist or not a SentinelNode
     * @pre true
     */
    public boolean contains(int number) {
        SentinelNode x = head;
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            if (x.getNumber() == number) {
                result = true;
                break;
            }
            x = x.next;
        }
        return result;
    }


    /**
     * Link a new SentinelNode with the number "e" to the beginning of the list.
     * @pre size higher than or equal to 1.
     * @param e - Represents the number of the SentinelNode to be linked.
     * @pos size of list is increased.
     */
    //TODO review change  final SentinelNode f = head;
    private void linkFirst(int e) throws SentinelLLException {
        if (size < 1)
            throw new SentinelLLException("Trying to linkFirst with size < 1");
        SentinelLL.SentinelNode node = new SentinelNode(e);
        node.setEast(node);
        node.setSouth(node);
        node.next = head;
        head = node;
        tail.next = head;
        size++;
    }


    /**
     * Inserts a new SentinelNode in the beginning of the list. It uses the helper method "linkFirst".
     * @pre true.
     * @param e - Represents the number of the SentinelNode to be inserted.
     * @pos size of list is increased.
     * @throws SentinelLLException if the size of list is less than 1.
     */
    public void addFirst(int e) throws SentinelLLException {
        if (contains(e))
            throw new SentinelLLException("Trying to add SentinelNode, it already exists");
        linkFirst(e);
    }


    /**
     * Link a new SentinelNode with the number "e" to the end of the list.
     * @pre true.
     * @param e - Represents the number of the SentinelNode to be linked.
     */
    private void linkLast(int e) {
        final SentinelNode l = tail;
        SentinelLL.SentinelNode node = new SentinelNode(e);
        node.setSouth(node);
        node.setEast(node);
        tail = node;
        if (l == null) {
            head = node;
            node.next = node;
        } else {
            l.next = node;
            node.next = head;
        }
        size++;
    }

    /**
     * Inserts a new SentinelNode in the end of the list. It uses the helper method "linkLast".
     * @pre true.
     * @param e - Represents the number of the SentinelNode to be inserted.
     * @pos size of list is increased.
     */
    public void addLast(int e) throws SentinelLLException {
        if (contains(e))
            throw new SentinelLLException("Trying to add SentinelNode, it already exists");
        linkLast(e);
    }

    /**
     * Remove a SentinelNode of the list. It breaks the link of the given SentinelNode so the garbage collector can
     * safely remove the SentinelNode out of the memory.
     * @param e - Represents the number of the SentinelNode to be removed.
     * @throws SentinelLLException if there is no SentinelNode in the list.
     */
    public void remove(int e) throws SentinelLLException {
        if (!contains(e))
            throw new SentinelLLException("trying to delete, SentinelNode not found");
        SentinelNode x = head;
        while (x.getNext().number != e) {
            x = x.getNext();
        }
        if (x.next == getHead())
            setHead(getHead().next);
        if (x.next == getTail())
            setTail(x);
        x.next = x.getNext().next;
        size--;
    }

    /**
     * Return this Sentinel Linked List.
     *
     * @return this SentinelLL
     * @pre true
     */
    private SentinelLL self() {
        return this;
    }


    /*******************************************************
     *                                                     *
     *              The  Sentinel Node class               *
     *                                                     *
     *******************************************************/

    /***
     * Our SentinelNode Class the following information:
     * - number: which is the index of the SentinelNode,
     * - next: which is the next SentinelNode of the list, and the last one will point to the first, forming then a
     * circular linked list.
     *
     * Being a subclass of Node, it also holds the Node south and Node east.
     * Both of these Nodes can be explained with the following paragraph.
     *  - "are references to either the next non zero data node or to a sentinel node. The E of the last data node in a
     *  given row points back to the sentinel node of its row. Similarly, S of the last data node in a given column
     *  points back to the sentinel node of its column." - Professor Joao Valente Oliveira.
     *  Where S and E indicates "south" and "east" respectively.
     */
    public static class SentinelNode extends Node {

        private SentinelNode next;
        private final int number;

        /***
         * Constructor for the SentinelNode.
         * @pre true
         * @param number Represents the index of the SentinelNode.
         * @pos this.number = number.
         */
        public SentinelNode(int number) {
            this.number = number;
        }

        /***
         * Setter for east field. It uses the super class setter.
         * @pre true.
         * @param east Non-zero node.
         * @pos this.east = east.
         */
        @Override
        public void setEast(Node east) {
            super.setEast(east);
        }

        public SentinelNode getNext() {
            return next;
        }

        /***
         * Setter for the south field. It uses the super class setter.
         * @pre true.
         * @param south Non-zero node.
         * @pos this.south = south.
         */
        @Override
        public void setSouth(Node south) {
            super.setSouth(south);
        }


        /***
         * Setter for the next field.
         * @pre true.
         * @param next Non-zero node.
         * @pos this.next = next.
         */
        public void setNext(SentinelNode next) {
            this.next = next;
        }

        /***
         * Getter for the number field.
         * @return this.number.
         */
        public int getNumber() {
            return number;
        }

        /***
         * Converts SentinelNodes to a string representation.
         * @return Representation of the SentinelNode.
         */
        @Override
        public String toString() {
            return "SentinelNode{ " +
                    "number = " +
                    number +
                    " }";
        }
    }


    /***
     * Sentinel Linked List Iterator, class that implements the Iterator interface.
     * Has the following fields:
     * - current: Represents a instance of the SentinelLLIterator
     * - nextIndex: Represents the index of the next sentinel node in the list.
     */
    private class SentinelLLIterator implements Iterator<SentinelNode> {

        private final SentinelLL current = self();

        private int nextIndex = head.getNumber();

        /***
         * Check to see if there is a next Sentinel Node in the list, based on the current Sentinel.
         * @return true or false.
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        /***
         * Get the next SentinelNode in the list.
         * @return SentinelNode.F
         */
        @Override
        public SentinelNode next() {
            SentinelNode result = null;
            try {
                result = current.getNode(nextIndex);
            } catch (SentinelLLException e) {
                System.out.println(e.toString());
            }
            nextIndex++;
            return result;
        }

    }
}