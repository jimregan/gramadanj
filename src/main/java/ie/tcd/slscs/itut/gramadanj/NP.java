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
import ie.tcd.slscs.itut.gramadanj.Features.Mutation;
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

public class NP extends PartOfSpeech {
    public List<FormSg> sgNom = new ArrayList<FormSg>();
    public List<FormSg> sgGen = new ArrayList<FormSg>();
    public List<FormSg> sgNomArt = new ArrayList<FormSg>();
    public List<FormSg> sgGenArt = new ArrayList<FormSg>();
    public List<Form> plNom = new ArrayList<Form>();
    public List<Form> plGen = new ArrayList<Form>();
    public List<Form> plNomArt = new ArrayList<Form>();
    public List<Form> plGenArt = new ArrayList<Form>();
    
    public boolean isDefinite = false;
    public boolean forceNominative = false;
    
    public String getLemma() {
        String ret="";

        if (sgNom.size() != 0) {
            ret = sgNom.get(0).value;
        } else {
            if (sgNomArt.size() != 0) {
                ret = sgNomArt.get(0).value;
            } else if (plNom.size() != 0) {
                ret = plNom.get(0).value;
            } else if (plNomArt.size() != 0) {
                ret = plNomArt.get(0).value;
            }
        }
        return ret;
    }
    
    public Gender getGender() {
        Gender ret = Features.Gender.Masc;
        if (sgNom.size() != 0) {
            ret = sgNom.get(0).gender;
        } else if (sgNomArt.size() != 0) {
            ret = sgNomArt.get(0).gender;
        }
        return ret;
    }
    
    public boolean hasGender() {
        boolean ret = false;
        if (sgNom.size() != 0 || sgNomArt.size() != 0) {
            ret = true;
        }
        return ret;
    }

    NP() {
        this.nickname_addition = " NP";
    }
    NP(Gender gender, String sgNom, String sgGen, String plNom, String plGen) {
        this.sgNom.add(new FormSg(sgNom, gender));
        Mutation mut = (gender == Features.Gender.Masc) ? Features.Mutation.PrefT : Features.Mutation.Len3;
        String value = "an " + Opers.Mutate(mut, sgNom);
        this.sgNomArt.add(new FormSg(value, gender));

        // This is intentional, as the nominative is more common than the genitive
        // when not following the article
        this.sgGen.add(new FormSg(sgNom, gender));

        mut = (gender == Features.Gender.Masc ? Features.Mutation.Len3 : Features.Mutation.PrefH);
        value = (gender == Features.Gender.Masc ? "an" : "na") + " " + Opers.Mutate(mut, sgGen);
        this.sgGenArt.add(new FormSg(value, gender));

        this.plNomArt.add(new Form(plNom));
        
        value = "na " + Opers.Mutate(Features.Mutation.PrefH, plNom);
        this.plNomArt.add(new Form(value));

        // This is intentional, as the nominative is more common than the genitive
        // when not following the article
        this.plGen.add(new Form(plNom));
        
        value = "na " + Opers.Mutate(Features.Mutation.Ecl1, plGen);
        this.plGenArt.add(new Form(value));
    }
    
