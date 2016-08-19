package ie.tcd.slscs.gramadanj;
import java.io.StringReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.gramadanj.Form;
import ie.tcd.slscs.gramadanj.Form.Gender;
import ie.tcd.slscs.gramadanj.Form.Strength;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Noun {
    public String disambig = "";
    public String getNickname() {
        String ret = getLemma();
        ret += (this.getGender() == Gender.Masc ? " masc" : " fem");
        if (!"".equals(this.disambig)) {
            ret += " " + this.disambig;
        }
        ret = ret.replace(" ", "_");
        return ret;
    }
    public String getFriendlyNickname() {
        String ret = getLemma();
        ret += " (";
        ret += (this.getGender() == Gender.Masc ? "masc" : "fem");
        ret += (this.declension > 0 ? this.declension : "");
        if(!"".equals(this.disambig)) {
            ret += " " + this.disambig;
        }
        ret += ")";
        return ret;
    }
	
    public int declension = 0;
    public List<FormSg> sgNom;
    public List<FormSg> sgGen;
	public List<FormSg> sgVoc;
	public List<FormSg> sgDat;

	public List<Form> plNom;
	public List<FormPlGen> plGen;
	public List<Form> plVoc;

	public List<Form> count;
	
	public boolean isProper = false;
	public boolean isImmutable = false;
	public boolean isDefinite = false;
	public boolean allowArticledGenitive = false;
	
    public String getLemma() {
        String ret = "";
        Form lemmaForm = this.sgNom.get(0);
        if (lemmaForm != null) {
            ret = lemmaForm.value;
        }
        return ret;
    }
	
    public Gender getGender() {
        return this.sgNom.get(0).gender;
    }

    /**
     * Load a noun in bunamo xml
     * @param is 
     * @throws Exception
     */
    public void loadNoun(InputSource is) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);
        String root = doc.getDocumentElement().getNodeName();
        if (root != "noun") {
            throw new IOException("Expected root node " + root);
        }
        String wdefault = doc.getDocumentElement().getAttribute("default").toString();
        this.isImmutable = Utils.getBooleanAttr(doc, "isImmutable");
        this.isDefinite = Utils.getBooleanAttr(doc, "isDefinite");
        this.isProper = Utils.getBooleanAttr(doc, "isProper");
        this.allowArticledGenitive = Utils.getBooleanAttr(doc, "allowArticledGenitive");
        String declattr = doc.getDocumentElement().getAttribute("declension");
        if(declattr == null) {
            throw new IOException("declension attribute missing");
        } else {
            this.declension = Integer.parseInt(declattr);
        }
        String disambattr = doc.getDocumentElement().getAttribute("disambig");
        if(disambattr == null) {
            throw new IOException("disambig attribute missing");
        } else {
            this.disambig = disambattr;
        }
        NodeList nl = doc.getDocumentElement().getChildNodes();
        for(int i=0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            String nform = n.getNodeName();
            if(nform.equals("sgNom")) {
            	this.sgNom.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("sgGen")) {
            	this.sgGen.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("sgDat")) {
            	this.sgDat.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("sgVoc")) {
            	this.sgVoc.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("plNom")) {
            	this.plNom.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("plGen")) {
            	this.plGen.add(new FormPlGen(Utils.getDefault(n), Utils.getStrength(n)));
            } else if(nform.equals("plVoc")) {
            	this.plVoc.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("count")) {
                this.count.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("#text")) {
                continue;
            } else {
            	throw new IOException("Unexpected node: " + nform);
            }
            if(this.sgDat.size() == 0 && this.sgNom.size() != 0) {
                sgDat.addAll(sgNom);
            }
        }
    }
    public void loadNoun(InputStream is) throws Exception {
        this.loadNoun(new InputSource(is));
    }
    public void loadNoun(File f) throws Exception {
        this.loadNoun(new FileInputStream(f));
    }
    public void loadNoun(String filename) throws Exception {
        this.loadNoun(new File(filename));
    }

	public Noun() {
        sgNom = new ArrayList<FormSg>();
        sgGen = new ArrayList<FormSg>();
        sgVoc = new ArrayList<FormSg>();
        sgDat = new ArrayList<FormSg>();

        plNom = new ArrayList<Form>();
        plGen = new ArrayList<FormPlGen>();
        plVoc = new ArrayList<Form>();

        count = new ArrayList<Form>();
    }
    public Noun(Gender gender, String sgNom, String sgGen, String sgVoc, 
                Strength strength, String plNom, String plGen, String plVoc) {
        this();
        this.sgNom.add(new FormSg(sgNom, gender));
        this.sgGen.add(new FormSg(sgGen, gender));
        this.sgVoc.add(new FormSg(sgVoc, gender));
        this.sgDat.add(new FormSg(sgNom, gender));
        this.plNom.add(new Form(plNom));
        this.plGen.add(new FormPlGen(plGen, strength));
        this.plVoc.add(new Form(plVoc));
    }
    public Noun(String filename) throws Exception {
        this();
        this.loadNoun(filename);
    }
    public boolean equals(Noun n) {
        if(!n.getLemma().equals(getLemma())) {
            return false;
        } else if(n.declension != declension) {
            return false;
        } else if(n.allowArticledGenitive != allowArticledGenitive) {
            return false;
        } else if(n.isDefinite != isDefinite) {
            return false;
        } else if(n.isImmutable != isImmutable) {
            return false;
        } else if(n.isProper != isProper) {
            return false;
        } else if(n.sgNom.size() != sgNom.size() || !n.sgNom.containsAll(sgNom)) {
            return false;
        } else if(n.sgGen.size() != sgGen.size() || !n.sgGen.containsAll(sgGen)) {
            return false;
        } else if(n.sgDat.size() != sgDat.size() || !n.sgDat.containsAll(sgDat)) {
            return false;
        } else if(n.sgVoc.size() != sgVoc.size() || !n.sgVoc.containsAll(sgVoc)) {
            return false;
        } else if(n.plNom.size() != plNom.size() || !n.plNom.containsAll(plNom)) {
            return false;
        } else if(n.plGen.size() != plGen.size() || !n.plGen.containsAll(plGen)) {
            return false;
        } else if(n.plVoc.size() != plVoc.size() || !n.plVoc.containsAll(plVoc)) {
            return false;
        } else if(n.count.size() != count.size() || !n.count.containsAll(count)) {
            return false;
        } else {
            return true;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sgNom: ");
        for(Form f : sgNom) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        sb.append("sgGen: ");
        for(Form f : sgGen) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        sb.append("sgDat: ");
        for(Form f : sgDat) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        sb.append("sgVoc: ");
        for(Form f : sgVoc) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        sb.append("plNom: ");
        for(Form f : plNom) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        sb.append("plGen: ");
        for(Form f : plGen) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        sb.append("plVoc: ");
        for(Form f : plVoc) {
            sb.append('[');
            sb.append(f.value);
            sb.append(']');
        }
        sb.append('\n');
        return sb.toString();
    }
}
