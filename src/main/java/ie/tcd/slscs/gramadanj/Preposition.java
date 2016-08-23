package ie.tcd.slscs.gramadanj;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Preposition extends PartOfSpeech {
    List<Form> sg1;
    List<Form> sg2;
    List<Form> sg3Masc;
    List<Form> sg3Fem;
    List<Form> pl1;
    List<Form> pl2;
    List<Form> pl3;

    public Preposition() {
        this.nickname_addition = " prep";
        sg1 = new ArrayList<Form>();
        sg2 = new ArrayList<Form>();
        sg3Masc = new ArrayList<Form>();
        sg3Fem = new ArrayList<Form>();
        pl1 = new ArrayList<Form>();
        pl2 = new ArrayList<Form>();
        pl3 = new ArrayList<Form>();
    }
    public Preposition(String filename) throws Exception {
        this();
        loadXML(filename);
    }
    /**
     * Load a noun in bunamo xml
     * @param is
     * @throws Exception
     */
    public void loadXML(InputSource is) throws Exception {
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
    public void writeXML(OutputStream os) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("preposition");
        root.setAttribute("default", getLemma());
        root.setAttribute("disambig", disambig);
        for(Form f : sg1) {
            Element e = doc.createElement("sg1");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sg2) {
            Element e = doc.createElement("sg2");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sg3Masc) {
            Element e = doc.createElement("sg3Masc");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sg3Fem) {
            Element e = doc.createElement("sg3Fem");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : pl1) {
            Element e = doc.createElement("pl1");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : pl2) {
            Element e = doc.createElement("pl2");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : pl3) {
            Element e = doc.createElement("pl3");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(os);
        transformer.transform(source, res);
    }
    public void writeXML(File f) throws Exception {
        writeXML(new FileOutputStream(f));
    }
    public void writeXML(String s) throws Exception {
        writeXML(new File(s));
    }
}
