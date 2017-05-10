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

public class Valencies extends Element {
    private List<Valency> vals;
    Valencies() {
        vals = new ArrayList<Valency>();
    }
    Valencies(List<Valency> l) {
        this();
        setValencies(l);
    }
    public setValencies(List<Valency> in) {
        this.vals = in;
    }
    public List<Valency> getValencies() {
        return this.vals;
    }
    public static Valencies fromNode(Node n) throws Exception {
        List<Valency> v = new ArrayList<Valency>();
        if(n.getNodeName().equals("noindex")) {
            NodeList ch = n.getChildNodes();
            String s = "";
            String t = "";
            // <noindex>(<src>from</src>, <trg>ó dhuine</trg>; <trg>ó, as, rud</trg>)
            // <noindex>(<src>to</src>, <trg>le</trg>; <src>between</src>, <trg>idir</trg>)
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
        return new Valencies(v);
    }
    public static boolean canSkipSrc(String s) {
        return (s.contains("s.o") || s.contains("sth"));
    }
    public static boolean canSkipTrg(String s) {
        return (s.contains("dhuine") || s.contains("duine") || s.contains("rud"));
    }
}
