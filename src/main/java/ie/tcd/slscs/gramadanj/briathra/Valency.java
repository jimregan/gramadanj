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

public class Valency {
    SyntacticRole syntacticRole;
    List<SemanticRole> semanticRoles;
    String lexicalisation = "";
    public Valency() {
        syntacticRole = new SyntacticRole();
        semanticRoles = new ArrayList<SemanticRole>();
    }
    public static Valency fromNode(Node n) throws Exception {
        Valency v = new Valency();
        if(n.getNodeName().equals("stelle")) {
            String synr = n.getAttributes().getNamedItem("syntaktischeRolle").getNodeValue();
            String optn = n.getAttributes().getNamedItem("optionalität").getNodeValue();
            v.syntacticRole.setContent(synr, optn);
            for(int i = 0; i < n.getChildNodes().getLength(); i++) {
                Node cur = n.getChildNodes().item(i);
                if(cur.getNodeName().equals("semantischeRolle")) {
                    v.semanticRoles.add(SemanticRole.fromNode(cur));
                } else if(cur.getNodeName().equals("lexikalisation")) {
                    v.lexicalisation = n.getFirstChild().getTextContent();
                } else {
                    throw new Exception("incorrect node type: " + cur.getNodeName());
                }
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return v;
    }
}