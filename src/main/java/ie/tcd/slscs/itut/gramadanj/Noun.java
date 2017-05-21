package ie.tcd.slscs.itut.gramadanj;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.itut.gramadanj.Features.Gender;
import ie.tcd.slscs.itut.gramadanj.Features.Strength;
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

public class Noun extends PartOfSpeech {
    public String getFriendlyNickname() {
        String ret = getLemma();
        ret += " (";
        ret += (this.getGender() == Features.Gender.Masc ? "masc" : "fem");
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
    
    public Gender getGender() {
        return this.sgNom.get(0).gender;
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
            if(sgVoc == null) {
                sgVoc = new ArrayList<FormSg>();
            }
            if(plVoc == null) {
                plVoc = new ArrayList<Form>();
            }
        }
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

    private void setLemma() {
        this.nickname_addition = (this.getGender() == Features.Gender.Masc ? " masc" : " fem");
        Form lemmaForm = this.sgNom.get(0);
        if (lemmaForm != null) {
            this.lemma = lemmaForm.value;
        } else {
            this.lemma = "";
        }
    }
    public Noun(Gender gender, String sgNom, String sgGen, String sgVoc, 
                Strength strength, String plNom, String plGen, String plVoc) {
        this();
        this.sgNom.add(new FormSg(sgNom, gender));
        this.sgGen.add(new FormSg(sgGen, gender));
        if(sgVoc != null && !sgVoc.equals("")) {
            this.sgVoc.add(new FormSg(sgVoc, gender));
        }
        this.sgDat.add(new FormSg(sgNom, gender));
        this.plNom.add(new Form(plNom));
        this.plGen.add(new FormPlGen(plGen, strength));
        if(plVoc != null && !plVoc.equals("")) {
            this.plVoc.add(new Form(plVoc));
        }
        setLemma();
    }
    public Noun(String filename) throws Exception {
        this();
        this.loadXML(filename);
        setLemma();
    }
    public Noun(Noun other) {
        this();
        this.isDefinite = other.isDefinite;
        this.lemma = other.lemma;
        this.isImmutable = other.isImmutable;
        this.allowArticledGenitive = other.allowArticledGenitive;
        this.nickname_addition = other.nickname_addition;
        this.declension = other.declension;
        this.isProper = other.isProper;
        this.disambig = other.disambig;
        this.sgNom.addAll(other.sgNom);
        this.sgGen.addAll(other.sgGen);
        this.sgDat.addAll(other.sgDat);
        this.sgVoc.addAll(other.sgVoc);
        this.plNom.addAll(other.plNom);
        this.plGen.addAll(other.plGen);
        this.plVoc.addAll(other.plVoc);
        this.count.addAll(other.count);
    }
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(this == o) {
            return true;
        }
        if(!(o instanceof Noun)) {
            return false;
        }
        final Noun n = (Noun) o;
        if(n.declension != declension) {
            return false;
        } else if(!n.disambig.equals(disambig)) {
            return false;
        } else if(n.allowArticledGenitive != allowArticledGenitive) {
            return false;
        } else if(n.isDefinite != isDefinite) {
            return false;
        } else if(n.isImmutable != isImmutable) {
            return false;
        } else if(n.isProper != isProper) {
            return false;
        } else if(!Utils.equalLists(n.sgNom, sgNom)) {
            return false;
        } else if(!Utils.equalLists(n.sgGen, sgGen)) {
            return false;
        } else if(!Utils.equalLists(n.sgDat, sgDat)) {
            return false;
        } else if(!Utils.equalLists(n.sgVoc, sgVoc)) {
            return false;
        } else if(!Utils.equalLists(n.plNom, plNom)) {
            return false;
        } else if(!Utils.equalLists(n.plGen, plGen)) {
            return false;
        } else if(!Utils.equalLists(n.plVoc, plVoc)) {
            return false;
        } else if(!Utils.equalLists(n.count, count)) {
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
    public void writeXML(OutputStream os) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder(); 
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("noun");
        root.setAttribute("default", getLemma());
        root.setAttribute("declension", Integer.toString(declension));
        root.setAttribute("disambig", disambig);
        if(isProper) {
            root.setAttribute("isProper", "1");
        }
        if(isImmutable) {
            root.setAttribute("isImmutable", "1");
        }
        if(isDefinite) {
            root.setAttribute("isDefinite", "1");
        }
        if(allowArticledGenitive) {
            root.setAttribute("allowArticledGenitive", "1");
        }
        for(FormSg f : sgNom) {
            Element e = doc.createElement("sgNom");
            e.setAttribute("default", f.value);
            e.setAttribute("gender", (f.gender == Gender.Masc) ? "masc" : "fem");
            root.appendChild(e);
        }
        for(FormSg f : sgGen) {
            Element e = doc.createElement("sgGen");
            e.setAttribute("default", f.value);
            e.setAttribute("gender", (f.gender == Gender.Masc) ? "masc" : "fem");
            root.appendChild(e);
        }
        for(FormSg f : sgVoc) {
            Element e = doc.createElement("sgVoc");
            e.setAttribute("default", f.value);
            e.setAttribute("gender", (f.gender == Gender.Masc) ? "masc" : "fem");
            root.appendChild(e);
        }
        for(FormSg f : sgDat) {
            Element e = doc.createElement("sgDat");
            e.setAttribute("default", f.value);
            e.setAttribute("gender", (f.gender == Gender.Masc) ? "masc" : "fem");
            root.appendChild(e);
        }
        for(Form f : plNom) {
            Element e = doc.createElement("plNom");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(FormPlGen f : plGen) {
            Element e = doc.createElement("plGen");
            e.setAttribute("default", f.value);
            e.setAttribute("strength", (f.strength == Strength.Strong) ? "strong" : "weak");
            root.appendChild(e);
        }
        for(Form f : plVoc) {
            Element e = doc.createElement("plVoc");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : count) {
            Element e = doc.createElement("count");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(os);
        transformer.transform(source, res);
    }
}
