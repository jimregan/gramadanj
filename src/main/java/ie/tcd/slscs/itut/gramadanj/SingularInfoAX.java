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

public class SingularInfoAX extends SingularInfo {
    public SingularInfoAX(String lemma, Features.Gender gender, boolean syncope, String broadeningTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        String form = lemma;
        if(syncope) {
            form = Opers.Syncope(form);
        }
        form = Opers.Broaden(form, broadeningTarget);
        form = form + "ach";
        this.gen.add(new Form(form));
    }
    public SingularInfoAX(String lemma, Features.Gender gender, boolean syncope) {
        this(lemma, gender, syncope, "");
    }  
}