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
import ie.tcd.slscs.itut.gramadanj.Conversion.EID.LabelMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * The &lt;g&gt; element contains grammatical information.
 * It is usually followed by a &lt;b&gt; element, which usually contains
 * the grammatical form referred to in &lt;g&gt;
 *
 */
public class G extends Element {
    private String second;
    private Map<String, String> children;

    G() {
        this.children = new HashMap<String, String>();
    }
    G(String s) {
        this();
        setRaw(s);
        int paren = s.indexOf('(');
        if(paren > 0) {
            setText(Utils.trim(s.substring(0, paren)));
            this.second = Utils.trim(s.substring(paren+1));
        } else {
            setText();
        }
    }
    public Map<String, String> getChildren() {
        return this.children;
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
    public int consume(NodeList nl, int start) {
        int consumed = 0;
        String left = "";
        String right = "";
        for(int i = start; i < nl.getLength(); i++) {
            if(nl.item(i).getNodeName().equals("b")) {
                if(i == start) {
                    left = this.second;
                }
                right = nl.item(i).getFirstChild().getTextContent();
                String cleanright = Utils.cleanTrailingPunctuation(Utils.trim(right));
                for (String realleft : LabelMap.getPoS(left)) {
                    this.children.put(realleft, cleanright);
                }
                consumed++;
            } else if(nl.item(i).getNodeName().equals("g")) {
                left = Utils.trim(nl.item(i).getFirstChild().getTextContent());
                consumed++;
            } else if(nl.item(i).getNodeName().equals("#text") && Utils.trim(nl.item(i).getTextContent()).equals(").")) {
                if(i < (nl.getLength() - 1) && nl.item(i + 1).getNodeName().equals("r") && nl.item(i + 1).getFirstChild().getTextContent().equals(" ")) {
                    return consumed + 2;
                } else {
                    return consumed + 1;
                }
            } else {
                return consumed;
            }
        }
        return consumed;
    }
}
