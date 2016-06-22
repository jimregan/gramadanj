package gramadanj;
import java.util.ArrayList;
import java.util.List;

import gramadanj.Form;
import gramadanj.Form.Gender;
import gramadanj.Form.Mutation;

public class NP {
	public String disambig="";
	public String getNickname() {
		String ret = getLemma() + " NP";
		if (this.disambig != "") {
			ret += " " + this.disambig;
		}
		ret = ret.replace(" ", "_");
		return ret;
	}
	
	public List<FormSg> sgNom = new ArrayList<FormSg>();
	public List<FormSg> sgGen = new ArrayList<FormSg>();
	public List<FormSg> sgNomArt = new ArrayList<FormSg>();
	public List<FormSg> sgGenArt = new ArrayList<FormSg>();
	public List<Form> plNom = new ArrayList<Form>();
	public List<Form> plGen = new ArrayList<Form>();
	public List<Form> plNomArt = new ArrayList<Form>();
	public List<Form> plGenArt = new ArrayList<Form>();
	
	public boolean isDefinite=false;
	
	public String getLemma() {
		String ret="";
		if (sgNom.size() != 0) {
			ret = sgNom.get(0).value;
		} else {
			if (sgNomArt.size() != 0) {
				ret = sgNomArt.get(0).value;
			} else if (plNom.size() != 0) {
				ret = plNom.get(0).value;
			} else if (plNomArt.size() != 0) {
				ret = plNomArt.get(0).value;
			}
		}
		return ret;
	}
	
	public Gender getGender() {
		Gender ret = Gender.Masc;
		if (sgNom.size() != 0) {
			ret = sgNom.get(0).gender;
		} else if (sgNomArt.size() != 0) {
			ret = sgNomArt.get(0).gender;
		}
		return ret;
	}
	
	public boolean hasGender() {
		boolean ret = false;
		if (sgNom.size() != 0 || sgNomArt.size() != 0) {
			ret = true;
		}
		return ret;
	}

	NP(Gender gender, String sgNom, String sgGen, String plNom, String plGen) {
		this.sgNom.add(new FormSg(sgNom, gender));
		Mutation mut = (gender == Gender.Masc) ? Mutation.PrefT : Mutation.Len3;
		String value = "an " + Opers.Mutate(mut, sgNom);
		this.sgNomArt.add(new FormSg(value, gender));
		
		// FIXME: sgGen, no?
		this.sgGen.add(new FormSg(sgNom, gender));

		mut = (gender==Gender.Masc ? Mutation.Len3 : Mutation.PrefH);
		value = (gender==Gender.Masc ? "an" : "na") + " " + Opers.Mutate(mut, sgGen);
		this.sgGenArt.add(new FormSg(value, gender));

		this.plNomArt.add(new Form(plNom));
		
		value = "na " + Opers.Mutate(Mutation.PrefH, plNom);
		this.plNomArt.add(new Form(value));
		
		// FIXME: plGen, no?
		this.plGen.add(new Form(plNom));
		
		value = "na " + Opers.Mutate(Mutation.Ecl1, plGen);
		this.plGenArt.add(new Form(value));
	}
	
	NP(Noun head) {
		this.isDefinite = head.isDefinite;
	}
}
