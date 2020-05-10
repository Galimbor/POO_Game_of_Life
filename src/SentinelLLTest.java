import org.junit.Test;

public class SentinelLLTest {
//
//    @Test(expected = SentinelLLException.class)
//    public void testSetSize() throws SentinelLLException {
//        SentinelLL test = new SentinelLL();
//        test.setSize(-1);
//    }

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
    public void testGetSentinel2() {

    }


}
