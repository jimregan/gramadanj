package ie.tcd.slscs.itut.gramadanj;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 * 
 * Based on Gramadán:
 * The MIT License (MIT)
 *
 * Copyright © 2017 Foras na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import ie.tcd.slscs.itut.gramadanj.Features.Mutation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Opers {
    public static String Demutate (String text) {
        String ret=text;
        ret=Utils.s(ret, "^[bB][hH]([fF].*)$", "$1");
        ret=Utils.s(ret, "^([bcdfgmpstBCDFGMPST])[hH](.*)$", "$1$2");
        ret=Utils.s(ret, "^[mM]([bB].*)$", "$1");
        ret=Utils.s(ret, "^[gG]([cC].*)$", "$1");
        ret=Utils.s(ret, "^[nN]([dD].*)$", "$1");
        ret=Utils.s(ret, "^[nN]([gG].*)$", "$1");
        ret=Utils.s(ret, "^[bB]([pP].*)$", "$1");
        ret=Utils.s(ret, "^[tT]([sS].*)$", "$1");
        ret=Utils.s(ret, "^[dD]([tT].*)$", "$1");
        ret=Utils.s(ret, "^[dD]'([fF])[hH](.*)$", "$1$2");
        ret=Utils.s(ret, "^[dD]'([aeiouaáéíóúAEIOUÁÉÍÓÚ].*)$", "$1");
        ret=Utils.s(ret, "^[hH]([aeiouaáéíóúAEIOUÁÉÍÓÚ].*)$", "$1");
        ret=Utils.s(ret, "^[nN]-([aeiouaáéíóúAEIOUÁÉÍÓÚ].*)$", "$1");
        
        return ret;
    }

    public static String Mutate (Mutation mutation, String text) {
        String ret = text;
        if ((mutation == Features.Mutation.Len1) || (mutation == Features.Mutation.Len1D)
            || (mutation == Features.Mutation.Len2) || (mutation == Features.Mutation.Len2D)
            || (mutation == Features.Mutation.Len3) || (mutation == Features.Mutation.Len3D)) {
            if (text.matches("^([pbmftdcgPBMFTDCG])[jJ]")) {
                return text;
            }
            if ((mutation == Features.Mutation.Len1) || (mutation == Features.Mutation.Len1D)) {
                ret=Utils.s(ret, "^([pbmftdcgPBMFTDCG])(.*)$", "$1h$2");
                ret=Utils.s(ret, "^([sS])([rnlRNLaeiouáéíóúAEIOUÁÉÍÓÚ].*)$", "$1h$2");
            } else {
                ret=Utils.s(ret, "^([pbmfcgPBMFCG])(.*)$", "$1h$2");
                if ((mutation == Features.Mutation.Len3) || (mutation == Features.Mutation.Len3D)) {
                    ret = Utils.s(ret, "^([sS])([rnlRNLaeiouáéíóúAEIOUÁÉÍÓÚ].*)$", "t$1$2");
                }
            }
            if ((mutation == Features.Mutation.Len1D) || (mutation == Features.Mutation.Len2D)
                || (mutation == Features.Mutation.Len3D)) {
                ret = Utils.s(ret, "^([aeiouáéíóúAEIOUÁÉÍÓÚfF])(.*)$", "d'$1$2");
                //ret = s(ret, "^([aeiouáéíóúAEIOUÁÉÍÓÚ])(.*)$", "d'$1$2");
                //ret = s(ret, "^([fF])(.*)$", "d'fh$2");
            }
        }
        if ((mutation == Features.Mutation.Ecl1) || (mutation == Features.Mutation.Ecl1x)
            || (mutation == Features.Mutation.Ecl2) || (mutation == Features.Mutation.Ecl3)) {
            ret=Utils.s(ret, "^([pP])(.*)$", "b$1$2");
            ret=Utils.s(ret, "^([bB])(.*)$", "m$1$2");
            ret=Utils.s(ret, "^([fF])(.*)$", "bh$1$2");
            ret=Utils.s(ret, "^([cC])(.*)$", "g$1$2");
            ret=Utils.s(ret, "^([gG])(.*)$", "n$1$2");
            if ((mutation == Features.Mutation.Ecl1) || (mutation == Features.Mutation.Ecl1x)) {
                ret=Utils.s(ret, "^([tT])(.*)$", "d$1$2");
                ret=Utils.s(ret, "^([dD])(.*)$", "n$1$2");
            }
            if (mutation == Features.Mutation.Ecl1) {
                ret=Utils.s(ret, "^([aeiuoáéíúó])(.*)$", "n-$1$2");
                ret=Utils.s(ret, "^([AEIUOÁÉÍÚÓ])(.*)$", "n$1$2");
            }
            if (mutation == Features.Mutation.Ecl3) {
                ret=Utils.s(ret, "^([sS])([rnlRNLaeiouáéíóúAEIOUÁÉÍÓÚ].*)$", "t$1$2");
            }
        }
        if (mutation == Features.Mutation.PrefT) {
            ret=Utils.s(ret, "^([aeiuoáéíúó])(.*)$", "t-$1$2");
            ret=Utils.s(ret, "^([AEIUOÁÉÍÚÓ])(.*)$", "t$1$2");
        }
        if (mutation == Features.Mutation.PrefH) {
            ret=Utils.s(ret, "^([aeiuoáéíúóAEIUOÁÉÍÚÓ])(.*)$", "h$1$2");
        }
        return ret;
    }

    public static String Consonants = "bcdfghjklmnpqrstvwxz";
    public static String Vowels = "aeiouáéíóú";
    public static String VowelsBroad = "aouáóú";
    public static String VowelsSlender = "eiéí";

    /**
     * Regular broadening: changes a slender vowel/vowel cluster to its
     * broad equivalent, if the input string ends with a consonant
     * cluster; if not, the string is returned unchanged.
     * See {@link #Slenderise(String)}, which performs the opposite operation.
     * @param bayse input
     * @return broadened string
     */
    public static String Broaden(String bayse) {
        String ret = bayse;
        String[] sources = new String[] {"ói", "ei", "éi", "i",  "aí",  "í",  "ui", "io"};
        String[] targets = new String[] {"ó",  "ea", "éa", "ea", "aío", "ío", "o",  "ea"};
        Matcher m;
        for (int i=0; i < sources.length; i++) {
            Pattern p1 = Pattern.compile("^(.*[" + Consonants + "])?" + sources[i] + "([" + Consonants + "]+)$");
            m = p1.matcher(bayse);
            if (m.matches()) {
                ret = m.group(1) + targets[i] + m.group(2);
                return ret;
            }
            Pattern p2 = Pattern.compile("^(.*)i([" + Consonants + "]+)$");
            m = p2.matcher(bayse);
            if (m.matches()) {
                ret = m.group(1) + m.group(2);
            }
        }
        return ret;
    }
    /**
     * Irregular broaden.
     * @param bayse
     * @param target
     * @return
     */
    public static String Broaden(String bayse, String target) {
        String ret = bayse;
        if(!target.matches("[" + VowelsBroad + "]$")) {
            ret = Broaden(bayse);
        } else {
            Pattern p = Pattern.compile("^(.*?)[" + Vowels + "]*[" + VowelsSlender + "]([" + Consonants + "]+)$");
            Matcher m = p.matcher(bayse);
            if(m.matches()) {
                ret = m.group(1) + m.group(2);
            }
        }
        return ret;
    }

    /**
     * Performs slenderisation of a word, replacing a broad vowel cluster
     * with its slender equivalent, if the input ends with a consonant
     * cluster; if not, the string is returned unchanged.
     * See {@link #Broaden(String)}, which performs the opposite operation.
     * @param bayse
     * @return
     */
    public static String Slenderise(String bayse) {
        String ret = bayse;
        String[] sources = new String[] {"ea", "éa", "ia", "ío", "io", "iu", "ae"};
        String[] targets = new String[] {"i",  "éi", "éi", "í",  "i",  "i",  "aei"};
        Matcher m;
        for (int i=0; i < sources.length; i++) {
            Pattern p1 = Pattern.compile("^(.*[" + Consonants + "])?" + sources[i] + "([" + Consonants + "]+)$");
            m = p1.matcher(bayse);
            if (m.matches()) {
                ret = m.group(1) + targets[i] + m.group(2);
                return ret;
            }
            Pattern p2 = Pattern.compile("^(.*[" + VowelsBroad + "])([" + Consonants + "]+)$");
            m = p2.matcher(bayse);
            if (m.matches()) {
                ret = m.group(1) + "i" + m.group(2);
            }
        }
        return ret;
    }

    /**
     * Irregular slenderisation.
     * @param bayse
     * @param target
     * @return
     */
    public static String Slenderise(String bayse, String target) {
        String ret = bayse;
        if(!target.matches("[" + VowelsSlender + "]$")) {
            ret = Slenderise(bayse);
        } else {
            Pattern p = Pattern.compile("^(.*?)[" + Vowels + "]*[" + VowelsBroad + "]([" + Consonants + "]+)$");
            Matcher m = p.matcher(bayse);
            if(m.matches()) {
                ret = m.group(1) + m.group(2);
            }
        }
        return ret;
    }
    /**
     * Spelling variation of {@link #Slenderise(String)}, to match original
     * @param bayse
     * @return
     */
    public static String Slenderize(String bayse) {
        return Slenderise(bayse);
    }

    /**
     * Devoices word final 'sd' to 'st'
     * @param bayse Input string
     * @return Devoiced string
     */
    public static String Devoice(String bayse) {
        String ret = bayse;
        ret = Utils.s(ret, "^(.*)sd$", "$1st");
        return ret;
    }
    
    /**
     * Checks if a string ends with a vowel
     * @param bayse String to match
     * @return true if bayse ends with a vowel, false otherwise
     */
    public static boolean EndsVowel(String bayse) {
        if(bayse == null || "".equals(bayse)) {
            return false;
        }
        return IsIrishVowel(bayse.charAt(bayse.length() - 1));
    }
    
    /**
     * Checks if a string starts with a vowel
     * @param bayse String to match
     * @return true if bayse starts with a vowel, false otherwise
     */
    public static boolean StartsVowel(String bayse) {
        if(bayse == null || "".equals(bayse)) {
            return false;
        }
        return IsIrishVowel(bayse.charAt(0));
    }

    /**
     * Helper function to check if a character is an Irish vowel
     * @param c the character to check
     * @return true if the character is a vowel, false otherwise
     */
    public static boolean IsIrishVowel(char c) {
        switch(c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case '\u00e1':
            case '\u00e9':
            case '\u00ed':
            case '\u00f3':
            case '\u00fa':
            case '\u00c1':
            case '\u00c9':
            case '\u00cd':
            case '\u00d3':
            case '\u00da':
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if word ends with "DeNTalS" consonant
     * @param bayse String to match
     * @return true if strings ends with d, n, t, or s;
     * false otherwise
     */
    public static boolean EndsDentals(String bayse) {
        return bayse.matches("[dntsDNTS]$");
    }
    
    public static String Unduplicate(String bayse) {
        String ret = bayse;
        if(bayse.matches("^.*[" + Consonants + "][" + Consonants + "]$")) {
            if(bayse.charAt(bayse.length()-1) == bayse.charAt(bayse.length()-2)) {
                ret = bayse.substring(0, bayse.length()-1);
            }
        }
        return ret;
    }
    public static String Syncope(String bayse) {
        String ret = bayse;
        Pattern p = Pattern.compile("^(.*[" + Consonants + "])?[" + Vowels + "]+([" + Consonants + "]+)$");
        Matcher m = p.matcher(bayse);
        if(m.matches()) {
            ret = Devoice(Unduplicate(m.group(1) + m.group(2)));
        }
        return ret;
    }

    private static String firstUpper(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
    public static String Prefix(String prefix, String body) {
        Mutation m = Mutation.Len1;
        if(EndsDentals(prefix)) {
            m = Mutation.Len2;
        }
        if(prefix.charAt(prefix.length()-1) == body.charAt(0)) {
            prefix += "-";
        } else if(EndsVowel(prefix) && StartsVowel(body)) {
            prefix += "-";
        }
        if(Character.isUpperCase(body.charAt(0))) {
            prefix = firstUpper(prefix);
            if(prefix.charAt(prefix.length()-1) != '-') {
                prefix += "-";
            }
        }
        return prefix + Mutate(m, body);
    }
    public static boolean isSlender(String s) {
        return s.matches("[eiéí][^aeiouáéíóú]+$");
    }
}
