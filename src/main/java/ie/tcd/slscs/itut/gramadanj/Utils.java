package ie.tcd.slscs.itut.gramadanj;

/*
 * Copyright 2016 Jim O'Regan <jaoregan@tcd.ie>
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

import ie.tcd.slscs.itut.gramadanj.Features.Gender;
import ie.tcd.slscs.itut.gramadanj.Features.Strength;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    /**
     * Gets the value of a boolean attribute from the root node of a document
     * @param doc Document to extract value from
     * @param attr Name of attribute
     * @return boolean corresponding to the value of the attribute
     * @throws IOException
     */
    public static boolean getBooleanAttr(Document doc, String attr) throws IOException {
        String curattr = doc.getDocumentElement().getAttribute(attr);
        if(curattr == null) {
            throw new IOException("missing attribute: " + attr);
        } else {
            return curattr.equals("1");
        }
    }

    /**
     * Gets the value of the gender attribute from a node.
     * @param n Node containing the gender attribute
     * @return Gender
     * @throws IOException
     */
    public static Gender getGender(Node n) throws IOException {
        String attr = n.getAttributes().getNamedItem("gender").getNodeValue();
        if(attr == null) {
            throw new IOException("missing attribute: gender");
        }
        if("fem".equals(attr)) {
            return Features.Gender.Fem;
        } else if("masc".equals(attr)) {
            return Features.Gender.Masc;
        } else {
            throw new IOException("attribute gender can contain only \"masc\" or \"fem\", got: " + attr);
        }
    }

    /**
     * Gets the value of the strength attribute from a node
     * @param n Node containing the strength attribute
     * @return Strength
     * @throws IOException
     */
    public static Strength getStrength(Node n) throws IOException {
        String attr = n.getAttributes().getNamedItem("strength").getNodeValue();
        if(attr == null) {
            throw new IOException("missing attribute: strength");
        }
        if("strong".equals(attr)) {
            return Features.Strength.Strong;
        } else if("weak".equals(attr)) {
            return Features.Strength.Weak;
        } else {
            throw new IOException("attribute strength can contain only \"strong\" or \"weak\", got: " + attr);
        }
    }
	
    /**
     * Gets the string of the default attribute of a node
     * @param n Node containing the default attribute
     * @return Value of the default attribute
     * @throws IOException
     */
    public static String getDefault(Node n) throws IOException {
        String attr = n.getAttributes().getNamedItem("default").getNodeValue();
        if(attr == null) {
            throw new IOException("missing attribute: default");
        }
        return attr;
    }

    /**
     * As close as I can get to Perl's s///g operator
     */
    static String s(String text, String pattern, String replacement) {
        String ret=text;
        if (text.matches(pattern)) {
            ret=text.replaceAll(pattern, replacement);
        }
        return ret;
    }

    public static String trim(String s) {
        int start = 0;
        int end = s.length() - 1;
        for(int i = start; i < end; i++) {
            if(s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\t') {
                start++;
            } else {
                break;
            }
        }
        for(int i = end; i > start; i--) {
            if(s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\t') {
                end--;
            } else {
                break;
            }
        }
        if(start == end) {
            return "";
        }
        return s.substring(start, end + 1);
    }

    public static String cleanTrailingPunctuation(String s) {
        char last = s.charAt(s.length() - 1);
        if (last == '.' || last == ',' || last == ';' || last == ':') {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

    /**
     * Expands an abbreviated grammatical entry of the kind used in
     * Foclóir Gaeilge-Béarla (Ó Dónaill).
     * @param base The headword to use as a basis for the expanded entry
     * @param mut the abbreviated ending
     * @return The expanded wordform
     */
    public static String expandFGB(String base, String mut) {
        String ret = trim(mut);
        if(ret.charAt(0) == '~') {
            return ret.replaceFirst("~", base);
        } else if(ret.charAt(0) == '-') {
            int offset = base.lastIndexOf(mut.charAt(1));
            return base.substring(0, offset) + ret.substring(1);
        } else {
            return base;
        }
    }

    /**
     * Expands parenthetical variants of the kind used in FGB:
     * "colo(u)r" to "color" and "colour"
     * @param s The string to expand
     * @return A string array containing both alternatives
     */
    public static String[] expandParentheticalVariants(String s) {
        String[] out = new String[2];
        String first = "";
        String second = "";
        boolean inside = false;
        for(char c : s.toCharArray()) {
            if(inside) {
                if(c == ')') {
                    inside = false;
                } else {
                    second += c;
                }
            } else {
                if(c == '(') {
                    inside = true;
                } else {
                    first += c;
                    second += c;
                }
            }
        }
        out[0] = first;
        out[1] = second;
        return out;
    }

    public static <T extends Comparable<? super T>> boolean equalLists(List<T> a, List<T> b) {
        if(a == null && b == null) {
            return true;
        }
        if(a == null && b != null) {
            return false;
        }
        if(a != null && b == null) {
            return false;
        }
        if(a.size() != b.size()) {
            return false;
        }
        a = new ArrayList<T>(a);
        b = new ArrayList<T>(b);
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }

    /**
     * Converts a string to a Node, mainly for use with tests
     * @param s The XML string to convert
     * @return an XML Node
     * @throws Exception
     */
    public static Node stringToNode(String s) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(s)));
        String root = doc.getDocumentElement().getNodeName();
        Node n = doc.getDocumentElement().cloneNode(true);
        return n;
    }

    /**
     * Slurp a .tsv file into a map
     */
    public static Map<String, String> readTSV(File f) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        BufferedReader br = new BufferedReader(new FileInputStream(f));
        String line;
        int lineno = 0;
        while((line = br.readLine()) != null) {
            lineno++;
            String[] sp = line.split("\\t");
            if(sp.length != 2) {
                throw new IOException("Error reading file at line: " + lineno);
            }
            ret.put(sp[0], sp[1]);
        }
        return ret;
    }
}
