package ie.tcd.slscs.itut.gramadanj.briathra;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class Entry {
    public String lemma;
    public String sublemma;
    public String group;
    public String subgroup;
    public String id;

    public List<SemanticClass> semanticClasses;
    public List<Valency> args;
    public List<Example> examples;
    public List<Quote> quotes;
    public List<String> comments;
    public Header header;

    public Entry() {
        semanticClasses = new ArrayList<SemanticClass>();
        examples = new ArrayList<Example>();
        comments = new ArrayList<String>();
        args = new ArrayList<Valency>();
        quotes = new ArrayList<Quote>();
        header = new Header();
    }

    static Entry fromNode(Node n) throws Exception {
        Entry e = new Entry();
        if(n.getNodeName().equals("eintrag")) {
            e.lemma = n.getAttributes().getNamedItem("lemma").getNodeValue();
            e.sublemma = n.getAttributes().getNamedItem("sublemma").getNodeValue();
            e.group = n.getAttributes().getNamedItem("gruppe").getNodeValue();
            e.subgroup = n.getAttributes().getNamedItem("untergruppe").getNodeValue();
            e.id = n.getAttributes().getNamedItem("lexonomyID").getNodeValue();

            for(int i = 0; i < n.getChildNodes().getLength(); i++) {
                Node cur = n.getChildNodes().item(i);
                if(cur.getNodeName().equals("semantischeKlasse")) {
                    e.semanticClasses.add(SemanticClass.fromNode(cur));
                } else if(cur.getNodeName().equals("kopfzeile")) {
                    e.header = Header.fromNode(cur);
                } else if(cur.getNodeName().equals("valenz")) {
                    for(int j = 0; j < cur.getChildNodes().getLength(); j++) {
                        Node curn = cur.getChildNodes().item(j);
                        if(curn.getNodeName().equals("stelle")) {
                            e.args.add(Valency.fromNode(curn));
                        } else {
                            throw new Exception("incorrect node type");
                        }
                    }
                } else if(cur.getNodeName().equals("beispiele")) {
                    for (int j = 0; j < cur.getChildNodes().getLength(); j++) {
                        e.examples.add(Example.fromNode(cur.getChildNodes().item(j)));
                    }
                } else if(cur.getNodeName().equals("belege")) {
                    for (int j = 0; j < cur.getChildNodes().getLength(); j++) {
                        e.quotes.add(Quote.fromNode(cur.getChildNodes().item(j)));
                    }
                } else if(cur.getNodeName().equals("kommentare")) {
                    for (int j = 0; j < cur.getChildNodes().getLength(); j++) {
                        Node curq = cur.getChildNodes().item(j);
                        if(curq.getNodeName().equals("kommentarIntern")) {
                            e.comments.add(curq.getFirstChild().getTextContent());
                        }
                    }
                } else {
                    throw new Exception("incorrect node name: " + cur.getNodeName());
                }
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return e;
    }
}
