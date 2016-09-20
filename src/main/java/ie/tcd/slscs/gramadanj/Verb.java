package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

import org.xml.sax.InputSource;

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
    public Verb() {
        this.nickname_addition = " verb";
        verbalNoun = new ArrayList<Form>();
        verbalAdjective = new ArrayList<Form>();
        tenses = new HashMap<VerbTense, Map<VerbDependency, Map<VerbPerson, List<Form>>>>();
        moods = new HashMap<VerbMood, Map<VerbPerson, List<Form>>>();
    }

    public void loadXML(InputSource is) throws Exception {
        // FIXME
    }
    public void writeXML(OutputStream os) throws Exception {
        // FIXME
    }
}
