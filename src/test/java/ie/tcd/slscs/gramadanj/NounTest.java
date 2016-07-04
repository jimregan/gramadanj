package ie.tcd.slscs.gramadanj;

import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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

}
