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

public class PluralInfoLgA extends PluralInfo {
    public PluralInfoLgA(String bayse) {
        this(bayse, "");
    }
    public PluralInfoLgA(String bayse, String broadeningTarget) {
        this.strength = Features.Strength.Weak;
        String form = Opers.Broaden(bayse, broadeningTarget) + "a";
        this.nom.add(new Form(form));
        this.gen.add(new Form(Opers.Broaden(bayse)));
        this.voc.add(new Form(form));
    }
}