    NP(Noun head) {
        this.isDefinite = head.isDefinite;
        for(FormSg f : head.sgNom) {
            this.sgNom.add(new FormSg(f.value, f.gender));
            if(!head.isDefinite) {
                Mutation m = f.gender == Gender.Masc ? Mutation.PrefT : Mutation.Len3;
                if(head.isImmutable) {
                    m = Mutation.None;
                }
                String tmp = "an " + Opers.Mutate(m, f.value);
                this.sgNomArt.add(new FormSg(tmp, f.gender));
            }
        }
        for(FormSg f : head.sgGen) {
            Mutation m = head.isProper ? Mutation.Len1 : Mutation.None;
            if(head.isImmutable) {
                m = Mutation.None;
            }
            String tmp = Opers.Mutate(m, f.value);
            this.sgGen.add(new FormSg(tmp, f.gender));
             
            if(!head.isDefinite || head.allowArticledGenitive) {
                m = f.gender == Gender.Masc ? Mutation.Len3 : Mutation.PrefH;
                if(head.isImmutable) {
                    m = Mutation.None;
                }
                String article = (f.gender == Gender.Masc) ? "an" : "na";
                tmp = article + " " + Opers.Mutate(m, f.value);
                this.sgGenArt.add(new FormSg(tmp, f.gender));
            }
        }
        for(Form f : head.plNom) {
            this.plNom.add(new Form(f.value));
            if(!head.isDefinite) {
                Mutation m = Mutation.PrefH;
                if(head.isImmutable) {
                    m = Mutation.None;
                }
                String tmp = "na " + Opers.Mutate(m, f.value);
                this.plNomArt.add(new Form(tmp));
            }
        }
        for(Form f : head.plGen) {
            Mutation m = head.isProper ? Mutation.Len1 : Mutation.None;
            if(head.isImmutable) {
                m = Mutation.None;
            }
            String tmp = Opers.Mutate(m, f.value);
            this.plGen.add(new Form(tmp));
            if(!head.isDefinite || head.allowArticledGenitive) {
                m = Mutation.Ecl1;
                if(head.isImmutable) {
                    m = Mutation.None;
                }
                tmp = "na " + Opers.Mutate(m, f.value);
                this.plGenArt.add(new Form(tmp));
            }
        }
    }
    public NP(Noun head, Adjective mod) {
        if(mod.isPre) {
            Noun prefixedHead = new Noun(head);
            String prefix = mod.getLemma();
            for (FormSg f : prefixedHead.sgNom) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (FormSg f : prefixedHead.sgGen) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (FormSg f : prefixedHead.sgDat) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (FormSg f : prefixedHead.sgVoc) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (Form f : prefixedHead.plNom) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (Form f : prefixedHead.plGen) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (Form f : prefixedHead.plVoc) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            for (Form f : prefixedHead.count) {
                f.value = Opers.Prefix(prefix, f.value);
            }
            NP np = new NP(prefixedHead);
            this.isDefinite = np.isDefinite;
            this.sgNom = np.sgNom;
            this.sgGen = np.sgGen;
            this.sgNomArt = np.sgNomArt;
            this.sgGenArt = np.sgGenArt;
            this.plNom = np.plNom;
            this.plGen = np.plGen;
            this.plNomArt = np.plNomArt;
            this.plGenArt = np.plGenArt;
        } else {
            this.isDefinite = head.isDefinite;
            this.forceNominative = true;
            for (FormSg nomf : head.sgNom) {
                for (Form adjf : mod.sgNom) {
                    String tmpa = adjf.value;
                    if(nomf.gender == Gender.Fem) {
                        tmpa = Opers.Mutate(Mutation.Len1, tmpa);
                    }
                    this.sgNom.add(new FormSg(nomf.value + " " + tmpa, nomf.gender));
                    if(!head.isDefinite) {
                        Mutation mutN = (nomf.gender == Gender.Masc) ? Mutation.PrefT : Mutation.Len3;
                        if(head.isImmutable) {
                            mutN = Mutation.None;
                        }
                        String tmpn = Opers.Mutate(mutN, nomf.value);
                        this.sgNomArt.add(new FormSg("an " + tmpn + " " + tmpa, nomf.gender));
                    }
                }
            }
            for(FormSg nomf : head.sgGen) {
                List<Form> tmpl = (nomf.gender == Gender.Masc ? mod.sgGenMasc : mod.sgGenFem);
                for(Form adjf : tmpl) {
                    String tmpa = adjf.value;
                    if(nomf.gender == Gender.Fem) {
                        tmpa = Opers.Mutate(Mutation.Len1, tmpa);
                    }
                    String tmpnf = nomf.value;
                    if(head.isProper && !head.isImmutable) {
                        tmpnf = Opers.Mutate(Mutation.Len1, nomf.value);
                    }
                    this.sgGen.add(new FormSg(tmpnf + " " + tmpa, nomf.gender));
                    if(!head.isDefinite || head.allowArticledGenitive) {
                        Mutation mutN = (nomf.gender == Gender.Masc) ? Mutation.Len3 : Mutation.PrefH;
                        String article = nomf.gender == Gender.Masc ? "an" : "na";
                        if(head.isImmutable) {
                            mutN = Mutation.None;
                        }
                        String tmpn = Opers.Mutate(mutN, nomf.value);
                        this.sgGenArt.add(new FormSg(article + " " + tmpn + " " + tmpa, nomf.gender));
                    }
                }
            }
            for(Form nomf : head.plNom) {
                for (Form adjf : mod.plNom) {
                    String tmpa = Opers.Mutate(Opers.isSlender(nomf.value) ? Mutation.Len1 : Mutation.None, adjf.value);
                    this.plNom.add(new Form(nomf.value + " " + tmpa));
                    if(!head.isDefinite) {
                        Mutation mutN = Mutation.PrefH;
                        if(head.isImmutable) {
                            mutN = Mutation.None;
                        }
                        this.plNomArt.add(new Form("na " + Opers.Mutate(mutN, nomf.value) + " " + tmpa));
                    }
                }
            }
            for(FormPlGen nomf : head.plGen) {
                List<Form> tmpf = (nomf.strength == Features.Strength.Strong) ? mod.plNom : mod.sgNom;
                for (Form adjf : tmpf) {
                    String tmpa = Opers.Mutate(Opers.isSlender(nomf.value) ? Mutation.Len1 : Mutation.None, adjf.value);
                    this.plGen.add(new FormPlGen(nomf.value + " " + tmpa, nomf.strength));
                    if(!head.isDefinite) {
                        Mutation mutN = Mutation.Ecl1;
                        if(head.isImmutable) {
                            mutN = Mutation.None;
                        }
                        this.plGenArt.add(new Form("na " + Opers.Mutate(mutN, nomf.value) + " " + tmpa));
                    }
                }
            }
        }
    }

