package ie.tcd.slscs.gramadanj;

import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.gramadanj.Form;
import ie.tcd.slscs.gramadanj.Form.Gender;
import ie.tcd.slscs.gramadanj.Form.Strength;
import ie.tcd.slscs.gramadanj.Form.Mutation;
import ie.tcd.slscs.gramadanj.Opers;

public class Adjective {
	public String disambig = "";
	public int declension = 0;

	public List<Form> sgNom = new ArrayList<Form>();
	public List<Form> sgGenMasc = new ArrayList<Form>();
	public List<Form> sgGenFem = new ArrayList<Form>();
	public List<Form> sgVocMasc = new ArrayList<Form>();
	public List<Form> sgVocFem = new ArrayList<Form>();
	public List<Form> plNom = new ArrayList<Form>();
	public List<Form> graded = new ArrayList<Form>();
	public List<Form> abstractNoun = new ArrayList<Form>();
	public boolean isPre = false;

	public String getNickname() {
		String ret = getLemma();
		ret += " adj";
		ret += (this.declension > 0 ? this.declension : "");
		
		if (this.disambig != "") {
			ret += " " + this.disambig;
		}
		ret = ret.replace(" ", "_");
		return ret;
	}
	
	public String getFriendlyNickname() {
		String ret = getLemma();
		ret += "(adj";
		ret += (this.declension > 0 ? this.declension : "");

		if (this.disambig != "") {
			ret += " " + this.disambig;
		}
		ret += ")";

		return ret;
	}
	
	public String getLemma() {
		String ret = "";
		Form lemmaForm = this.sgNom.get(0);
		if (lemmaForm != null) {
			ret = lemmaForm.value;
		}
		return ret;
	}

	public List<Form> getComparPres() {
		List<Form> ret = new ArrayList<Form>();
		for (Form f : graded) {
			ret.add(new Form("níos " + f.value));
		}
		return ret;
	}

	public List<Form> getSuperPres() {
		List<Form> ret = new ArrayList<Form>();
		for (Form f : graded) {
			ret.add(new Form("is " + f.value));
		}
		return ret;
	}

	public List<Form> getComparPast() {
		List<Form> ret = new ArrayList<Form>();
		for (Form f : graded) {
			String s = "";
			if (f.value.matches("^[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
				s = "ní b'" + f.value;
			} else if (f.value.matches("^f[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
				s = "ní b'" + Opers.Mutate(Mutation.Len1, f.value);
			} else {
				s = "ní ba " + Opers.Mutate(Mutation.Len1, f.value);
			}
			ret.add(new Form(s));
		}
		return ret;
	}

	public List<Form> getSuperPast() {
		List<Form> ret = new ArrayList<Form>();
		for (Form f : graded) {
			String s = "";
			if (f.value.matches("^[aeiouáéíóúAEIOUÁÉÍÓÚ]")) {
				s = "ab'" + f.value;
			} else if (f.value.matches("^f")) {
				s = "ab " + Opers.Mutate(Mutation.Len1, f.value);
			} else {
				s = "ba " + Opers.Mutate(Mutation.Len1, f.value);
			}
			ret.add(new Form(s));
		}
		return ret;
	}

	public Adjective() {
	}

	public Adjective(SingularInfo sgMasc, SingularInfo sgFem, String plural, String graded) {
		this.sgNom = sgMasc.nom;
		this.sgGenMasc = sgMasc.gen;
		this.sgGenFem = sgFem.gen;
		this.sgVocMasc = sgMasc.voc;
		this.sgVocFem = sgFem.voc;
		if (plural != null) {
			this.plNom.add(new Form(plural));
		}
		//this = create(sgMasc, sgFem, plural, graded);
	}

	public static Adjective create(SingularInfo sgMasc, SingularInfo sgFem, String plural, String graded) {
		Adjective adj = new Adjective();
		adj.sgNom = sgMasc.nom;
		adj.sgGenMasc = sgMasc.gen;
		adj.sgGenFem = sgFem.gen;
		adj.sgVocMasc = sgMasc.voc;
		adj.sgVocFem = sgFem.voc;
		if (plural != null) {
			adj.plNom.add(new Form(plural));
		}
		return adj;
	}

	public static Adjective fromXML() {
		Adjective adj = new Adjective();
		return adj;
	}
}
