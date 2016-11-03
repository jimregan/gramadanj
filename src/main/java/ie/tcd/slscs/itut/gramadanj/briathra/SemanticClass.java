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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SemanticClass {
    public String semanticClass;
    public boolean certainty;
    public SemanticClass() {}
    public SemanticClass(String scls, boolean cert) {
        this.semanticClass = scls;
        this.certainty = cert;
    }

    static final Map<String, String> classes;
    static {
        Map<String, String> tmpclasses = new HashMap<String, String>();
        tmpclasses.put("ACT", "Action");
        tmpclasses.put("AUX", "Auxiliary");
        tmpclasses.put("CAU", "Causative");
        tmpclasses.put("COG", "Cognition");
        tmpclasses.put("DIC", "Speech");
        tmpclasses.put("EVT", "Event");
        tmpclasses.put("MOD", "");
        tmpclasses.put("MOT", "Motion");
        tmpclasses.put("PHA", "Phase");
        tmpclasses.put("PRO", "Process");
        tmpclasses.put("PSY", "Perception");
        tmpclasses.put("REL", "Relation");
        tmpclasses.put("SOC", "");
        tmpclasses.put("TRA", "Transport");
        classes = Collections.unmodifiableMap(tmpclasses);
    }

    public String getClassDescription(String cls) {
        return classes.get(cls);
    }
    public String getClassDescription() {
        return classes.get(this.semanticClass);
    }
    public static SemanticClass fromNode(Node n) throws Exception {
        SemanticClass c = new SemanticClass();
        if(n.getNodeName().equals("semantischeKlasse")) {
            String scls = n.getAttributes().getNamedItem("klasse").getNodeValue();
            c.semanticClass = scls;
            String cert = n.getAttributes().getNamedItem("sicherheit").getNodeValue();
            if(cert.equals("sicher")) {
                c.certainty = true;
            } else {
                c.certainty = false;
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return c;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof SemanticClass)) {
            return false;
        }
        final SemanticClass s = (SemanticClass) o;
        if(s.certainty == this.certainty && s.semanticClass.equals(this.semanticClass)) {
            return true;
        } else {
            return false;
        }
    }
}
