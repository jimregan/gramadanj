package ie.tcd.slscs.itut.gramadanj;
/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 * 
 * Based on Gramadán:
 * The MIT License (MIT)
 *
 * Copyright © 2017 Foras na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Adjective extends PartOfSpeech {
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
                s = "ní b'" + Opers.Mutate(Features.Mutation.Len1, f.value);
            } else {
                s = "ní ba " + Opers.Mutate(Features.Mutation.Len1, f.value);
            }
            ret.add(new Form(s));
        }
        return ret;
    }

    public List<Form> getSuperPast() {
        List<Form> ret = new ArrayList<Form>();
        for (Form f : graded) {
            String s;
            if (f.value.matches("^[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
                s = "ab'" + f.value;
            } else if (f.value.matches("^f")) {
                s = "ab " + Opers.Mutate(Features.Mutation.Len1, f.value);
            } else {
                s = "ba " + Opers.Mutate(Features.Mutation.Len1, f.value);
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
        this.nickname_addition = " adj";
        disambig = "";
    }

    private void setLemma() {
        Form lemmaForm = this.sgNom.get(0);
        if (lemmaForm != null) {
            this.lemma= lemmaForm.value;
        } else {
            this.lemma = "";
        }
    }

    public Adjective(SingularInfo sgMasc, SingularInfo sgFem, String plural, String graded) {
        this();
        this.sgNom = sgMasc.nom;
        setLemma();
        this.sgGenMasc = sgMasc.gen;
        this.sgGenFem = sgFem.gen;
        this.sgVocMasc = sgMasc.voc;
        this.sgVocFem = sgFem.voc;
        if (plural != null) {
            this.plNom.add(new Form(plural));
        }
        if(graded != null) {
            this.graded.add(new Form(graded));
        }
    }
    
    public Adjective(SingularInfo sgMasc, SingularInfo sgFem, String graded) {
        this(sgMasc, sgFem, null, graded);
    }

    public static Adjective create(SingularInfo sgMasc, SingularInfo sgFem, String plural, String graded) {
        return new Adjective(sgMasc, sgFem, plural, graded);
    }

    public void loadXML(InputSource is) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder(); 
        Document doc = docBuilder.parse(is); 
        String root = doc.getDocumentElement().getNodeName(); 
        if (!root.equals("adjective")) {
            throw new IOException("Expected root node " + root); 
        } 
        String wdefault = doc.getDocumentElement().getAttribute("default");
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
            } else if(nform.equals("#text")) {
                continue;
            } else {
                throw new IOException("Unexpected node: " + nform);
            }
        }
        setLemma();
    }
    public Adjective(String filename) throws Exception {
        this();
        this.loadXML(filename);
    }
    public void writeXML(OutputStream os) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder(); 
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("adjective");
        root.setAttribute("default", getLemma());
        root.setAttribute("declension", Integer.toString(declension));
        root.setAttribute("disambig", disambig);
        if(isPre) {
            root.setAttribute("isPre", "True");
        }
        for(Form f : sgNom) {
            Element e = doc.createElement("sgNom");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sgGenMasc) {
            Element e = doc.createElement("sgGenMasc");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sgGenFem) {
            Element e = doc.createElement("sgGenFem");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sgVocMasc) {
            Element e = doc.createElement("sgVocMasc");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : sgVocFem) {
            Element e = doc.createElement("sgVocFem");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : plNom) {
            Element e = doc.createElement("plNom");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : graded) {
            Element e = doc.createElement("graded");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : abstractNoun) {
            Element e = doc.createElement("abstractNoun");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(os);
        transformer.transform(source, res);
    }
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(this == o) {
            return true;
        }
        if(!(o instanceof Adjective)) {
            return false;
        }
        final Adjective a = (Adjective) o;
        if(a.declension != declension) {
            return false;
        } else if(!a.disambig.equals(disambig)) {
            return false;
        } else if(a.isPre != isPre) {
            return false;
        } else if(!Utils.equalLists(a.sgNom, sgNom)) {
            return false;
        } else if(!Utils.equalLists(a.sgGenMasc, sgGenMasc)) {
            return false;
        } else if(!Utils.equalLists(a.sgGenFem, sgGenFem)) {
            return false;
        } else if(!Utils.equalLists(a.sgVocMasc, sgVocMasc)) {
            return false;
        } else if(!Utils.equalLists(a.sgVocFem, sgVocFem)) {
            return false;
        } else if(!Utils.equalLists(a.plNom, plNom)) {
            return false;
        } else if(!Utils.equalLists(a.graded, graded)) {
            return false;
        } else if(!Utils.equalLists(a.abstractNoun, abstractNoun)) {
            return false;
        } else {
            return true;
        }
    }
}
