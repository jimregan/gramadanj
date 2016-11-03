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
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.itut.gramadanj.Features.Gender;

public class SingularInfo {
	public Gender gender;
	public List<Form> nom;
	public List<Form> gen;
	public List<Form> dat;
	public List<Form> voc;

	SingularInfo() {
		nom = new ArrayList<Form>();
		gen = new ArrayList<Form>();
		dat = new ArrayList<Form>();
		voc = new ArrayList<Form>();
	}
	public String print() {
		String ret="";
		ret += "NOM: ";
		for (Form f : this.nom) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		ret += "GEN: ";
		for (Form f : this.gen) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		ret += "DAT: ";
		for (Form f : this.dat) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		ret += "VOC: ";
		for (Form f : this.voc) {
			ret += "[" + f.value + "]";
		}
		ret += "\n";
		return ret;
	}
}
