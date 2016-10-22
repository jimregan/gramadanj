package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;
import ie.tcd.slscs.gramadanj.Utils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class UtilsTest extends TestCase {
    public void testEqualLists() {
        List<String> a = new ArrayList<String>();
        List<String> b = new ArrayList<String>();
        a.add("bee");
        a.add("cee");
        a.add("ay");
        b.add("cee");
        b.add("ay");
        b.add("bee");
        assertEquals(true, Utils.equalLists(a, b));
    }

    public void testEqualsListsWithForm() {
        List<Form> a = new ArrayList<Form>();
        List<Form> b = new ArrayList<Form>();
        Form aa = new Form("ay");
        Form ab = new Form("bee");
        Form ac = new Form("cee");
        Form ba = new Form("ay");
        Form bb = new Form("bee");
        Form bc = new Form("cee");
        a.add(ab);
        a.add(ac);
        a.add(aa);
        b.add(bc);
        b.add(ba);
        b.add(bb);
        assertEquals(true, aa.equals(ba));
        assertEquals(true, ab.equals(bb));
        assertEquals(true, ac.equals(bc));
        assertEquals(true, Utils.equalLists(a, b));
        b.add(new Form("dee"));
        assertEquals(false, Utils.equalLists(a, b));
        a.add(new Form("ee"));
        assertEquals(false, Utils.equalLists(a, b));
    }

    public void testTrim() {
        assertEquals("", Utils.trim("     "));
        assertEquals("aaa", Utils.trim("  aaa   "));
        assertEquals("aaa", Utils.trim("  aaa"));
        assertEquals("aaa", Utils.trim("aaa  "));
    }

    public void testExpandFGB() {
        assertEquals("endings", Utils.expandFGB("ending", "~s"));
        assertEquals("chasing", Utils.expandFGB("chase", "-sing"));
    }
}
