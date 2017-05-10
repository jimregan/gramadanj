package ie.tcd.slscs.itut.gramadanj.EID;
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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.w3c.dom.Node;
import ie.tcd.slscs.itut.gramadanj.Utils;

/**
 * This is a pseudo-element used to extract see also information from text
 * nodes.
 * <p>
 * EID is somewhat notorious for having references to non-existent entries or
 * subentries.
 * <p>
 * The &lt;line&gt; element that contains the text reference may also contain
 * &lt;super&gt; elements, which makes it a bit of a nightmare. Here, they
 * are just appended with '#', to match the entry names. These nodes are of no
 * real use for our current purposes, so this exists just to consume them.
 * 
 */
public class SeeAlso extends Element {
    public void fromNode(Node n) throws Exception {
        String txt = getRaw();
        if(n.getNodeName().equals("super")) {
            txt += "#";
            txt += n.getFirstChild().getTextContent();
            setRaw(txt);
        } else if(n.getNodeName().equals("#text")) {
            txt += n.getTextContent();
            setRaw(txt);
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
    }
}
