package ie.tcd.slscs.itut.gramadanj.cngv1;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Tags {
    static final Map<String, String> fulltags;
    static {
        Map<String, String> fulltmp = new HashMap<String, String>();
        fulltmp.put("Y", "+Abr");
        fulltmp.put("Fi", "+Punct+Int");
        fulltags = Collections.unmodifiableMap(fulltmp);
    }

    public static String mapGender(char c) {
        switch(c) {
            case 'm':
                return "+Masc";
            case 'f':
                return "+Fem";
            default:
                return "";
        }
    }
    public static String mapNumber(char c) {
        switch(c) {
            case 's':
                return "+Sg";
            case 'p':
                return "+Pl";
            default:
                return "";
        }
    }
    public static String mapPOS(char c) {
        switch(c) {
            case 'N':
                return "+Noun";
            case 'V':
                return "+Verb";
            case 'A':
                return "+Adj";
            case 'P':
                return "+Pron";
            case 'D':
                return "+Det";
            case 'T':
                return "+Art";
            case 'R':
                return "+Adv";
            case 'S':
                return "+Prep";
            case 'C':
                return "+Conj";
            case 'M':
                return "+Num";
            case 'I':
                return "+Ij";
            case 'U':
                return "+Part";
            case 'X':
                return "";
            case 'F':
                return "+Punct";
            default:
                return "";
        }
    }
    public static String mapDetType(char c) {
        switch(c) {
            case 'd':
                return "+Dem";
            case 'p':
                return "+Poss";
            case 'q':
                return "+Q";
            default:
                return "";
        }
    }
    public static String mapPers(char c) {
        switch(c) {
            case '1':
                return "+1P";
            case '2':
                return "+2P";
            case '3':
                return "+3P";
            default:
                return "";
        }
    }
    public static String mapCase(char c) {
        switch(c) {
            case 'n':
                return "+Nom";
            case 'g':
                return "+Gen";
            case 'd':
                return "+Dat";
            case 'v':
                return "+Voc";
            default:
                return "";
        }
    }
    public static String mapPositivity(char c) {
        switch(c) {
            case 'n':
                return "+Neg";
            // affirmative is unmarked
            case 'a':
                return "";
            default:
                return "";
        }
    }
    public static String mapTenseMood(char c, char d) {
        switch(c) {
            case 'i':
                switch(d) {
                    case 'p':
                        return "+PresInd";
                    case 's':
                        return "+PastInd";
                    case 'h':
                        return "+PresImp";
                    case 'f':
                        return "+FutInd";
                    case 'g':
                        return "+PastImp";
                    default:
                        return "";
                }
            case 's':
                if(d == 'p') {
                    return "+PresSubj";
                } else {
                    return "+Subj";
                }
            default:
                return "";
        }
    }
}
