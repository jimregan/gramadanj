package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class SingularInfoL extends SingularInfo {
    public SingularInfoL(String lemma, Features.Gender gender, String broadeningTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        String form = Opers.Broaden(lemma, broadeningTarget);
        this.gen.add(new Form(form));
    }
    public SingularInfoL(String lemma, Features.Gender gender) {
        this(lemma, gender, "");
    }
}
