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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The &lt;title&gt; element contains the name of the headword. It may be
 * followed by &lt;x&gt; containing the sense number of that headword.
 */
public class Title extends Element {
    private int sensekey = 0;
    private List<String> alttitles;
    private Replacement repl;
    Title() {
        alttitles = new ArrayList<String>();
    }
    Title(String s) {
        setRaw(s);
        String clean = Utils.cleanTrailingPunctuation(Utils.trim(s));
        if(clean.contains(",")) {
            String[] tmp = clean.split(",");
            for(int i = 0; i < tmp.length; i++) {
                String cur = Utils.trim(tmp[i]);
                if(i == 0) {
                    setText(cur);
                } else {
                    if(cur.charAt(0) == '~' || cur.charAt(0) == '-') {
                        alttitles.add(Utils.expandFGB(getText(), cur));
                    } else {
                        alttitles.add(cur);
                    }
                }
            }
        }
    }
    public void setX(Node n) throws Exception {
        X x = X.fromNode(n);
        if(x.get().length > 1) {
            throw new IOException("Element x contains more than one entry in " + getRaw());
        }
        this.sensekey = x.get()[0];
    }
    public static Title fromNode(Node n) throws Exception {
        String txt;
        if(n.getNodeName().equals("title")) {
            txt = n.getFirstChild().getTextContent();
        } else {
            throw new IOException("Unexpected node: " + n.getNodeName());
        }
        return new Title(txt);
    }
    public String getFullTitle() {
        if (this.sensekey > 0) {
            return getText() + '#' + this.sensekey;
        } else {
            return getText();
        }
    }
    public void setReplacement() {
        this.repl = FGBData.getReplacement(getFullTitle());
    }
    public Replacement getReplacement() {
        return this.repl;
    }
}
