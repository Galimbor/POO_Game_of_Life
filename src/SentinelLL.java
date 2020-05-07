import java.util.Iterator;

/***
 *
 */
public class SentinelLL implements Iterable<SentinelLL.SentinelNode> {

    public SentinelNode head;
    public SentinelNode tail;
    int size;

    /***
     *
     */
    public SentinelLL() {
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
    private void linkFirst(SentinelNode e) {
        final SentinelNode f = head;
        e.next = f;
        head = e;
        tail.next = head;
        size++;
    }


    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    public void addFirst(SentinelNode e) {
        e.setEast(e);
        e.setSouth(e);
        linkFirst(e);
    }


    /**
     * Links e as last element of the list.
     */
    void linkLast(SentinelNode e) {
        final SentinelNode l = tail;
        tail = e;
        if (l == null) {
            head = e;
            e.next = e;
        } else {
            l.next = e;
            e.next = head;
        }
        size++;
    }


    /**
     * Appends the specified SentinelNode to the end of this list.
     *
     * @param node node to be appended to this list
     */

    public void add(SentinelNode node) {
        node.setSouth(node);
        node.setEast(node);
        linkLast(node);
    }



    /**
     * Returns the element at the specified position in this list.
     *
     * @param index of the SentinelNode to return
     * @return the SentinelNode at the specified position in this list
     * @throws IndexOutOfBoundsException if index is not valid.
     */

    public SentinelNode getSentinel(int index) {
        return returnSentinelNode(index);
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
    public void setSize(int size) {
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
     * Returns the (non-null) SentinelNodeNode at the specified element index.
     */
    SentinelNode returnSentinelNode(int index) {
        SentinelNode x = head;
        for (int i = 0; i < this.size; i++) {
            if (x.getNumber() == index)
                return x;
            x = x.next;
        }
       //TODO throw exception sentinel not found
        return null; //to erase
    }


    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   of the SentinelNode to replace
     * @param element to be stored at the specified position
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, SentinelNode element) {
        SentinelNode x = returnSentinelNode(index);
        x.east = element.south;
        x.south = element.south;
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
    public static class SentinelNode extends Node {

        /***
         *
         */
        private SentinelNode next;
        private int number;

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

        private SentinelLL current = self();

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
            SentinelNode result = current.getSentinel(nextIndex);
            nextIndex++;
            return result;
        }

        /***
         *
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /***
     *
     * @return
     */
    @Override
    public Iterator<SentinelNode> iterator() {
        return new SentinelLLIterator();
    }

}
