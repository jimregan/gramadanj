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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SyntacticRole {
    public String syntacticRole;
    public boolean obligatory;
    public SyntacticRole() {}
    public SyntacticRole(String role, boolean opt) {
        this.syntacticRole = role;
        this.obligatory = opt;
    }
    public SyntacticRole(String role, String opt) {
        this(role, opt.equals("obligatorisch"));
    }
    public void setContent(String role, String opt) {
        this.syntacticRole = role;
        this.obligatory = opt.equals("obligatorisch");
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
    public static String getRoleDescription(String cls) {
        return roles.get(cls);
    }
    public String getRoleDescription() {
        return roles.get(this.syntacticRole);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof SyntacticRole)) {
            return false;
        }
        final SyntacticRole s = (SyntacticRole) o;
        if(s.obligatory == this.obligatory && s.syntacticRole.equals(this.syntacticRole)) {
            return true;
        } else {
            return false;
        }
    }
}
