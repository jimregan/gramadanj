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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The &lt;entry&gt; element is the primary element, containing an individual
 * dictionary entry.
 */
public class Entry {//extends Element {

    private Title title;
    private List<Element> children;
    Entry() {
        this.children = new ArrayList<Element>();
    }
    private static boolean nameeq(Node n, String s) {
        return n.getNodeName().equals(s);
    }
    private static boolean isText(Node n) {
        return nameeq(n, "#text");
    }
    /**
     * First pass for simple 'equals' entries. Find '=' in any child node
     * of the entry
     */
    public static boolean entryContainsEquals(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if (isText(n)) {
                if (n.getTextContent().contains("=")) {
                    return true;
                }
            } else if(n.getNodeType() == Element.ELEMENT_NODE) {
                if(isText(n.getFirstChild()) && n.getFirstChild().getTextContent().contains("=")) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }
    public static Entry fromNode(Node n) throws Exception {
        Entry ret = new Entry();
        if(!nameeq(n, "entry")) {
            throw new IOException("Expected <entry>, got <" + n.getNodeName() + ">");
        }
        NodeList nl = n.getChildNodes();
        for(int i = 0; i < nl.getLength(); i++) {
            Node cur = nl.item(i);
            if(nameeq(cur, "title")) {
                Title t = Title.fromNode(cur);
                // Have Title consume X, if it's the next element
                if(i < (nl.getLength() - 1) && nameeq(nl.item(i + 1), "x")) {
                    t.setX(nl.item(i + 1));
                    i++;
                }
            } else if(nameeq(cur, "c")) {
                
            }
        }
        return ret;
    }
}
