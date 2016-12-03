package ie.tcd.slscs.itut.gramadanj.EID;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2015-2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import ie.tcd.slscs.itut.gramadanj.Utils;
import org.junit.Test;
import org.w3c.dom.Node;

import static org.junit.Assert.*;

public class TargetTest {
    @Test
    public void isNoun() throws Exception {

    }

    @Test
    public void isSimpleWord() throws Exception {

    }

    @Test
    public void hasOpenParen() throws Exception {

    }

    @Test
    public void hasCloseParen() throws Exception {

    }

    @Test
    public void nextContinues() throws Exception {

    }

    @Test
    public void isAmbiguous() throws Exception {

    }

    @Test
    public void fromNode() throws Exception {
        final String gender = "<trg>foo <noindex>(<label>m</label>)</noindex></trg>";
        final Node ngen = Utils.stringToNode(gender);
        final Target tgen = Target.fromNode(ngen);
        assertEquals("m", tgen.gender);
    }

}