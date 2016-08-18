package ie.tcd.slscs.gramadanj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ie.tcd.slscs.gramadanj.Form;
import ie.tcd.slscs.gramadanj.Form.Gender;
import ie.tcd.slscs.gramadanj.Form.Strength;
import ie.tcd.slscs.gramadanj.Form.Mutation;
import ie.tcd.slscs.gramadanj.Opers;

public class Adjective {
    public String disambig = "";
    public int declension = 0;

    public List<Form> sgNom;
    public List<Form> sgGenMasc;
    public List<Form> sgGenFem;
    public List<Form> sgVocMasc;
    public List<Form> sgVocFem;
    public List<Form> plNom;
    public List<Form> graded;
    public List<Form> abstractNoun;
    public boolean isPre = false;

    public String getNickname() {
        String ret = getLemma();
        ret += " adj";
        ret += (this.declension > 0 ? this.declension : "");
        
        if (this.disambig != "") {
            ret += " " + this.disambig;
        }
        ret = ret.replace(" ", "_");
        return ret;
    }
    
    public String getFriendlyNickname() {
        String ret = getLemma();
        ret += "(adj";
        ret += (this.declension > 0 ? this.declension : "");

        if (this.disambig != "") {
            ret += " " + this.disambig;
        }
        ret += ")";

        return ret;
    }
    
    public String getLemma() {
        String ret = "";
        Form lemmaForm = this.sgNom.get(0);
        if (lemmaForm != null) {
            ret = lemmaForm.value;
        }
        return ret;
    }

    public List<Form> getComparPres() {
        List<Form> ret = new ArrayList<Form>();
        for (Form f : graded) {
            ret.add(new Form("níos " + f.value));
        }
        return ret;
    }

    public List<Form> getSuperPres() {
        List<Form> ret = new ArrayList<Form>();
        for (Form f : graded) {
            ret.add(new Form("is " + f.value));
        }
        return ret;
    }

    public List<Form> getComparPast() {
        List<Form> ret = new ArrayList<Form>();
        for (Form f : graded) {
            String s = "";
            if (f.value.matches("^[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
                s = "ní b'" + f.value;
            } else if (f.value.matches("^f[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
                s = "ní b'" + Opers.Mutate(Mutation.Len1, f.value);
            } else {
                s = "ní ba " + Opers.Mutate(Mutation.Len1, f.value);
            }
            ret.add(new Form(s));
        }
        return ret;
    }

    public List<Form> getSuperPast() {
        List<Form> ret = new ArrayList<Form>();
        for (Form f : graded) {
            String s = "";
            if (f.value.matches("^[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
                s = "ab'" + f.value;
            } else if (f.value.matches("^f")) {
                s = "ab " + Opers.Mutate(Mutation.Len1, f.value);
            } else {
                s = "ba " + Opers.Mutate(Mutation.Len1, f.value);
            }
            ret.add(new Form(s));
        }
        return ret;
    }

    public Adjective() {
        sgNom = new ArrayList<Form>();
        sgGenMasc = new ArrayList<Form>();
        sgGenFem = new ArrayList<Form>();
        sgVocMasc = new ArrayList<Form>();
        sgVocFem = new ArrayList<Form>();
        plNom = new ArrayList<Form>();
        graded = new ArrayList<Form>();
        abstractNoun = new ArrayList<Form>();
    }

    public Adjective(SingularInfo sgMasc, SingularInfo sgFem, String plural, String graded) {
        this();
        this.sgNom = sgMasc.nom;
        this.sgGenMasc = sgMasc.gen;
        this.sgGenFem = sgFem.gen;
        this.sgVocMasc = sgMasc.voc;
        this.sgVocFem = sgFem.voc;
        if (plural != null) {
            this.plNom.add(new Form(plural));
        }
    }
    
    public Adjective(SingularInfo sgMasc, SingularInfo sgFem, String graded) {
        this(sgMasc, sgFem, null, graded);
    }

    public static Adjective create(SingularInfo sgMasc, SingularInfo sgFem, String plural, String graded) {
        return new Adjective(sgMasc, sgFem, plural, graded);
    }

    public void loadAdjective(InputSource is) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder(); 
        Document doc = docBuilder.parse(is); 
        String root = doc.getDocumentElement().getNodeName(); 
        if (root != "adjective") { 
            throw new IOException("Expected root node " + root); 
        } 
        String wdefault = doc.getDocumentElement().getAttribute("default").toString();
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
        String curattr = doc.getDocumentElement().getAttribute("isPre");
        if(curattr != null && curattr.toLowerCase().equals("true")) {
            isPre = true;
        } else {
            isPre = false;
        }
        NodeList nl = doc.getDocumentElement().getChildNodes();
        for(int i=0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            String nform = n.getNodeName();
            if(nform.equals("sgNom")) {
                this.sgNom.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sgGenMasc")) {
                this.sgGenMasc.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sgGenFem")) {
                this.sgGenFem.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sgVocMasc")) {
                this.sgVocMasc.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("sgVocFem")) {
                this.sgVocFem.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("plNom")) {
                this.plNom.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("graded")) {
                this.graded.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("abstractNoun")) {
                this.abstractNoun.add(new Form(Utils.getDefault(n)));
            } else {
                throw new IOException("Unexpected node: " + nform);
            }
        }
    }
    public void loadAdjective(InputStream is) throws Exception {
        this.loadAdjective(new InputSource(is));
    }
    public void loadAdjective(File f) throws Exception {
        this.loadAdjective(new FileInputStream(f));
    }
    public void loadAdjective(String filename) throws Exception {
        this.loadAdjective(new File(filename));
    }
}
