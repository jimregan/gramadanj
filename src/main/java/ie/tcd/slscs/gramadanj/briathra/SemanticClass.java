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

public class SemanticClass {
    public String semanticClass;
    public boolean certainty;

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
}
