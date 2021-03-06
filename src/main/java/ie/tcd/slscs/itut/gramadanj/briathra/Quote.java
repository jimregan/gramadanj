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

import ie.tcd.slscs.itut.gramadanj.Utils;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class Quote {
    String source;
    String corpus;
    List<QuoteFragment> fragments;
    Quote() {
        fragments = new ArrayList<QuoteFragment>();
    }
    Quote(String s, String c, List<QuoteFragment> qf) {
        this.source = s;
        this.corpus = c;
        this.fragments = qf;
    }

    public static Quote fromNode(Node n) throws Exception {
        Quote q = new Quote();
        if(!n.getNodeName().equals("beleg")) {
            throw new Exception("incorrect node type: " + n.getNodeName());
        }
        q.source = n.getAttributes().getNamedItem("quelle").getNodeValue();
        q.corpus = n.getAttributes().getNamedItem("korpus").getNodeValue();
        for(int i = 0; i < n.getChildNodes().getLength(); i++) {
            Node child = n.getChildNodes().item(i);
            q.fragments.add(QuoteFragment.fromNode(child));
        }
        return q;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quote)) {
            return false;
        }
        final Quote q = (Quote) o;
        if(!q.corpus.equals(corpus)) {
            return false;
        } else if(!q.source.equals(source)) {
            return false;
        } else if(q.fragments.size() != fragments.size()) {
            return false;
        } else {
            for(int i = 0; i < q.fragments.size(); i++) {
                if(!q.fragments.get(i).equals(fragments.get(i))) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
}
