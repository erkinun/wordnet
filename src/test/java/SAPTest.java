import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SAPTest {

    private SAP sap;

    @Before
    public void setup() {
        In in = new In("files/digraph1.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }

    @Test
    public void testLength() throws Exception {
        int length = sap.length(3, 11);

        Assert.assertEquals(4, length);
    }

    @Test
    public void testAncestor() throws Exception {
        int ancestor = sap.ancestor(3, 11);

        Assert.assertEquals(1, ancestor);
    }

    @Test
    public void testAncestor2() throws Exception {
        int ancestor = sap.ancestor(9, 12);

        Assert.assertEquals(5, ancestor);
    }
}