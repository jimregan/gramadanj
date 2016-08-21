package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;
import org.xml.sax.InputSource;

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
        exp = new Noun(Features.Gender.Fem, "adharc", "adhairce", "", Features.Strength.Weak, "adharca", "adharc", "");
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
        assert(exp.equals(inp));
    }
}
