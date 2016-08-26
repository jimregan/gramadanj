package ie.tcd.slscs.gramadanj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.Charset;

import org.xml.sax.InputSource;

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
