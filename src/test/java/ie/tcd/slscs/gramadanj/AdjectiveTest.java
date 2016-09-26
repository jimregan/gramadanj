package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;
import org.xml.sax.InputSource;

import java.io.StringReader;

public class AdjectiveTest extends TestCase {
    private String abarach
            = "<?xml version='1.0' encoding='utf-8'?>\n" +
                    "<adjective default=\"abarach\" declension=\"1\" disambig=\"\" isPre=\"False\">\n" +
                    "  <sgNom default=\"abarach\" />\n" +
                    "  <sgGenMasc default=\"abaraigh\" />\n" +
                    "  <sgGenFem default=\"abaraí\" />\n" +
                    "  <plNom default=\"abaracha\" />\n" +
                    "  <graded default=\"abaraí\" />\n" +
                    "</adjective>";
    private Adjective exp;
    public AdjectiveTest() {
        SingularInfo sgm = new SingularInfoSimple("abarach", "abaraigh");
        SingularInfo sgf = new SingularInfoSimple("abarach", "abaraí");
        exp = new Adjective(sgm, sgf, "abaracha", "abaraí");
        exp.declension = 1;
        exp.isPre = false;
    }
    public void testGetLemma() {
        assertEquals("abarach", exp.getLemma());
    }
    public void testLoadAdjective() {
        Adjective inp = new Adjective();
        try {
            inp.loadXML(new InputSource(new StringReader(abarach)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        assertEquals(exp, inp);
    }
}
