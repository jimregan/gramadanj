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

public class PluralInfoLgC extends PluralInfo {
    public PluralInfoLgC(String bayse) {
        this(bayse, "");
    }
    public PluralInfoLgC(String bayse, String slenderisationTarget) {
        this.strength = Features.Strength.Weak;
        String form = Opers.Broaden(bayse);
        this.gen.add(new Form(form));

        form = form + "a";
        this.voc.add(new Form(form));

        form = bayse;
        form = Utils.s(form, "ch$", "gh");
        form = Opers.Slenderise(bayse, slenderisationTarget);
        this.nom.add(new Form(form));
    }
}