package ie.tcd.slscs.itut.gramadanj.EID;
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

public class EIDReader {
    final static Map<String, String[]> multi;
    static {
        Map<String, String[]> multitmp = new HashMap<String, String[]>();
        multitmp.put("a. & s. Geog", new String[] {"a. & s.", "Geog"});
        multitmp.put("a. Bot:", new String[] {"a.", "Bot:"});
        multitmp.put("s. A", new String[] {"s.", "A"});
        multitmp.put("s. Anat", new String[] {"s.", "Anat"});
        multitmp.put("s. Archeol", new String[] {"s.", "Archeol"});
        multitmp.put("s. Bot", new String[] {"s.", "Bot"});
        multitmp.put("s. Box", new String[] {"s.", "Box"});
        multitmp.put("s. Brew", new String[] {"s.", "Brew"});
        multitmp.put("s. Cards", new String[] {"s.", "Cards"});
        multitmp.put("s. Civ.E", new String[] {"s.", "Civ.E"});
        multitmp.put("s. Cost", new String[] {"s.", "Cost"});
        multitmp.put("s. Cr", new String[] {"s.", "Cr"});
        multitmp.put("s. Ecc", new String[] {"s.", "Ecc"});
        multitmp.put("s. El", new String[] {"s.", "El"});
        multitmp.put("s. Ent", new String[] {"s.", "Ent"});
        multitmp.put("s. F", new String[] {"s.", "F"});
        multitmp.put("s. Fin", new String[] {"s.", "Fin"});
        multitmp.put("s. Geog", new String[] {"s.", "Geog"});
        multitmp.put("s. Harn", new String[] {"s.", "Harn"});
        multitmp.put("s.pl. Harn", new String[] {"s.pl.", "Harn"});
        multitmp.put("s. Her", new String[] {"s.", "Her"});
        multitmp.put("s. Hockey", new String[] {"s.", "Hockey"});
        multitmp.put("s. Husb", new String[] {"s.", "Husb"});
        multitmp.put("s. Ich", new String[] {"s.", "Ich"});
        multitmp.put("s. Jur", new String[] {"s.", "Jur"});
        multitmp.put("s. Lap", new String[] {"s.", "Lap"});
        multitmp.put("s. Leath", new String[] {"s.", "Leath"});
        multitmp.put("s. Ling", new String[] {"s.", "Ling"});
        multitmp.put("s. Lt", new String[] {"s.", "Lt"});
        multitmp.put("s. Mec.E", new String[] {"s.", "Mec.E"});
        multitmp.put("s. Metall", new String[] {"s.", "Metall"});
        multitmp.put("s. Mil", new String[] {"s.", "Mil"});
        multitmp.put("s. Myth", new String[] {"s.", "Myth"});
        multitmp.put("s. Nau", new String[] {"s.", "Nau"});
        multitmp.put("s. Orn", new String[] {"s.", "Orn"});
        multitmp.put("s. Orn:", new String[] {"s.", "Orn:"});
        multitmp.put("s. P", new String[] {"s.", "P"});
        multitmp.put("s. Poet", new String[] {"s.", "Poet"});
        multitmp.put("s. Rail", new String[] {"s.", "Rail"});
        multitmp.put("s. Row", new String[] {"s.", "Row"});
        multitmp.put("s. Sculp", new String[] {"s.", "Sculp"});
        multitmp.put("s. Ten", new String[] {"s.", "Ten"});
        multitmp.put("s. Tex", new String[] {"s.", "Tex"});
        multitmp.put("s. Th", new String[] {"s.", "Th"});
        multitmp.put("s. Veh", new String[] {"s.", "Veh"});
        multitmp.put("s. Ven", new String[] {"s.", "Ven"});
        multitmp.put("s. W.Tel", new String[] {"s.", "W.Tel"});
        multitmp.put("s. Wr", new String[] {"s.", "Wr"});
        multitmp.put("s.pl. Harn", new String[] {"s.pl.", "Harn"});
        multitmp.put("v.tr. Cu", new String[] {"v.tr.", "Cu"});
        multitmp.put("v.tr. F", new String[] {"v.tr.", "F"});
        multitmp.put("v.tr. Lit", new String[] {"v.tr.", "Lit"});
        multitmp.put("v.tr. Mec.E", new String[] {"v.tr.", "Mec.E"});
        multitmp.put("v.tr. P", new String[] {"v.tr.", "P"});
        multitmp.put("v.i. Cr", new String[] {"v.i.", "Cr"});
        multitmp.put("v.i. F", new String[] {"v.i.", "F"});
        multitmp.put("v.i. P", new String[] {"v.i.", "P"});
        multitmp.put("v.i. Sp", new String[] {"v.i.", "Sp"});
        multitmp.put("v.i. Th", new String[] {"v.i.", "Th"});
        multi =  Collections.unmodifiableMap(multitmp);
    }
    /**
     * valency information for verbs (and adjectives) is in the form
     * <noindex>(<src>foo</src>, <trg>bar</trg>)</noindex>
     * or
     * <noindex>(<src>foo</src>, <trg>bar</trg>; <src>foo</src>, <trg>bar</trg>)</noindex>
     * If the node passed is a noindex with that specific set of
     * child nodes, return true; false otherwise
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
    public Node repairEntry(Node n) throws Exception {
        if(!n.getNodeName().equals("entry")) {
            throw new IOException("Unrecognised node type: " + n.getNodeName());
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Node out = doc.createElement(n.getNodeName());
        for(int i = 0; i < n.getChildNodes().getLength(); i++) {
            Node c = n.getChildNodes().item(i);
            if(c.getNodeName().equals("label")) {
                // do stuff
            } else if(c.getNodeName().equals("trg")) {
                if(c.getChildNodes().getLength() > 2
                   && c.getChildNodes().item(1).getNodeName().equals("label")
                   && c.getChildNodes().item(1).getFirstChild().getNodeName().equals("#text")) {
                    Node tmptrg = doc.createElement("trg");
                    //tmptrg.appendChild(new Tex)
                    // c.getChildNodes().item(1).getFirstChild().getTextContent().matches("^[mf], [^ ]* [mf]$")
                }
            } else {
                out.appendChild(c.cloneNode(true));
            }
        }
        return out;
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
