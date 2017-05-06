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
 * The &lt;g&gt; element contains grammatical information
 */
public class G extends Element {
    private String second;
    private B child;

    G(String s) {
        setRaw(s);
        int paren = s.indexOf('(');
        if(paren > 0) {
            setText(Utils.trim(s.substring(0, paren)));
            this.second = Utils.trim(s.substring(paren+1));
        } else {
            setText();
        }
    }
    public boolean hasSecond() {
        return (second == null);
    }
    public String[] getSecond() {
        if(!hasSecond()) {
            return new String[]{""};
        }
        String[] ret = LabelMap.getPoS(second);
        if(ret == null) {
            return new String[]{""};
        } else {
             return ret;
        }
    }
    public static G fromNode(Node n) throws Exception {
        String txt;
        if(n.getNodeName().equals("g")) {
            txt = n.getFirstChild().getTextContent();
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
        G ret = new G(txt);
        return ret;
    }
    public void addB(B b) {
        this.child = b;
    }
    public void addB(Node n) throws Exception {
        B tmp = B.fromNode(n, true);
        this.child = tmp;
    }
}
