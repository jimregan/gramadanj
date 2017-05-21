package ie.tcd.slscs.itut.gramadanj.FGB;
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

import ie.tcd.slscs.itut.gramadanj.Utils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * The &lt;trans&gt; element contains translations within a child &lt;r&gt;
 * element
 */
public class SimpleEquals {
    String s;
    String x;
    String target;
    String targetx;

    SimpleEquals(String src, String x, String trg) {
        this.s = src;
        this.x = x;
        this.target = trg;
    }

    private static String cleaner(NodeList nl, int i) {
        return Utils.cleanTrailingPunctuation(nl.item(i).getFirstChild().getTextContent());
    }
    public static SimpleEquals[] fromEntry(Node n) {
        List<SimpleEquals> out = new ArrayList<SimpleEquals>();
        NodeList nl = n.getChildNodes();
        // <entry xml:space="preserve"><title>urnaí</title><x>2</x> = <s>fionraí.</s></entry>
        // <title>urlaic</title><x>2</x>, <g>f = </g><s>urlacan </s><n>2.</n></entry>
        // <title>úiríocht, </title><g>f = </g><s>úire</s><x>1</x>.</entry>
        // <title>umhla</title><x>2</x>, <g>f, </g><b>~</b><h>cht = </h><s>umhlaíocht.</s></entry>
        // <title>uráil </title>= <s>foráil</s><x>2,3</x>. </entry>
        // <title>uain</title><x>4</x>, <g>m = </g><s>uaithne</s><x>2</x>.</entry>
        // <title>tórramh</title><x>2</x>, ~aigh = <s>tórraigh.</s></entry>
        // <title>tonnáiste</title><x>2</x>, ~ach = <s>tónáiste, -ach.</s></entry>
        // <title>toghach, </title><g>a1 = </g><s>tofa</s><x>1</x> <n>2.</n></entry>
        if (nl.getLength() == 4
           && nl.item(0).getNodeName().equals("title")
           && nl.item(1).getNodeName().equals("x")
           && (nl.item(2).getNodeName().equals("#text") && Utils.trim(nl.item(2).getTextContent()).endsWith("="))
           && nl.item(3).getNodeName().equals("s")) {
            String text = Utils.trim(nl.item(2).getTextContent());
            String src = cleaner(nl, 0);
            String x = cleaner(nl, 1);
            String s = cleaner(nl, 3);
            out.add(new SimpleEquals(src, x, s));
        } else if (nl.getLength() == 5) {
        } else {
            return null;
        }
        return out.toArray(new SimpleEquals[out.size()]);
    }
}
