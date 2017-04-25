package ie.tcd.slscs.itut.gramadanj.EID;
/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2017 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2015-2017 Coláiste na Tríonóide, Baile Átha Cliath
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

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import ie.tcd.slscs.itut.gramadanj.Utils;

public class Src extends Element {
    int supersense = 0;
    List<String> variants;
    Src() {
      variants = new ArrayList<String>();
    }
    Src(String s) {
      this();
      setRaw(s);
      if(s.endsWith(" (the)")) {
        setText(s.substring(0, s.length()-6));
      } else if(s.contains("(")) {
        String[] ss = Utils.expandParentheticalVariants(s);
        this.variants.add(ss[1]);
        setText(ss[0]);
      } else {
        setText(s);
      }
    }
    public static Src fromNode(Node n) throws Exception {
        String txt;
        if(n.getNodeName().equals("src")) {
            txt = n.getFirstChild().getTextContent();
        } else {
            throw new Exception("Unexpected node: " + n.getNodeName());
        }
        return new Src(txt);
    }
    public boolean hasSuperSense() {
      return (this.supersense > 0);
    }
    public boolean isPrefixLike() {
      return getRaw().startsWith("-");
    }
    public boolean isSuffixLike() {
      return getRaw().endsWith("-");
    }
    public List<String> getVariants() {
      return this.variants;
    }
    /**
     * Because the first entry in &lt;trg&gt; is always uppercase, we check here
     * to see if the case needs to be adjusted to lowercase.
     */
    public boolean isCased() {
      return(getText().equals(getText().toLowerCase()));
    }
    public void addVariant(Src s) {
      this.variants.add(s.getText());
      for(String vs : s.getVariants()) {
        this.variants.add(vs);
      }
    }
}
