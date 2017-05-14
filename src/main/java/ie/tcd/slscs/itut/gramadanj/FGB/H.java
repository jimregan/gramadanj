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
import ie.tcd.slscs.itut.gramadanj.EID.LabelMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The &lt;h&gt; element contains the continuation of a &lt;title&lt;
 * where there are optional pieces: e.g.,
 * <pre>
 * &lt;title&gt;smeanta&lt;/title&gt;(&lt;h&gt;in&lt;/h&gt;)(&lt;h&gt;e&lt;/h&gt;).&lt;h&gt; &lt;/h&gt;
 * </pre>
 * should be read as smeanta, smeantain, smeantaine
 * <p>
 * Otherwise, it can appear in two part 'equals' entries, to contain either the
 * second word or the ending to add to the first to create the second word.
 * For example:
 * <pre>
 * &lt;title&gt;aibiúil, &lt;/title&gt;&lt;g&gt;a2, &lt;/g&gt;&lt;h&gt;aibiúlacht = &lt;/h&gt;&lt;s&gt;aibí, -ocht.&lt;/s&gt;
 * </pre>
 * Represents that "aibiúil" is the same as "aibí", and "aibiúlacht" is the 
 * same as "aibíocht" (aibí-ocht).
 */
public class H extends Element {
    H(String s) {
    }
    public static H fromNode(Node n) throws Exception {
        return new H("FIXME");
    }
}
