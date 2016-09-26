package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 *
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VP {
    public enum VPTense {
        Any,
        Past,
        PastCont,
        Pres,
        PresCont,
        Fut,
        Cond
    }
    public enum VPMood {
        Imper,
        Subj
    }
    public enum VPShape {
        Any,
        Declar,
        Interrog /*, RelDep, RelIndep, Report */
    }
    public enum VPPerson {
        Any,
        Sg1,
        Sg2,
        Sg3Masc,
        Sg3Fem,
        Pl1,
        Pl2,
        Pl3,
        NoSubject,
        Auto
    }
    public enum VPPolarity {
        Any,
        Pos,
        Neg
    }

    public Map<VPTense, Map<VPShape, Map<VPPerson, Map<VPPolarity, List<Form>>>>> tenses;
    public Map<VPMood, Map<VPPerson, Map<VPPolarity, List<Form>>>> moods;
    public VP() {
        tenses = new HashMap<VPTense, Map<VPShape, Map<VPPerson, Map<VPPolarity, List<Form>>>>>();
        moods = new HashMap<VPMood, Map<VPPerson, Map<VPPolarity, List<Form>>>>();
        for(VPTense t : VPTense.values()) {
            this.tenses.put(t, new HashMap<VPShape, Map<VPPerson, Map<VPPolarity, List<Form>>>>());
            for(VPShape s : VPShape.values()) {
                this.tenses.get(t).put(s, new HashMap<VPPerson, Map<VPPolarity, List<Form>>>());
                for(VPPerson p : VPPerson.values()) {
                    this.tenses.get(t).get(s).put(p, new HashMap<VPPolarity, List<Form>>());
                    for(VPPolarity pol : VPPolarity.values()) {
                        this.tenses.get(t).get(s).get(p).put(pol, new ArrayList<Form>());
                    }
                }
            }
        }
        for(VPMood m : VPMood.values()) {
            this.moods.put(m, new HashMap<VPPerson, Map<VPPolarity, List<Form>>>());
            for(VPPerson p : VPPerson.values()) {
                this.moods.get(m).put(p, new HashMap<VPPolarity, List<Form>>());
                for(VPPolarity pol : VPPolarity.values()) {
                    this.moods.get(m).get(p).put(pol, new ArrayList<Form>());
                }
            }
        }
    }
    public VP(Verb v) {
        this();
        for(VPTense t : v.tenseRules.keySet()) {
            for(VPPerson p : v.tenseRules.get(t).keySet()) {
                for(VPShape s : v.tenseRules.get(t).get(p).keySet()) {
                    for(VPPolarity pol : v.tenseRules.get(t).get(p).get(s).keySet()) {
                        for(VerbTenseRule r : v.tenseRules.get(t).get(p).get(s).get(pol)) {
                            for(Form f : v.tenses.get(r.verbTense).get(r.verbDependency).get(r.verbPerson)) {
                                Form tmp = new Form("");
                                if(!"".equals(r.particle)) {
                                    tmp.value = r.particle + " ";
                                }
                                tmp.value += Opers.Mutate(r.mutation, f.value);
                                if(!"".equals(r.pronoun)) {
                                    tmp.value += " " + r.pronoun;
                                }
                                if(v.getLemma().equals("bí")
                                        && t == VPTense.Pres && s == VPShape.Declar
                                        && pol == VPPolarity.Neg && tmp.value.startsWith("ní fhuil")) {
                                    tmp.value = "níl" + tmp.value.substring(8);
                                }
                                this.tenses.get(t).get(s).get(p).get(pol).add(tmp);
                            }
                        }
                    }
                }
            }
        }
    }
}
