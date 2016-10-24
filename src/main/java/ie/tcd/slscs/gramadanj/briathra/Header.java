package ie.tcd.slscs.gramadanj.briathra;
/*
 * Copyright 2016 Trinity College, Dublin
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

public class Header {
    List<HeaderFragment> pieces;
    public String english;
    public String german;

    public Header() {
        pieces = new ArrayList<HeaderFragment>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(HeaderFragment h : pieces) {
            if(!first) {
                sb.append(" ");
            } else {
                first = false;
            }
            if(h.ph) {
                sb.append("[");
            }
            sb.append(h.fragment);
            if(h.ph) {
                sb.append("]");
            }
        }
        return sb.toString();
    }
    static Header fromNode(Node n) throws Exception {
        Header h = new Header();
        if(n.getNodeName().equals("kopfzeile")) {
            for(int i = 0; i < n.getChildNodes().getLength(); i++) {
                Node nh = n.getChildNodes().item(i);
                if(nh.getNodeName().equals("paraphrase")) {
                    for(int j = 0; j < nh.getChildNodes().getLength(); j++) {
                        Node nhc = nh.getChildNodes().item(j);
                        h.pieces.add(HeaderFragment.fromNode(nhc));
                    }
                } else if(nh.getNodeName().equals("übersetzungDE")) {
                    h.german = nh.getFirstChild().getTextContent();
                } else if(nh.getNodeName().equals("übersetzungEN")) {
                    h.english = nh.getFirstChild().getTextContent();
                } else {
                    throw new Exception("incorrect node type");
                }
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return h;
    }
}