package ie.tcd.slscs.itut.gramadanj.FGB;
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
import java.util.Arrays;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static org.junit.Assert.*;

public class GTest {
    @Test
    public void checkGTest() throws Exception {
        Node na = Utils.stringToNode("<fake><g>m. (gs. </g><b>-ora, </b><g>pl. </g><b>~í</b>).</fake>");
        NodeList nla = na.getChildNodes();
        G ga = G.fromNode(nla.item(0));
        int consumeda = ga.consume(nla, 1);
        assertEquals(4, consumeda);
        assertEquals("m.", ga.getText());
        assertEquals("-ora", ga.getChildren().get("gs."));
        assertEquals("~í", ga.getChildren().get("pl."));
    }
}