    public void loadXML(InputSource is) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);
        String root = doc.getDocumentElement().getNodeName();
        if (root != "nounPhrase") {
            throw new IOException("Expected root node " + root);
        }
        String wdefault = doc.getDocumentElement().getAttribute("default").toString();
        this.isDefinite = Utils.getBooleanAttr(doc, "isDefinite");
        this.forceNominative = Utils.getBooleanAttr(doc, "forceNominative");
        String disambattr = doc.getDocumentElement().getAttribute("disambig");
        if(disambattr == null) {
            throw new IOException("disambig attribute missing");
        } else {
            this.disambig = disambattr;
        }
        NodeList nl = doc.getDocumentElement().getChildNodes();
        for(int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            String nform = n.getNodeName();
            if(nform.equals("sgNom")) {
                this.sgNom.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("sgGen")) {
                this.sgGen.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("sgNomArt")) {
                this.sgNomArt.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("sgGenArt")) {
                this.sgGenArt.add(new FormSg(Utils.getDefault(n), Utils.getGender(n)));
            } else if(nform.equals("plNom")) {
                this.plNom.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("plGen")) {
                this.plGen.add(new FormPlGen(Utils.getDefault(n), Utils.getStrength(n)));
            } else if(nform.equals("plNomArt")) {
                this.plNomArt.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("plGenArt")) {
                this.plGenArt.add(new Form(Utils.getDefault(n)));
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
        Element root = doc.createElement("nounPhrase");
        root.setAttribute("default", getLemma());
        root.setAttribute("disambig", disambig);
        if(isDefinite) {
            root.setAttribute("isDefinite", "1");
        } else {
            root.setAttribute("isDefinite", "0");
        }
        if(forceNominative) {
            root.setAttribute("forceNominative", "1");
        } else {
            root.setAttribute("forceNominative", "0");
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
        for(FormSg f : sgNomArt) {
            Element e = doc.createElement("sgNomArt");
            e.setAttribute("default", f.value);
            e.setAttribute("gender", (f.gender == Gender.Masc) ? "masc" : "fem");
            root.appendChild(e);
        }
        for(FormSg f : sgGenArt) {
            Element e = doc.createElement("sgGenArt");
            e.setAttribute("default", f.value);
            e.setAttribute("gender", (f.gender == Gender.Masc) ? "masc" : "fem");
            root.appendChild(e);
        }
        for(Form f : plNom) {
            Element e = doc.createElement("plNom");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : plGen) {
            Element e = doc.createElement("plGen");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : plNomArt) {
            Element e = doc.createElement("plNomArt");
            e.setAttribute("default", f.value);
            root.appendChild(e);
        }
        for(Form f : plGenArt) {
            Element e = doc.createElement("plGenArt");
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
