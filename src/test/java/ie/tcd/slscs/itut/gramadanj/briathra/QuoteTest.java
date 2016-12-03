package ie.tcd.slscs.itut.gramadanj.briathra;
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

import junit.framework.TestCase;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class QuoteTest extends TestCase {
    final String frag = "<beleg korpus=\"CC\" quelle=\"RM-4-08-01\" xml:space=\"preserve\">Tiom\u00E1in leat, <lemma xml:space=\"preserve\">abair</lemma> leat.</beleg>";
    private List<QuoteFragment> qf;
    private Node inNode;
    public QuoteTest() throws Exception {
        qf = new ArrayList<QuoteFragment>();
        qf.add(new QuoteFragment("Tiomáin leat, "));
        qf.add(new QuoteFragment("abair", true));
        qf.add(new QuoteFragment(" leat."));
        inNode = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new ByteArrayInputStream(frag.getBytes())).getDocumentElement();
    }
    public void testFromNode() throws Exception {
        Quote exp = new Quote("RM-4-08-01", "CC", qf);
        Quote inp = Quote.fromNode(inNode);
        assertEquals(exp, inp);
        qf.add(new QuoteFragment("break it!"));
        assert(!exp.equals(inp));
    }

}
