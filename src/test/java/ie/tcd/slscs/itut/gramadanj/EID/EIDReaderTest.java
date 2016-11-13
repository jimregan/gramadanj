package ie.tcd.slscs.itut.gramadanj.EID;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Created by jaoregan on 13/11/2016.
 */
public class EIDReaderTest {
    static Node stringToNode(String s) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(s)));
        String root = doc.getDocumentElement().getNodeName();
        Node n = doc.getDocumentElement().cloneNode(false);
        return n;
    }
    @Test
    public void isValencyNoIndex() throws Exception {
        final String ambig = "<noindex>(<src>a</src>, <trg>z</trg>; <src>b</src>, <trg>y</trg>)</noindex>";
        final String simple = "<noindex>(<src>a</src>, <trg>z</trg>)</noindex>";
        final String notvalency = "<noindex> (<label>m</label>) </noindex>";
        final Node namb = stringToNode(ambig);
        final Node nsimp = stringToNode(simple);
        final Node nsnot = stringToNode(notvalency);
        assertEquals(true, EIDReader.isValencyNoIndex(nsimp));
        assertEquals(true, EIDReader.isValencyNoIndex(namb));
        assertEquals(false, EIDReader.isValencyNoIndex(nsnot));
    }

}