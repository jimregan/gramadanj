package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

public class VerbTenseRule {
    public String particle = "";
    public Features.Mutation mutation = Features.Mutation.None;
    public Verb.VerbTense verbTense;
    public Verb.VerbDependency verbDependency;
    public Verb.VerbPerson verbPerson;

    public String pronoun = "";

    public VerbTenseRule(String particle, Features.Mutation mutation, Verb.VerbTense verbTense, Verb.VerbDependency verbDependency, Verb.VerbPerson verbPerson, String pronoun) {
        this.particle = particle;
        this.mutation = mutation;
        this.verbTense = verbTense;
        this.verbDependency = verbDependency;
        this.verbPerson = verbPerson;
        this.pronoun = pronoun;
    }
}
