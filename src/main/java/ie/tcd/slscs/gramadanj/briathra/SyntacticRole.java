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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SyntacticRole {
    public String syntacticRole;
    public boolean optional;
    public SyntacticRole() {}
    public SyntacticRole(String role, boolean opt) {
        this.syntacticRole = role;
        this.optional = opt;
    }
    static final Map<String, String> roles;
    static {
        Map<String, String> tmproles = new HashMap<String, String>();
        tmproles.put("A", "Adverb");
        tmproles.put("Adj", "Adjective");
        tmproles.put("N1", "Subject");
        tmproles.put("N2", "Object");
        tmproles.put("P1", "Prepositional Phrase");
        tmproles.put("P2", "Prepositional Phrase");
        tmproles.put("S1", "Infinitive Subclause");
        tmproles.put("S2", "Finite Subclause");
        roles = Collections.unmodifiableMap(tmproles);
    }
    public String getRoleDescription(String cls) {
        return roles.get(cls);
    }
    public String getRoleDescription() {
        return roles.get(this.syntacticRole);
    }
    public static SyntacticRole fromNode(Node n) throws Exception {
        SyntacticRole r = new SyntacticRole();
        if(n.getNodeName().equals("syntaktischeRolle")) {
            String role = n.getAttributes().getNamedItem("rolle").getNodeValue();
            r.syntacticRole = role;
            String opt = n.getAttributes().getNamedItem("optionalität").getNodeValue();
            if(opt.equals("obligatorisch")) {
                r.optional = false;
            } else {
                r.optional = true;
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return r;
    }
}
