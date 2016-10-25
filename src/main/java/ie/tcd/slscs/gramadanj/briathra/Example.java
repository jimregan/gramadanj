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

public class Example {
    String text;
    String german;
    String english;
    Example(String text, String en, String de) {
        this.text = text;
        this.german = de;
        this.english = en;
    }
    Example() {}

    static Example fromNode(Node eg) throws Exception {
        Example e = new Example();
        if(eg.getNodeName().equals("beispiel")) {
            for(int j = 0; j < eg.getChildNodes().getLength(); j++) {
                Node egp = eg.getChildNodes().item(j);
                if(egp.getNodeName().equals("beispielText")) {
                    e.text = egp.getFirstChild().getTextContent();
                } else if(egp.getNodeName().equals("übersetzungDE")) {
                    e.german = egp.getFirstChild().getTextContent();
                } else if(egp.getNodeName().equals("übersetzungEN")) {
                    e.english = egp.getFirstChild().getTextContent();
                } else {
                    throw new Exception("incorrect node type");
                }
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return e;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Example)) {
            return false;
        }
        final Example e = (Example) o;
        if(e.text.equals(this.text) && e.english.equals(this.english) && e.german.equals(this.german)) {
            return true;
        } else {
            return false;
        }
    }
}
