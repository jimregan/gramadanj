package ie.tcd.slscs.itut.gramadanj.EID;

import ie.tcd.slscs.itut.gramadanj.Utils;
import org.junit.Test;
import org.w3c.dom.Node;

import static org.junit.Assert.*;

/**
 * Created by jaoregan on 13/11/2016.
 */
public class EIDReaderTest {
    @Test
    public void isValencyNoIndex() throws Exception {
        final String ambig = "<noindex>(<src>a</src>, <trg>z</trg>; <src>b</src>, <trg>y</trg>)</noindex>";
        final String simple = "<noindex>(<src>a</src>, <trg>z</trg>)</noindex>";
        final String notvalency = "<noindex> (<label>m</label>) </noindex>";
        final Node namb = Utils.stringToNode(ambig);
        final Node nsimp = Utils.stringToNode(simple);
        final Node nsnot = Utils.stringToNode(notvalency);
        assertEquals(true, EIDReader.isValencyNoIndex(nsimp));
        assertEquals(true, EIDReader.isValencyNoIndex(namb));
        assertEquals(false, EIDReader.isValencyNoIndex(nsnot));
    }

}