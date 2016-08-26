package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class PluralInfoTr extends PluralInfo {
    public PluralInfoTr(String bayse) {
        this.strength = Features.Strength.Strong;
        this.nom.add(new Form(bayse));
        this.gen.add(new Form(bayse));
        this.voc.add(new Form(bayse));
    }
}