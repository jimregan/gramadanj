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
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

// <a>S.a. </a><s>crann </s><n>6 </n>(<l>b</l>).<p> </p><b>3 = </b><s>tochardadh </s><n>2. </n>

/**
 * The &lt;a&gt; element contains a "see also" note.
 * The actual text of the node ought only to be "S.a." or "See",
 * but this node should reparent the following nodes that
 * "belong" to it:
 */
public class A extends Element {
    private String a;
    public class SeeAlsoEntry {
        String s;
        String x;
        public String getEntry() {
            if(this.x != null && !this.x.equals("")) {
                return this.s + "#" + this.x;
            } else {
                return this.s;
            }
        }
    }
    public class SeeAlsoRef {
        String s;
        String x;
        public String getEntry() {
            if(this.x != null && !this.x.equals("")) {
                return this.s + "#" + this.x;
            } else {
                return this.s;
            }
        }
    }
    A(String s) {
        this.a = a;
    }
    public static A fromNode(Node n) throws Exception {
        return new A("see also");
    }
    private static boolean eqname(NodeList nl, int i, String s) {
        return nl.item(i).getNodeName().equals(s);
    }
    public static int skipElements(NodeList nl, int start) {
        int ret = 0;
        for (int i = start; i < nl.getLength(); i++) {
            if (eqname(nl, i, "s") || eqname(nl, i, "x")) {
                ret++;
            }
        }
        return ret;
    }
    // FIXME: consume the child <r>?
}
