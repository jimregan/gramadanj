package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 *
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

import java.util.*;

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

    static final Map<VPPerson, Verb.VerbPerson> PERSON_MAP;
    static final Map<VPPerson, String> PRONOUN_MAP;
    static {
        Map<VPPerson, Verb.VerbPerson> pmap = new HashMap<VPPerson, Verb.VerbPerson>();
        pmap.put(VPPerson.Sg1, Verb.VerbPerson.Sg1);
        pmap.put(VPPerson.Sg2, Verb.VerbPerson.Sg2);
        pmap.put(VPPerson.Sg3Masc, Verb.VerbPerson.Sg3);
        pmap.put(VPPerson.Sg3Fem, Verb.VerbPerson.Sg3);
        pmap.put(VPPerson.Pl1, Verb.VerbPerson.Pl1);
        pmap.put(VPPerson.Pl2, Verb.VerbPerson.Pl2);
        pmap.put(VPPerson.Pl3, Verb.VerbPerson.Pl3);
        pmap.put(VPPerson.NoSubject, Verb.VerbPerson.Base);
        pmap.put(VPPerson.Auto, Verb.VerbPerson.Auto);
        PERSON_MAP = Collections.unmodifiableMap(pmap);
        Map<VPPerson, String> prnmap = new HashMap<VPPerson, String>();
        prnmap.put(VPPerson.Sg1, " mé");
        prnmap.put(VPPerson.Sg2, " tú");
        prnmap.put(VPPerson.Sg3Masc, " sé");
        prnmap.put(VPPerson.Sg3Fem, " sí");
        prnmap.put(VPPerson.Pl1, " muid");
        prnmap.put(VPPerson.Pl2, " sibh");
        prnmap.put(VPPerson.Pl3, " siad");
        prnmap.put(VPPerson.NoSubject, "");
        prnmap.put(VPPerson.Auto, "");
        PRONOUN_MAP = Collections.unmodifiableMap(prnmap);
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
        for(VPPerson p : VPPerson.values()) {
            boolean hasSynthetic = false;
            for(Form f : v.moods.get(Verb.VerbMood.Imper).get(PERSON_MAP.get(p))) {
                String pos = f.value;
                String neg = "ná " + Opers.Mutate(Features.Mutation.PrefH, pos);
                this.moods.get(VPMood.Imper).get(p).get(VPPolarity.Pos).add(new Form(pos));
                this.moods.get(VPMood.Imper).get(p).get(VPPolarity.Neg).add(new Form(neg));
                hasSynthetic = true;
            }
            if(!hasSynthetic || p == VPPerson.Pl1 || p == VPPerson.Pl3) {
                for(Form f : v.moods.get(Verb.VerbMood.Imper).get(Verb.VerbPerson.Base)) {
                    String pos = f.value + PRONOUN_MAP.get(p);
                    String neg = "ná " + Opers.Mutate(Features.Mutation.PrefH, pos);
                    this.moods.get(VPMood.Imper).get(p).get(VPPolarity.Pos).add(new Form(pos));
                    this.moods.get(VPMood.Imper).get(p).get(VPPolarity.Neg).add(new Form(neg));
                    hasSynthetic = true;
                }
            }
        }
        for(VPPerson p : VPPerson.values()) {
            Features.Mutation posMut = Features.Mutation.Ecl1;
            Features.Mutation negMut = Features.Mutation.Len1;
            String negPart = "nár ";
            if(v.getLemma().equals("abair")) {
                negMut = Features.Mutation.None;
            }
            if(v.getLemma().equals("bí")) {
                negPart = "ná ";
            }
            boolean hasSynthetic = false;
            for(Form f : v.moods.get(Verb.VerbMood.Subj).get(PERSON_MAP.get(p))) {
                String pos = "go " + Opers.Mutate(posMut, f.value);
                String neg = negPart + Opers.Mutate(negMut, f.value);
                this.moods.get(VPMood.Subj).get(p).get(VPPolarity.Pos).add(new Form(pos));
                this.moods.get(VPMood.Subj).get(p).get(VPPolarity.Neg).add(new Form(neg));
                hasSynthetic = true;
            }
            if(!hasSynthetic || p == VPPerson.Pl1) {
                for(Form f : v.moods.get(Verb.VerbMood.Subj).get(Verb.VerbPerson.Base)) {
                    String pos = "go " + Opers.Mutate(posMut, f.value) + PRONOUN_MAP.get(p);
                    String neg = negPart + Opers.Mutate(negMut, f.value) + PRONOUN_MAP.get(p);
                    this.moods.get(VPMood.Subj).get(p).get(VPPolarity.Pos).add(new Form(pos));
                    this.moods.get(VPMood.Subj).get(p).get(VPPolarity.Neg).add(new Form(neg));
                    hasSynthetic = true;
                }
            }
        }
    }
}
