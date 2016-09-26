package ie.tcd.slscs.gramadanj.briathra;

import junit.framework.TestCase;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class QuoteTest extends TestCase {
    final String frag = "<beleg korpus=\"CC\" quelle=\"RM-4-08-01\" xml:space=\"preserve\">Tiomáin leat, <lemma xml:space=\"preserve\">abair</lemma> leat.</beleg>";
    private List<QuoteFragment> qf;
    private Node inNode;
    public QuoteTest() throws Exception {
        qf = new ArrayList<QuoteFragment>();
        qf.add(new QuoteFragment("Tiomáin leat, "));
        qf.add(new QuoteFragment("abair", true));
        qf.add(new QuoteFragment(" leat."));
        inNode = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new ByteArrayInputStream(frag.getBytes())).getDocumentElement();
    }
    public void testFromNode() throws Exception {
        Quote exp = new Quote("RM-4-08-01", "CC", qf);
        Quote inp = Quote.fromNode(inNode);
        assertEquals(exp, inp);
        qf.add(new QuoteFragment("break it!"));
        assert(!exp.equals(inp));
    }

}