package ie.tcd.slscs.gramadanj;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormTest {

    @Test
    public void testEquals() {
        Form exp = new Form("test");
        Form inp = new Form("test");
        assertEquals(true, exp.equals(inp));
    }

    @Test
    public void testCompareTo() {
        Form a = new Form("a");
        Form b = new Form("b");
        assertEquals("a".compareTo("b"), a.compareTo(b));
        assertEquals("b".compareTo("a"), b.compareTo(a));
        assertEquals("a".compareTo("a"), a.compareTo(a));
    }
}
