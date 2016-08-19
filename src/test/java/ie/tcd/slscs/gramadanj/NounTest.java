package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ie.tcd.slscs.gramadanj.Form.Gender;
import ie.tcd.slscs.gramadanj.Form.Strength;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class NounTest extends TestCase {
    private String adharc
            = "<?xml version='1.0' encoding='utf-8'?>\n" +
            "<noun default=\"adharc\" declension=\"2\" disambig=\"\" isProper=\"0\" isDefinite=\"0\" allowArticledGenitive=\"0\">\n" +
            "  <sgNom default=\"adharc\" gender=\"fem\" />\n" +
            "  <sgGen default=\"adhairce\" gender=\"fem\" />\n" +
            "  <plNom default=\"adharca\" />\n" +
            "  <plGen default=\"adharc\" strength=\"weak\" />\n" +
            "</noun>\n";
    private Noun exp;
    public NounTest() {
        exp = new Noun(Gender.Fem, "adharc", "adhairce", "", Strength.Weak, "adharca", "adharc", "");
        exp.declension = 2;
    }
    public void testGetLemma() {
        assertEquals("adharc", exp.getLemma());
    }
    public void testLoadNoun() {
        Noun inp = new Noun();
        try {
            inp.loadNoun(new InputSource(new StringReader(adharc)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        //assertEquals(exp.toString(), inp.toString());
        assert(exp.equals(inp));
    }
}
