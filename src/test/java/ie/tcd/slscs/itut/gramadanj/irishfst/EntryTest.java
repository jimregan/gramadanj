package ie.tcd.slscs.itut.gramadanj.irishfst;
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

import static org.junit.Assert.assertEquals;

public class EntryTest extends TestCase {
    private final String i1 = "Abbott+Prop+Noun+Masc+Gen+Sg+Len:Abbott";
    Entry e1 = new Entry();
    public EntryTest() {
        e1.surface = "Abbott";
        e1.lemma = "Abbott";
        e1.tags.add("+Prop");
        e1.tags.add("+Noun");
        e1.tags.add("+Masc");
        e1.tags.add("+Gen");
        e1.tags.add("+Sg");
        e1.tags.add("+Len");
    }
    public void testFromLine() {
        Entry out1 = new Entry();
        out1.fromDumpstringsLine(i1);
        assertEquals(e1.lemma, out1.lemma);
        assertEquals(e1.surface, out1.surface);
        assertEquals(e1.tags.size(), out1.tags.size());
        assertEquals(e1.tags.get(0), out1.tags.get(0));
        assertEquals(e1, out1);
    }
}
