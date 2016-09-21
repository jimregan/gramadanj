package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 *
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

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
    VP() {
        tenses = new HashMap<VPTense, Map<VPShape, Map<VPPerson, Map<VPPolarity, List<Form>>>>>();
        moods = new HashMap<VPMood, Map<VPPerson, Map<VPPolarity, List<Form>>>>();
    }
}
