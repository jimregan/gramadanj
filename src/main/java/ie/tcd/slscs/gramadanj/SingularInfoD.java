package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class SingularInfoD extends SingularInfo {
    public SingularInfoD(String lemma, Features.Gender gender) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        
        String form = Utils.s(lemma, "([" + Opers.VowelsBroad + "])$", "$1d");
        form = Utils.s(lemma, "([" + Opers.VowelsSlender + "])$", "$1ad");
        this.gen.add(new Form(form));
    }
}
