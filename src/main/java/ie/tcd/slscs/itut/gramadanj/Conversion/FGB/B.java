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

import ie.tcd.slscs.itut.gramadanj.Utils;
import org.w3c.dom.Node;

/**
 * The &lt;b&gt; element when following &lt;g&gt; contains a word form, or
 * information from which to construct one: if the content begins with `~',
 * this indicates that the text that follows is appended to the headword
 * to yield the form specified in the preceding &lt;g&gt; element; if it
 * begins with `-', this indicates that the text is to be appended from
 * the last instance of the first character following `-': for example,
 * `-men' with the word `woman' would indicate that the plural is
 * `women'.
 * <p>
 * In other contexts, it can contain a subsense number, preceding a 
 * translation of that other sense of the word. In this use, especially if
 * following a "see also" node (or, more specifically, the &lt;s&gt; node
 * containing the word to see), it may have been mislabelled as &lt;n&gt;.
 * This can also happen where the keyword is a variant of another word.
 */
public class B extends Element {

    private boolean grammar;
    public boolean isGrammar() {
        return grammar;
    }
    public void setGrammar(boolean gram) {
        this.grammar = gram;
    }

    private boolean equals;
    public boolean isEquals() {
        return equals;
    }
    public void setEquals(boolean equals) {
        this.equals = equals;
    }

    private int number = 0;
    public boolean hasSenseNumber() {
        return number > 0;
    }
    public int getSenseNumber() {
        return this.number;
    }

    B(String s) {
        setRaw(s);
        String clean = Utils.cleanTrailingPunctuation(Utils.trim(s));
        if(clean.charAt(clean.length()-1) == '=') {
            this.equals = true;
            setText(Utils.trim(clean.substring(0, clean.length()-1)));
        } else {
            setText(clean);
        }
    }
    public void expandGrammar(String in) {
        if(grammar) {
            setText(Utils.expandFGB(in, getRaw()));
        }
    }
    public static B fromNode(Node n, boolean grammar, boolean mislabelled) throws Exception {
        String txt;
        if(n.getNodeName().equals("b") || (mislabelled && n.getNodeName().equals("n") )) {
            txt = n.getFirstChild().getTextContent();
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
        B ret = new B(txt);
        ret.setGrammar(grammar);
        return ret;
    }
}
