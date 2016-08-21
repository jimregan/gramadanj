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

    public void testEqualsListsWithForm() extends TestCase {
        List<Form> a = new ArrayList<Form>();
        List<Form> b = new ArrayList<Form>();
        a.add(new Form("bee"));
        a.add(new Form("cee"));
        a.add(new Form("ay"));
        b.add(new Form("cee"));
        b.add(new Form("ay"));
        b.add(new Form("bee"));
        assertEquals(true, Utils.equalLists(a, b));
    }
}
