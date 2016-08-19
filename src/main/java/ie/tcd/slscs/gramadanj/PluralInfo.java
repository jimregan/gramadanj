package ie.tcd.slscs.gramadanj;

import ie.tcd.slscs.gramadanj.Features.Strength;
import java.util.List;
import java.util.ArrayList;

public class PluralInfo {
	public Strength strength;
	public List<Form> nom;
	public List<Form> gen;
	public List<Form> voc;

	PluralInfo() {
		nom = new ArrayList<Form>();
		gen = new ArrayList<Form>();
		voc = new ArrayList<Form>();
	}
	public String print() {
		String ret="";
		ret += "NOM: ";
		for (Form f : this.nom) {
			ret += "[" + f.value + "] ";
		}
		ret += "\n";
		
		ret += "GEN: ";
		for (Form f : this.gen) {
			ret += "[" + f.value + "] ";
		}
		ret += "\n";

		ret += "VOC: ";
		for (Form f : this.voc) {
			ret += "[" + f.value + "] ";
		}
		ret += "\n";

		return ret;
	}
}
