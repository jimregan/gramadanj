package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Verb extends PartOfSpeech {
    public enum VerbTense {
        Past,
        PastCont,
        Pres,
        PresCont,
        Fut,
        Cond
    }
    public enum VerbMood {
        Imper,
        Subj
    }
    public enum VerbDependency {
        Indep,
        Dep
    }
    public enum VerbPerson {
        Base,
        Sg1,
        Sg2,
        Sg3,
        Pl1,
        Pl2,
        Pl3,
        Auto
    }

    List<Form> verbalNoun;
    List<Form> verbalAdjective;
    Map<VerbTense, Map<VerbDependency, Map<VerbPerson, List<Form>>>> tenses;
    Map<VerbMood, Map<VerbPerson, List<Form>>> moods;
    Map<VP.VPTense, Map<VP.VPPerson, Map<VP.VPShape, Map<VP.VPPolarity, List<VerbTenseRule>>>>> tenseRules;
    public Verb() {
        this.nickname_addition = " verb";
        verbalNoun = new ArrayList<Form>();
        verbalAdjective = new ArrayList<Form>();
        tenses = new HashMap<VerbTense, Map<VerbDependency, Map<VerbPerson, List<Form>>>>();
        moods = new HashMap<VerbMood, Map<VerbPerson, List<Form>>>();
        tenseRules = new HashMap<VP.VPTense, Map<VP.VPPerson, Map<VP.VPShape, Map<VP.VPPolarity, List<VerbTenseRule>>>>>();

        for(VP.VPTense vt : VP.VPTense.values()) {
            this.tenseRules.put(vt, new HashMap<VP.VPPerson, Map<VP.VPShape, Map<VP.VPPolarity, List<VerbTenseRule>>>>());
            for(VP.VPPerson vp : VP.VPPerson.values()) {
                this.tenseRules.get(vt).put(vp, new HashMap<VP.VPShape, Map<VP.VPPolarity, List<VerbTenseRule>>>());
                for(VP.VPShape vs : VP.VPShape.values()) {
                    this.tenseRules.get(vt).get(vp).put(vs, new HashMap<VP.VPPolarity, List<VerbTenseRule>>());
                    for(VP.VPPolarity vpol : VP.VPPolarity.values()) {
                        this.tenseRules.get(vt).get(vp).get(vs).put(vpol, new ArrayList<VerbTenseRule>());
                    }
                }
            }
        }
        makeTense(VP.VPTense.Past);
        makeTense(VP.VPTense.Pres);
        makeTense(VP.VPTense.PresCont);
        makeTense(VP.VPTense.Fut);
        makeTense(VP.VPTense.Cond);
        makeTense(VP.VPTense.PastCont);

        for(VerbTense vt : VerbTense.values()) {
            this.tenses.put(vt, new HashMap<VerbDependency, Map<VerbPerson, List<Form>>>());
            for(VerbDependency vd : VerbDependency.values()) {
                this.tenses.get(vt).put(vd, new HashMap<VerbPerson, List<Form>>());
                for(VerbPerson vp : VerbPerson.values()) {
                    this.tenses.get(vt).get(vd).put(vp, new ArrayList<Form>());
                }
            }
        }
        for(VerbMood vm : VerbMood.values()) {
            this.moods.put(vm, new HashMap<VerbPerson, List<Form>>());
            for(VerbPerson vp : VerbPerson.values()) {
                this.moods.get(vm).put(vp, new ArrayList<Form>());
            }
        }
    }

    private void makeTense(VP.VPTense t) {
        addTenseRuleGroup(t, VP.VPPerson.NoSubject, "");
        addTenseRuleGroup(t, VP.VPPerson.Sg1, "mé");
        addTenseRuleGroup(t, VP.VPPerson.Sg2, "tú");
        addTenseRuleGroup(t, VP.VPPerson.Sg3Masc, "sé");
        addTenseRuleGroup(t, VP.VPPerson.Sg3Fem, "sí");
        addTenseRuleGroup(t, VP.VPPerson.Pl1, "");
        addTenseRuleGroup(t, VP.VPPerson.Pl1, "muid", VerbPerson.Base);
        addTenseRuleGroup(t, VP.VPPerson.Pl2, "siad");
        addTenseRuleGroup(t, VP.VPPerson.Pl3, "");
        addTenseRuleGroup(t, VP.VPPerson.Auto, "");
    }

    private void addTenseRule(VP.VPTense t, VP.VPPerson p, VP.VPShape s, VP.VPPolarity pol, VerbTenseRule rule) {
        this.tenseRules.get(t).get(p).get(s).get(pol).add(rule);
    }
    private void addTenseRuleGroup(VP.VPTense t, VP.VPPerson p, String pron) {
        VerbPerson vpers = VerbPerson.Base;
        if(p == VP.VPPerson.Pl1) {
            vpers = VerbPerson.Pl1;
        } else if(p == VP.VPPerson.Pl3) {
            vpers = VerbPerson.Pl3;
        } else if(p == VP.VPPerson.Auto) {
            vpers = VerbPerson.Auto;
        }
        addTenseRuleGroup(t, p, pron, vpers);
    }

    private void addTenseRuleGroup(VP.VPTense t, VP.VPPerson p, String pron, VerbPerson vpers) {
        Features.Mutation mut[];
        if(t == VP.VPTense.Past) {
            mut = new Features.Mutation[] {
                    Features.Mutation.Len1D,
                    Features.Mutation.Len1,
                    Features.Mutation.Len1,
                    Features.Mutation.Len1D
            };
        } else {
            mut = new Features.Mutation[] {
                    Features.Mutation.None,
                    Features.Mutation.Len1,
                    Features.Mutation.Ecl1x,
                    Features.Mutation.Ecl1
            };
        }
        VerbTense tns = verbTenseFromVPTense(t);
        addTenseRule(t, p, VP.VPShape.Declar, VP.VPPolarity.Pos, new VerbTenseRule("", mut[0], tns, VerbDependency.Indep, vpers, pron));
        addTenseRule(t, p, VP.VPShape.Declar, VP.VPPolarity.Neg, new VerbTenseRule("níor", mut[1], tns, VerbDependency.Dep, vpers, pron));
        addTenseRule(t, p, VP.VPShape.Interrog, VP.VPPolarity.Pos, new VerbTenseRule("ar", mut[2], tns, VerbDependency.Dep, vpers, pron));
        addTenseRule(t, p, VP.VPShape.Interrog, VP.VPPolarity.Neg, new VerbTenseRule("nár", mut[3], tns, VerbDependency.Dep, vpers, pron));
    }

    private VerbTense verbTenseFromVPTense(VP.VPTense t) {
        Map<VP.VPTense, VerbTense> m = new HashMap<VP.VPTense, VerbTense>();
        m.put(VP.VPTense.Cond, VerbTense.Cond);
        m.put(VP.VPTense.Past, VerbTense.Past);
        m.put(VP.VPTense.PastCont, VerbTense.PastCont);
        m.put(VP.VPTense.Pres, VerbTense.Pres);
        m.put(VP.VPTense.PresCont, VerbTense.PresCont);
        m.put(VP.VPTense.Fut, VerbTense.Fut);
        return m.get(t);
    }

    public void loadXML(InputSource is) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);
        String root = doc.getDocumentElement().getNodeName();
        if (root != "verb") {
            throw new IOException("Expected root node " + root);
        }
        this.lemma = doc.getDocumentElement().getAttribute("default").toString();
        this.disambig = doc.getDocumentElement().getAttribute("disambig").toString();
        NodeList nl = doc.getDocumentElement().getChildNodes();
        for(int i=0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            String nform = n.getNodeName();
            if (nform.equals("verbalNoun")) {
                this.verbalNoun.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("verbalAdjective")) {
                this.verbalAdjective.add(new Form(Utils.getDefault(n)));
            } else if(nform.equals("tenseForm")) {
                addTense(getTense(n), getDep(n), getPerson(n), Utils.getDefault(n));
            } else if(nform.equals("moodForm")) {
                addMood(getMood(n), getPerson(n), Utils.getDefault(n));
            }
        }
        handleIrregular();
    }
    public void writeXML(OutputStream os) throws Exception {
        // FIXME
    }
    static VerbDependency getDep(Node n) {
        VerbDependency dep = VerbDependency.Indep;
        String s = n.getAttributes().getNamedItem("dependency").toString();
        if(s.equals("Indep") || s.toLowerCase().equals("indep")) {
            dep = VerbDependency.Indep;
        } else if(s.equals("Dep") || s.toLowerCase().equals("dep")) {
            dep = VerbDependency.Dep;
        }
        return dep;
    }
    static VerbTense getTense(Node n) {
        VerbTense tns = VerbTense.Past;
        String s = n.getAttributes().getNamedItem("tense").toString();
        if(s.equals("Past") || s.toLowerCase().equals("past")) {
            tns = VerbTense.PastCont;
        } else if(s.equals("PastCont") || s.toLowerCase().equals("pastcont")) {
            tns = VerbTense.PastCont;
        } else if(s.equals("PresCont") || s.toLowerCase().equals("prescont")) {
            tns = VerbTense.PresCont;
        } else if(s.equals("Fut") || s.toLowerCase().equals("fut")) {
            tns = VerbTense.Fut;
        } else if(s.equals("Cond") || s.toLowerCase().equals("cond")) {
            tns = VerbTense.Cond;
        }
        return tns;
    }
    static VerbPerson getPerson(Node n) {
        VerbPerson prs = VerbPerson.Base;
        String s = n.getAttributes().getNamedItem("person").toString();
        if(s.equals("Base") || s.toLowerCase().equals("base")) {
            prs = VerbPerson.Base;
        } else if(s.equals("Sg1") || s.toLowerCase().equals("sg1")) {
            prs = VerbPerson.Sg1;
        } else if(s.equals("Sg2") || s.toLowerCase().equals("sg2")) {
            prs = VerbPerson.Sg2;
        } else if(s.equals("Sg3") || s.toLowerCase().equals("sg3")) {
            prs = VerbPerson.Sg3;
        } else if(s.equals("Pl1") || s.toLowerCase().equals("pl1")) {
            prs = VerbPerson.Pl1;
        } else if(s.equals("Pl2") || s.toLowerCase().equals("pl2")) {
            prs = VerbPerson.Pl2;
        } else if(s.equals("Pl3") || s.toLowerCase().equals("pl3")) {
            prs = VerbPerson.Pl3;
        } else if(s.equals("Auto") || s.toLowerCase().equals("auto")) {
            prs = VerbPerson.Auto;
        }
        return prs;
    }
    static VerbMood getMood(Node n) {
        VerbMood mood = VerbMood.Subj;
        String s = n.getAttributes().getNamedItem("mood").toString();
        if(s.equals("Imper") || s.toLowerCase().equals("Imper")) {
            mood = VerbMood.Imper;
        } else if(s.equals("Subj") || s.toLowerCase().equals("subj")) {
            mood = VerbMood.Subj;
        }
        return mood;
    }
    public void addTense(VerbTense t, VerbDependency d, VerbPerson p, String form) {
        this.tenses.get(t).get(d).get(p).add(new Form(form));
    }
    public void addTense(VerbTense t, VerbPerson p, String form) {
        addTense(t, VerbDependency.Dep, p, form);
        addTense(t, VerbDependency.Indep, p, form);
    }
    public void addMood(VerbMood m, VerbPerson p, String form) {
        this.moods.get(m).get(p).add(new Form(form));
    }
    List<VerbTenseRule> getTenseRules(VP.VPTense t, VP.VPPerson p, VP.VPShape s, VP.VPPolarity pol) {
        List<VerbTenseRule> ret = new ArrayList<VerbTenseRule>();
        for(VP.VPTense vt : VP.VPTense.values()) {
            for(VP.VPPerson vp : VP.VPPerson.values()) {
                for(VP.VPShape vs : VP.VPShape.values()) {
                    for(VP.VPPolarity vpol : VP.VPPolarity.values()) {
                        if((vt == VP.VPTense.Any || vt == t) && (vp == VP.VPPerson.Any || vp == p) && (vs == VP.VPShape.Any || vs == s) && (vpol == VP.VPPolarity.Any || vpol == pol)) {
                            for(VerbTenseRule rl : this.tenseRules.get(vt).get(vp).get(vs).get(vpol)) {
                                ret.add(rl);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    private void handleIrregular() {
        if(getLemma().equals("bí")) {
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Declar, VP.VPPolarity.Pos)) {
                r.mutation = Features.Mutation.Len1;
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Declar, VP.VPPolarity.Neg)) {
                r.mutation = Features.Mutation.None;
                r.particle = "ní";
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Interrog, VP.VPPolarity.Pos)) {
                r.mutation = Features.Mutation.None;
                r.particle = "an";
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Interrog, VP.VPPolarity.Neg)) {
                r.mutation = Features.Mutation.None;
                r.particle = "nach";
            }
        }
        if(getLemma().equals("abair")) {
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Any, VP.VPPerson.Any, VP.VPShape.Declar, VP.VPPolarity.Pos)) {
                r.mutation = Features.Mutation.None;
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Any, VP.VPPerson.Any, VP.VPShape.Declar, VP.VPPolarity.Neg)) {
                r.mutation = Features.Mutation.None;
                r.particle = "ní";
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Any, VP.VPPerson.Any, VP.VPShape.Interrog, VP.VPPolarity.Pos)) {
                r.mutation = Features.Mutation.Ecl1x;
                r.particle = "an";
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Any, VP.VPPerson.Any, VP.VPShape.Interrog, VP.VPPolarity.Neg)) {
                r.mutation = Features.Mutation.Ecl1;
                r.particle = "nach";
            }
        }
        if(getLemma().equals("déan")) {
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Declar, VP.VPPolarity.Neg)) {
                r.mutation = Features.Mutation.Len1;
                r.particle = "ní";
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Interrog, VP.VPPolarity.Pos)) {
                r.mutation = Features.Mutation.Ecl1x;
                r.particle = "an";
            }
            for(VerbTenseRule r : getTenseRules(VP.VPTense.Past, VP.VPPerson.Any, VP.VPShape.Interrog, VP.VPPolarity.Neg)) {
                r.mutation = Features.Mutation.Ecl1;
                r.particle = "nach";
            }
        }
    }
}
