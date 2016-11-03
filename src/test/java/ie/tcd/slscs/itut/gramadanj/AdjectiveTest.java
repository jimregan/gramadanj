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
import org.xml.sax.InputSource;

import java.io.StringReader;

public class AdjectiveTest extends TestCase {
    private static final String abarach
            = "<?xml version='1.0' encoding='utf-8'?>\n" +
                    "<adjective default=\"abarach\" declension=\"1\" disambig=\"\" isPre=\"False\">\n" +
                    "  <sgNom default=\"abarach\" />\n" +
                    "  <sgGenMasc default=\"abaraigh\" />\n" +
                    "  <sgGenFem default=\"abaraí\" />\n" +
                    "  <plNom default=\"abaracha\" />\n" +
                    "  <graded default=\"abaraí\" />\n" +
                    "</adjective>";
    private Adjective exp;
    public AdjectiveTest() {
        SingularInfo sgm = new SingularInfoSimple("abarach", "abaraigh");
        SingularInfo sgf = new SingularInfoSimple("abarach", "abaraí");
        exp = new Adjective(sgm, sgf, "abaracha", "abaraí");
        exp.declension = 1;
        exp.isPre = false;
    }
    public void testGetLemma() {
        assertEquals("abarach", exp.getLemma());
    }
    public void testLoadAdjective() {
        Adjective inp = new Adjective();
        try {
            inp.loadXML(new InputSource(new StringReader(abarach)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        assertEquals(exp, inp);
    }
}
