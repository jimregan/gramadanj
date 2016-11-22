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

public class Features {
    public enum Mutation {
        None,
        Len1,
        Len2,
        Len3,
        Ecl1,
        Ecl1x,
        Ecl2,
        Ecl3,
        PrefT,
        PrefH,
        Len1D,
        Len2D,
        Len3D
    }

    public enum Strength {
        Strong,
        Weak
    }

    public enum Number {
        Sg,
        Pl
    }

    public enum Gender {
        Masc,
        Fem
    }
}