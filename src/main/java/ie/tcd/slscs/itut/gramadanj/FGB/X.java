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
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The &lt;x&gt; element contains a sense number; it usually follows
 * either &lt;title&gt; to establish the sense of that entry, or
 * &lt;s&gt; which refers to a particular sense or number of senses.
 */
public class X {
    private String raw;
    private List<Integer> x;
    X() {
        x = new ArrayList<Integer>();
    }
    X(String in) {
        this.raw = in;
        String clean = Utils.cleanTrailingPunctuation(Utils.trim(in));
        String[] sp = in.split(",");
        for (String s : sp) {
            x.add(Integer.parseInt(s));
        }
    }
    int[] get() {
        int[] out = new int[x.size()];
        for(int i = 0; i < x.size(); i++) {
            out[i] = x.get(i);
        }
        return out;
    }
    String getRaw() {
        return raw;
    }

    /**
     * Sets the numeric content of the reference, first removing whitespace
     * and trailing punctuation.
     * @param in string to convert from
     */
    void set(String in) {
        String clean = Utils.cleanTrailingPunctuation(Utils.trim(in));
        String[] sp = in.split(",");
        for (String s : sp) {
            x.add(Integer.parseInt(s));
        }
    }
    public static X fromNode(Node n) throws Exception {
        String txt;
        if(n.getNodeName().equals("x")) {
            txt = n.getFirstChild().getTextContent();
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
        return new X(txt);
    }
}
