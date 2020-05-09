import java.util.Iterator;

/***
 *
 */
public class SentinelLL {

    private SentinelNode head;
    private SentinelNode tail;
    private int size;

    /***
     *
     */
    public SentinelLL() {

    }

    /***
     *
     * @param head
     */
    public void setHead(SentinelNode head) {
        this.head = head;
    }

    /***
     *
     * @param tail
     */
    public void setTail(SentinelNode tail) {
        this.tail = tail;
    }

    /***
     *
     * @param size
     */
    public void setSize(int size) throws SentinelLLException {
        if (size < 0) {
            throw new SentinelLLException("size < 0");
        }
        this.size = size;
    }

    /***
     *
     * @return
     */
    public SentinelNode getHead() {
        return head;
    }

    /***
     *
     * @return
     */
    public SentinelNode getTail() {
        return tail;
    }

    /***
     *
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param number of the SentinelNode to return
     * @return the SentinelNode at the specified position in this list
     * @throws IndexOutOfBoundsException if number is not valid.
     */

    public SentinelNode getSentinel(int number) throws SentinelLLException {
        return returnSentinelNode(number);
    }


    /**
     * Returns the (non-null) SentinelNodeNode at the specified element index.
     */
    private SentinelNode returnSentinelNode(int index) throws SentinelLLException {
        SentinelNode x = head;
        for (int i = 0; i < this.size; i++) {
            if (x.getNumber() == index)
                return x;
            x = x.next;
        }
        throw new SentinelLLException("SentinelNode not found");
    }


    public boolean existsSentinelNumber(int number) {
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
     * Links e as first element.
     * There needs to already be an element here. Might change this later.
     */
    //TODO review change  final SentinelNode f = head;
    private void linkFirst(int e) {
        SentinelLL.SentinelNode node = new SentinelNode(e);
        node.setEast(node);
        node.setSouth(node);
        node.next = head;
        head = node;
        tail.next = head;
        size++;
    }


    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    public void addFirst(int e) {
        linkFirst(e);
    }


    /**
     * Links e as last element of the list.
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

    public void addLast(int e) {
        linkLast(e);
    }

    public void remove(int e) throws SentinelLLException {
        if (!existsSentinelNumber(e))
            throw new SentinelLLException("trying to delete, SentinelNode not found");
        SentinelNode x = head;
        while (x.getNext().getNumber() != e) {
            x = x.getNext();
        }
        if (x.next == getHead())
            setHead(getHead().next);
        if (x.next == getTail())
            setTail(x);
        x.next = x.getNext().next;
    }


    private SentinelLL self() {
        return this;
    }


    /*******************************************************
     *                                                     *
     *              The  Sentinel Node class               *
     *                                                     *
     *******************************************************/

    /***
     *
     */
    private static class SentinelNode extends Node {

        /***
         *
         */
        private SentinelNode next;
        private final int number;

        /***
         *
         * @param number
         */
        public SentinelNode(int number) {
            this.number = number;
        }

        /***
         *
         * @param east
         */
        @Override
        public void setEast(Node east) {
            super.setEast(east);
        }

        public SentinelNode getNext() {
            return next;
        }

        /***
         *
         * @param south
         */
        @Override
        public void setSouth(Node south) {
            super.setSouth(south);
        }

        /***
         *
         * @param next
         */
        public void setNext(SentinelNode next) {
            this.next = next;
        }

        /***
         *
         * @return
         */
        public int getNumber() {
            return number;
        }

        /***
         *
         * @return
         */
        @Override
        public String toString() {
            return "SentinelNode{" +
                    "next=" + next +
                    ", number=" + number +
                    '}';
        }
    }

    /***
     *
     */
    private class SentinelLLIterator implements Iterator<SentinelNode> {

        private final SentinelLL current = self();

        private int nextIndex = head.getNumber();

        /***
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        /***
         *
         * @return
         */
        @Override
        public SentinelNode next() {
            SentinelNode result = null;
            try {
                result = current.getSentinel(nextIndex);
            } catch (SentinelLLException e) {
                System.out.println(e.toString());
            }
            nextIndex++;
            return result;
        }

    }
}
