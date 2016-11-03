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

import static ie.tcd.slscs.itut.gramadanj.Opers.*;

public class OpersTest extends TestCase {
    public void testDemutate() throws Exception {
        assert(Demutate("bhfuil") == "fuil");
        assert(Demutate("mbó") == "bó");
        assert(Demutate("gcat") == "cat");
        assert(Demutate("ndiaidh") == "diaidh");
        assert(Demutate("tsamhradh") == "samhradh");
        assert(Demutate("dteach") == "teach");
        assert(Demutate("hasal") == "asal");
        assert(Demutate("n-asal") == "asal");
    }

    public void testMutate() throws Exception {

    }

    public void testBroaden() throws Exception {
        assert(Broaden("blaín") == "blaíon");
        assert(Broaden("blan") == "blan");
    }

    public void testDevoice() throws Exception {
        assert(Devoice("blasd") == "blast");
    }

    public void testEndsVowel() throws Exception {
        assert(EndsVowel("sneachta") == true);
        assert(EndsVowel("abair") == false);
    }

    public void testStartsVowel() throws Exception {
        assert(StartsVowel("abair") == true);
        assert(StartsVowel("sneachta") == false);
    }

    public void testUnduplicate() throws Exception {
        assertEquals("foo", Opers.Unduplicate("foo"));
        assertEquals("bar", Opers.Unduplicate("barr"));
        assertEquals("barn", Opers.Unduplicate("barn"));
    }
}