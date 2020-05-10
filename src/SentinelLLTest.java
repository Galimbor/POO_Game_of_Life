import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SentinelLLTest {

    @Test
    public void testGetSize() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        assertEquals(1, test.getSize());

        test.addLast(1);
        test.addLast(2);
        assertEquals(3, test.getSize());

        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        assertEquals(6, test.getSize());
    }

    @Test(expected = SentinelLLException.class)
    public void testGetSentinel0() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.getNode(1);
    }

    @Test(expected = SentinelLLException.class)
    public void testGetSentinel1() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.getNode(0);
    }

    @Test
    public void testGetSentinel2() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);
        test.addLast(10);
        test.addLast(11);

        SentinelLL.SentinelNode nodeReturned1 = test.getNode(0);
        assertEquals(0, nodeReturned1.getNumber());

        SentinelLL.SentinelNode nodeReturned2 = test.getNode(2);
        assertEquals(2, nodeReturned2.getNumber());

        SentinelLL.SentinelNode nodeReturned3 = test.getNode(10);
        assertEquals(10, nodeReturned3.getNumber());

        SentinelLL.SentinelNode nodeReturned4 = test.getNode(1);
        assertEquals(1, nodeReturned4.getNumber());
    }

    @Test
    public void testContains() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);
        test.addLast(10);
        test.addLast(11);

        assert (test.contains(0));
        assert (test.contains(11));
        assert (test.contains(10));
        assert (test.contains(2));

        assert (!test.contains(3));
        assert (!test.contains(4));
        assert (!test.contains(5));
        assert (!test.contains(12));

    }

    @Test(expected = SentinelLLException.class)
    public void testAddFirst0() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addFirst(0);
    }

    @Test
    public void testAddFirst1() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addFirst(3);
        assertEquals(3, test.getHead().getNumber());
        assertNotEquals(2, test.getHead().getNumber());

        test.addFirst(-2);
        assertEquals(-2, test.getHead().getNumber());

        test.addFirst(15);
        assertEquals(15, test.getHead().getNumber());
    }

    @Test
    public void testAddLast() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        assertEquals(0, test.getTail().getNumber());

        test.addLast(2);
        test.addFirst(1);
        test.addLast(3);
        assertEquals(3, test.getTail().getNumber());

        test.addLast(-2);
        assertEquals(-2, test.getTail().getNumber());
    }

    @Test(expected = SentinelLLException.class)
    public void testRemove0() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);
        test.remove(3);
    }

    @Test(expected = SentinelLLException.class)
    public void testRemove1() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);
        test.remove(-1);
    }

    @Test
    public void testRemove2() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addLast(2);
        test.addLast(3);

        test.remove(0);
        assertEquals(2, test.getSize());
        assertEquals(2, test.getHead().getNumber());

        test.remove(3);
        assertEquals(1, test.getSize());
        assertEquals(2, test.getTail().getNumber());

        test.addLast(3);
        test.addLast(4);
        test.remove(3);
        assertEquals(2, test.getHead().getNumber());
        assertEquals(4, test.getTail().getNumber());
    }

    /*
     *
     *
     * SentinelNode Class Test
     *
     *
     */

    @Test
    public void testGetNext() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.addLast(4);

        SentinelLL.SentinelNode node1 = test.getNode(0);
        assertEquals(1, node1.getNext().getNumber());

        SentinelLL.SentinelNode node2 = test.getNode(1);
        assertEquals(2, node2.getNext().getNumber());

        SentinelLL.SentinelNode node3 = test.getNode(3);
        assertEquals(4, node3.getNext().getNumber());

        SentinelLL.SentinelNode node4 = test.getNode(4);
        assertEquals(0, node4.getNext().getNumber());
    }

    @Test
    public void toString0() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        SentinelLL.SentinelNode node0 = test.getNode(0);
        String expected1 = "SentinelNode{ number = 0 }";
        assertEquals(expected1, node0.toString());

        test.addLast(10);
        SentinelLL.SentinelNode node1 = test.getNode(10);
        String expected2 = "SentinelNode{ number = 10 }";
        assertEquals(expected2, node1.toString());

        test.addLast(-2);
        SentinelLL.SentinelNode node2 = test.getNode(-2);
        String expected3 = "SentinelNode{ number = -2 }";
        assertEquals(expected3, node2.toString());
    }


}
