package ie.tcd.slscs.itut.gramadanj.Conversion.EID;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2015-2016 Coláiste na Tríonóide, Baile Átha Cliath
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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.*;
import ie.tcd.slscs.itut.gramadanj.Utils;

/**
 *
entries
entry
label
line
noindex
sense
src
subsense
super
supersense
title
trg
*/

public class EIDReader {
    /**
     * valency information for verbs (and adjectives) is in the form
     * <noindex>(<src>foo</src>, <trg>bar</trg>)</noindex>
     * or
     * <noindex>(<src>foo</src>, <trg>bar</trg>; <src>foo</src>, <trg>bar</trg>)</noindex>
     * If the node passed is a &lt;noindex&gt; with that specific set of
     * child nodes, return true; false otherwise
     * FIXME: <noindex>(<src>foo</src>, <trg>bar</trg>; <trg>bar</trg>)</noindex>
     * @param n the Node to check
     * @return true if all conditions are satisfied, false otherwise
     */
    static boolean isValencyNoIndex(Node n) {
        if(!n.getNodeName().equals("noindex")) {
            return false;
        }
        NodeList ch = n.getChildNodes();
        if(!(ch.getLength() >= 5 && (ch.getLength() % 2 == 1))) {
            return false;
        }
        if(!(ch.item(0).getNodeName().equals("#text") && ch.item(0).getTextContent().equals("("))) {
            return false;
        }
        if(!ch.item(1).getNodeName().equals("src")) {
            return false;
        }
        if(!(ch.item(2).getNodeName().equals("#text") && ch.item(2).getTextContent().equals(", "))) {
            return false;
        }
        if(!ch.item(ch.getLength() - 2).getNodeName().equals("trg")) {
            return false;
        }
        if(!(ch.item(ch.getLength() - 1).getNodeName().equals("#text") && ch.item(ch.getLength() - 1).getTextContent().equals(")"))) {
            return false;
        }
        return true;
    }
    public static boolean isPunctuationNode(Node n) {
        if(!n.getNodeName().equals("#text")) {
            return false;
        } else {
            if(Utils.trim(n.getTextContent()).equals(".")) {
                return true;
            } else if(Utils.trim(n.getTextContent()).equals(",")) {
                return true;
            } else if(Utils.trim(n.getTextContent()).equals(":")) {
                return true;
            } else {
                return false;
            }
        }
    }
    public static boolean isSeeAlsoNode(Node n) {
        return Utils.textNodeContains(n, "S.a.");
    }
    public static boolean isEqualsNode(Node n) {
        return Utils.textNodeContains(n, "=");
    }
    public static List<Entry> loadXML(InputSource is) throws Exception {
        List<Entry> entries = new ArrayList<Entry>();
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);
        String root = doc.getDocumentElement().getNodeName();
        if(!root.equals("entries")) {
            throw new IOException("This file does not appear to contain EID!");
        }
        for(int i = 0; i < doc.getDocumentElement().getChildNodes().getLength(); i++) {
            Node ent = doc.getDocumentElement().getChildNodes().item(i);
            if(ent.getNodeName().equals("entry")) {
                Entry e = new Entry();
                String last_node;
                for(int j = 0; j < ent.getChildNodes().getLength(); j++) {
                    Node enta = ent.getChildNodes().item(j);
                    String cur_node = enta.getNodeName();
                    if(cur_node.equals("title")) {
                        last_node = cur_node;

                    }

                }
            } else {
                throw new Exception("Unexpected node: " + ent.getNodeName());
            }
        }
        return entries;
    }
}