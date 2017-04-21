package ie.tcd.slscs.itut.gramadanj;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

import junit.framework.TestCase;
import ie.tcd.slscs.itut.gramadanj.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class UtilsTest extends TestCase {
    public void testExpandParentheticalVariants() throws Exception {
        String testin = "colo(u)r";
        String[] testout = {"color", "colour"};
        String[] run = Utils.expandParentheticalVariants(testin);
        assertArrayEquals(testout, run);
    }

    public void testCleanTrailingPunctuation() throws Exception {
        final String ina = "thing,";
        final String inb = "thing";
        assertEquals(inb, Utils.cleanTrailingPunctuation(ina));
        assertEquals(inb, Utils.cleanTrailingPunctuation(inb));
    }

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
        assertEquals("truncálann", Utils.expandFGB("truncáil", "-álann"));
    }
}
