package ie.tcd.slscs.itut.gramadanj.Conversion.FGB;
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

import org.w3c.dom.NodeList;

/*
i,b,trans
i,b, n -> equals b
i,b
i,b,b,(,o,trans
i,b,i,trans
i,b,i,b,trans

<i>Gan </i><b>~, </b><trans><r>unconditionally. </r></trans>
*/

/**
 * The &lt;i&gt; element contains the Irish side of an example.
 * Because I'm not allowed to use the examples, this just contains a function
 * to tell the &lt;entry&gt; reader to skip over the pieces of the example.
 * This is important, because the English side is written the same as a
 * regular translation, so not skipping would lead to the inclusion of an
 * incorrect translation.
 */
public class I extends Element {
    private static boolean eqname(NodeList nl, int i, String s) {
        return nl.item(i).getNodeName().equals(s);
    }
    public static int skipElements(NodeList nl, int start) {
        int ret = 0;
        int len = nl.getLength();
        if((start <= len + 3) && eqname(nl, start, "i")
           && eqname(nl, start + 1, "p")
           && nl.item(start + 1).getFirstChild().getTextContent().equals(", ")
           && eqname(nl, start + 2, "trans")) {
            return 3;
        }
        for (int i = start; i < nl.getLength(); i++) {
            if (eqname(nl, i, "i") || eqname(nl, i, "b")) {
                ret++;
            }
        }
        return ret;
    }
    // FIXME: consume the child <r>?
}
