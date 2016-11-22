package ie.tcd.slscs.itut.gramadanj;
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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import junit.framework.TestCase;

public class AinmfhocailExample extends TestCase {
    public void createFile() throws Exception {
        String abairt = "<?xml version='1.0' encoding='utf-8'?>\n" +
                "<noun default=\"abairt\" declension=\"2\" disambig=\"\" isProper=\"0\" isDefinite=\"0\" allowArticledGenitive=\"0\">\n" +
                "  <sgNom default=\"abairt\" gender=\"fem\" />\n" +
                "  <sgGen default=\"abairte\" gender=\"fem\" />\n" +
                "  <plNom default=\"abairtí\" />\n" +
                "  <plGen default=\"abairtí\" strength=\"strong\" />\n" +
                "</noun>";
        
        OutputStream fos = new FileOutputStream("abairt_fem2.xml");
        OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(abairt);
        bw.close();
    }
    public AinmfhocailExample() throws Exception {
        createFile();
    }
    public void example() throws Exception {
        Noun abairtN = new Noun("abairt_fem2.xml");
        NP abairtNP = new NP(abairtN);
        System.out.println(abairtNP.sgNomArt.get(0).value);
        System.out.println(abairtNP.sgGenArt.get(0).value);
        System.out.println(abairtNP.plNomArt.get(0).value);
        System.out.println(abairtNP.plGenArt.get(0).value);
    }
    public void testExample() throws Exception {
        Noun abairtN = new Noun("abairt_fem2.xml");
        NP abairtNP = new NP(abairtN);
        assertEquals("abairt", abairtN.sgNom.get(0).value);
        assertEquals("an abairt", abairtNP.sgNomArt.get(0).value);
        assertEquals("na habairte", abairtNP.sgGenArt.get(0).value);
        assertEquals("na habairtí", abairtNP.plNomArt.get(0).value);
        assertEquals("na n-abairtí", abairtNP.plGenArt.get(0).value);
    }
}
