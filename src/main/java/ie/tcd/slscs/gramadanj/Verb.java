package ie.tcd.slscs.gramadanj;

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
    public Verb() {
        this.nickname_addition = " verb";
        verbalNoun = new ArrayList<Form>();
        verbalAdjective = new ArrayList<Form>();
        tenses = new HashMap<VerbTense, Map<VerbDependency, Map<VerbPerson, List<Form>>>>();
    }

    public void loadXML(InputSource is) throws Exception {
        // FIXME
    }
    public void writeXML(OutputStream os) throws Exception {
        // FIXME
    }
}