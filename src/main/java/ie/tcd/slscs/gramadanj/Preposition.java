package ie.tcd.slscs.gramadanj;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Preposition {
    public String disambig = "";
    public String getNickname() {
        String ret = getLemma() + " prep";
        if (!"".equals(this.disambig)) {
            ret += " " + this.disambig;
        }
        ret = ret.replace(" ", "_");
        return ret;
    }
    public String lemma = "";
    public String getLemma() {
        return lemma;
    }
    List<Form> sg1;
    List<Form> sg2;
    List<Form> sg3Masc;
    List<Form> sg3Fem;
    List<Form> pl1;
    List<Form> pl2;
    List<Form> pl3;

    public Preposition() {
        sg1 = new ArrayList<Form>();
        sg2 = new ArrayList<Form>();
        sg3Masc = new ArrayList<Form>();
        sg3Fem = new ArrayList<Form>();
        pl1 = new ArrayList<Form>();
        pl2 = new ArrayList<Form>();
        pl3 = new ArrayList<Form>();
    }
    /**
     * Load a noun in bunamo xml
     * @param is
     * @throws Exception
     */
    public void loadPreposition(InputSource is) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);
        String root = doc.getDocumentElement().getNodeName();
        if (root != "preposition") {
            throw new IOException("Expected root node " + root);
        }
        this.lemma = doc.getDocumentElement().getAttribute("default").toString();
        String disambattr = doc.getDocumentElement().getAttribute("disambig");
        if(disambattr == null) {
            this.disambig = "";
        } else {
            this.disambig = disambattr;
        }
        NodeList nl = doc.getDocumentElement().getChildNodes();
        for(int i=0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            String nform = n.getNodeName();
            if(nform.equals("sg1")) {
                this.sg1.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sg2")) {
                this.sg2.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sg3Masc")) {
                this.sg3Masc.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sg3Fem")) {
                this.sg3Fem.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("pl1")) {
                this.pl1.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("pl2")) {
                this.pl2.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("pl3")) {
                this.pl3.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("#text")) {
                continue;
            } else {
                throw new IOException("Unexpected node: " + nform);
            }
        }
    }
    public void loadPreposition(InputStream is) throws Exception {
        this.loadPreposition(new InputSource(is));
    }
    public void loadPreposition(File f) throws Exception {
        this.loadPreposition(new FileInputStream(f));
    }
    public void loadPreposition(String filename) throws Exception {
        this.loadPreposition(new File(filename));
    }
}
