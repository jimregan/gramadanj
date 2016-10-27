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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Wikidata {
    static final Map<String, String> gender;
    static final Map<String, String> occupation;
    static final Map<String, String> university;
    static {
        Map<String, String> gendermap = new HashMap<String, String>();
        gendermap.put("M", "Q6581097");
        gendermap.put("F", "Q6581072");
        gender = Collections.unmodifiableMap(gendermap);
        Map<String, String> occmap = new HashMap<String, String>();
        occmap.put("scoláire", "Q20826540");
        occmap.put("múinteoir", "Q37226");
        occmap.put("file", "Q49757");
        occmap.put("scríobhaí", "Q916292");
        occupation = Collections.unmodifiableMap(occmap);
        Map<String, String> unimap = new HashMap<String, String>();
        unimap.put("Coláiste na Tríonóide, Baile Átha Cliath", "Q258464");
        university = Collections.unmodifiableMap(unimap);
    }
    public static String sex(String s) {
        if(gender.containsKey(s)) {
            return gender.get(s);
        } else {
            return "";
        }
    }
    public static String occupation(String s) {
        if(occupation.containsKey(s)) {
            return occupation.get(s);
        } else {
            return "";
        }
    }
    public static String university(String s) {
        if(university.containsKey(s)) {
            return university.get(s);
        } else {
            return "";
        }
    }
}
