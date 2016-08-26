package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class SingularInfoA extends SingularInfo {
    public SingularInfoA(String lemma, Features.Gender gender, boolean syncope, String broadeningTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        
        String form = Utils.s(lemma, "([" + Opers.VowelsSlender + "])rt$", "$1rth");
        form = Utils.s(lemma, "([" + Opers.VowelsSlender + "])nnt$", "$1nn");
        form = Utils.s(lemma, "([" + Opers.VowelsSlender + "])nt$", "$1n");
        if(syncope){
            form = Opers.Syncope(form);
        }
        form = Opers.Broaden(form, broadeningTarget);
        form = form + "a";
        this.gen.add(new Form(form));
    }
    public SingularInfoA(String lemma, Features.Gender gender, boolean syncope) {
        this(lemma, gender, syncope, "");
    }
}
