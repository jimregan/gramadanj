package ie.tcd.slscs.itut.gramadanj.FGB;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2017 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2015-2017 Coláiste na Tríonóide, Baile Átha Cliath
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
import ie.tcd.slscs.itut.gramadanj.EID.LabelMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The &lt;r&gt; element serves a number of purposes:
 * <ul>
 *   <li>After &lt;a&gt;, it can contain the Roman numeral part of the "see
 *       also" reference. In this context, it can also contain "&amp;"
     </li>
     <li>Within &lt;trans&gt; it contains the text of a translation
     <li>Otherwise, it contains usage information -- FIXME: this is followed by other stuff!
 * <ul>
 */
public class R extends Element {
    R(String s) {
        setRaw(s);
        setText();
    }
    public static R fromNode(Node n) throws Exception {
        return new R("FIXME");
    }
    // FIXME: consume the child <r>?
}
