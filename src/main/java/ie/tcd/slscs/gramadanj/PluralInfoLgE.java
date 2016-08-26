package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class PluralInfoLgE extends PluralInfo {
    public PluralInfoLgE(String bayse) {
        this(bayse, "");
    }
    public PluralInfoLgE(String bayse, String slenderisationTarget) {
        this.strength = Features.Strength.Weak;
        String form = Opers.Slenderise(bayse) + "e";
        this.nom.add(new Form(form));
        this.gen.add(new Form(Opers.Broaden(form)));
        this.voc.add(new Form(form));

    }
}
