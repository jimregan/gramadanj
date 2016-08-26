package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class SingularInfoC extends SingularInfo {
    public SingularInfoC(String lemma, Features.Gender gender, String slenderisationTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        String form = Utils.s(lemma, "ch$", "gh");
        form = Opers.Slenderise(form, slenderisationTarget);
        if(gender == Features.Gender.Fem) {
            this.voc.add(new Form(lemma));
            form = Utils.s(form, "igh$", "í");
        } else {
            this.voc.add(new Form(form));
        }
        this.gen.add(new Form(form));
    }
    public SingularInfoC(String lemma, Features.Gender gender) {
        this(lemma, gender, "");
    }
}
