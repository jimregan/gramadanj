package ie.tcd.slscs.gramadanj.ainm;
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

public class Person extends TextPiece {
    public String id;
    public String baseform;
    public Person(String id, String base, String text) {
        super(text);
        this.id = id;
        this.baseform = base;
    }
    public static Person fromNode(Node n) throws Exception {
        String id;
        String bf;
        String txt;
        if(n.getNodeName().equals("persName")) {
            id = n.getAttributes().getNamedItem("id").getNodeValue();
            bf = n.getAttributes().getNamedItem("baseform").getNodeValue();
            txt = n.getFirstChild().getTextContent();
            if(bf == null || bf.equals("")) {
                bf = txt;
            }
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
        return new Person(id, bf, txt);
    }
    public static String personToURI(Person p) {
        return "http://www.ainm.ie/Bio.aspx?ID=" + p.id;
    }
}
