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

public class SemanticRole {
    static final Map<String, String> roles;
    static {
        Map<String, String> tmproles = new HashMap<String, String>();
        tmproles.put("ACT", "Action");
        tmproles.put("ADR", "Adressee");
        tmproles.put("AFF", "Patient");
        tmproles.put("AGS", "Agent");
        tmproles.put("ASP", "");
        tmproles.put("ASS", "Association");
        tmproles.put("CAU", "Cause");
        tmproles.put("CON", "Contact Point");
        tmproles.put("DIC", "Speech");
        tmproles.put("DIR", "Direction");
        tmproles.put("EFF", "Product");
        tmproles.put("EVT", "Event");
        tmproles.put("EXP", "Experiencer");
        tmproles.put("FAC", "Fact");
        tmproles.put("FIN", "Purpose");
        tmproles.put("FNT", "Source");
        tmproles.put("FUN", "Role");
        tmproles.put("INS", "Instrument");
        tmproles.put("INT", "Total");
        tmproles.put("LOC", "Location");
        tmproles.put("MOD", "Method");
        tmproles.put("MOT", "Moved Object");
        tmproles.put("MOV", "Motive");
        tmproles.put("PER", "Perfective");
        tmproles.put("POS", "Possession");
        tmproles.put("PRT", "Part");
        tmproles.put("QUA", "Quality");
        tmproles.put("RES", "Result");
        tmproles.put("SOC", "Society");
        tmproles.put("STA", "State");
        tmproles.put("TEM", "Time");
        tmproles.put("TER", "Goal");
        tmproles.put("THE", "Theme");
        tmproles.put("VAL", "Value");
        tmproles.put("X", "unspecified");
        roles = Collections.unmodifiableMap(tmproles);
    }
}
