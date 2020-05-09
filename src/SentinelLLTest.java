import org.junit.Test;

import static org.junit.Assert.*;

public class SentinelLLTest {

    @Test(expected = SentinelLLException.class)
    public void testSetSize() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.setSize(-1);
    }

    @Test(expected = SentinelLLException.class)
    public void testGetSentinel0() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.addLast(0);
        test.getSentinel(1);
    }

    @Test(expected = SentinelLLException.class)
    public void testGetSentinel1() throws SentinelLLException {
        SentinelLL test = new SentinelLL();
        test.getSentinel(0);
    }

    @Test
    public void testGetSentinel2(){

    }



}
