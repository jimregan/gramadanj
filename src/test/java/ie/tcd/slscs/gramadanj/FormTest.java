package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;

public class FormTest extends TestCase {

    public void testEquals() {
        Form exp = new Form("test");
        Form inp = new Form("test");
        assertEquals(true, exp.equals(inp));
    }

    public void testCompareTo() {
        Form a = new Form("a");
        Form b = new Form("b");
        assertEquals("a".compareTo("b"), a.compareTo(b));
        assertEquals("b".compareTo("a"), b.compareTo(a));
        assertEquals("a".compareTo("a"), a.compareTo(a));
    }
}
