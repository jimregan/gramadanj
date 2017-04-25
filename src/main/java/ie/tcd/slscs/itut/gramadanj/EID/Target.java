package ie.tcd.slscs.itut.gramadanj.EID;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2015-2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
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

import ie.tcd.slscs.itut.gramadanj.Utils;
import org.w3c.dom.Node;

public class Target {
    private String before;
    private String after;
    private String gender;
    private String secondaryGender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isMultiplePOS() {
        return multiplePOS;
    }

    public void setMultiplePOS(boolean multiplePOS) {
        this.multiplePOS = multiplePOS;
    }

    private String label;
    private boolean multiplePOS = false;

    public boolean isNoun() {
        return (label.toLowerCase().equals("m") || label.toLowerCase().equals("f")
               || label.toLowerCase().equals("mpl") || label.toLowerCase().equals("fpl"));
    }
    public boolean isSimpleWord () {
        return (("".equals(after) || afterHasGrammaticalInformation())
                && !hasCloseParen() && !hasOpenParen() && !isAmbiguous());
    }
    public boolean hasOpenParen() {
        return (before.contains("(") || after.contains("("));
    }
    public boolean hasCloseParen() {
        return (before.contains(")") || after.contains(")"));
    }
    public boolean nextContinues() {
        return (hasOpenParen() && !hasCloseParen());
    }
    public boolean isAmbiguous() {
        return (before.contains(",") || after.contains(","));
    }
    public boolean afterHasGrammaticalInformation() {
        return Utils.trim(after).startsWith("-");
    }

    private static boolean optionalLabel(Node n) {
        if(n.getChildNodes().getLength() == 3
           && (n.getChildNodes().item(0).getNodeName().equals("#text") && n.getChildNodes().item(0).getTextContent().equals("("))
           && n.getChildNodes().item(1).getNodeName().equals("label")
           && (n.getChildNodes().item(2).getNodeName().equals("#text") && n.getChildNodes().item(2).getTextContent().equals(")"))) {
            return true;
        }
        return false;
    }
    private static boolean maybeGrammatical(Node n) {
        if(n.getChildNodes().getLength() == 3
           && (n.getChildNodes().item(0).getNodeName().equals("#text") && n.getChildNodes().item(0).getTextContent().equals("("))
           && n.getChildNodes().item(1).getNodeName().equals("label")
           && (n.getChildNodes().item(2).getNodeName().equals("#text") && n.getChildNodes().item(2).getTextContent().endsWith(")"))) {
            return true;
        }
        return false;
    }

    /**
     * Checks the value of a &lt;label&gt; node, to see if it contains a value
     * containing grammatical gender, setting the normalised value of gender
     * if so.
     * @param n label node
     * @return true, if value seems to be a gender value
     */
    public boolean canSetGender(Node n) {
        String label = n.getFirstChild().getTextContent();
        if(!n.getNodeName().equals("label")) {
            return false;
        }
        if(label.equals("f") || label.equals("f.")) {
            this.gender = "f";
            return true;
        }
        if(label.equals("m") || label.equals("m.")) {
            this.gender = "m";
            return true;
        }
        if(label.equals("mpl") || label.equals("mpl.")) {
            this.gender = "mpl";
            return true;
        }
        if(label.equals("fpl") || label.equals("fpl.")) {
            this.gender = "fpl";
            return true;
        }
        return false;
    }
    static Target fromNode(Node n) throws Exception {
        Target t = new Target();
        if(n.getNodeName().equals("trg")) {
            for(int i = 0; i < n.getChildNodes().getLength(); i++) {
                Node cur = n.getChildNodes().item(i);
                if(cur.getNodeName().equals("#text")) {
                    if(i == 0) {
                        t.setBefore(cur.getTextContent());
                    } else {
                        t.setAfter(cur.getTextContent());
                    }
                } else if(cur.getNodeName().equals("label")) {
                    if(!t.canSetGender(cur)) {
                        t.setLabel(cur.getFirstChild().getTextContent());
                    }
                } else if(cur.getNodeName().equals("noindex")) {
                    if(cur.getChildNodes().getLength() == 3) {
                        if(optionalLabel(cur)) {
                            String lbltmp = cur.getChildNodes().item(1).getTextContent();
                            if(t.canSetGender(cur.getChildNodes().item(1))) {
                                t.setMultiplePOS(true);
                            } else {
                                t.setLabel(lbltmp);
                            }
                        }
                    }
                } else {
                    throw new Exception("incorrect node name: " + cur.getNodeName());
                }
            }
        } else {
            throw new Exception("incorrect node type");
        }
        return t;
    }

}
