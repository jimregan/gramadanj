package ie.tcd.slscs.gramadanj;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.gramadanj.Features.Gender;
import ie.tcd.slscs.gramadanj.Features.Mutation;
import org.xml.sax.InputSource;

public class NP extends PartOfSpeech {
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
		Gender ret = Features.Gender.Masc;
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

    NP() {
        this.nickname_addition = " NP";
    }
	NP(Gender gender, String sgNom, String sgGen, String plNom, String plGen) {
		this.sgNom.add(new FormSg(sgNom, gender));
		Mutation mut = (gender == Features.Gender.Masc) ? Features.Mutation.PrefT : Features.Mutation.Len3;
		String value = "an " + Opers.Mutate(mut, sgNom);
		this.sgNomArt.add(new FormSg(value, gender));
		
		// FIXME: sgGen, no?
		this.sgGen.add(new FormSg(sgNom, gender));

		mut = (gender== Features.Gender.Masc ? Features.Mutation.Len3 : Features.Mutation.PrefH);
		value = (gender== Features.Gender.Masc ? "an" : "na") + " " + Opers.Mutate(mut, sgGen);
		this.sgGenArt.add(new FormSg(value, gender));

		this.plNomArt.add(new Form(plNom));
		
		value = "na " + Opers.Mutate(Features.Mutation.PrefH, plNom);
		this.plNomArt.add(new Form(value));
		
		// FIXME: plGen, no?
		this.plGen.add(new Form(plNom));
		
		value = "na " + Opers.Mutate(Features.Mutation.Ecl1, plGen);
		this.plGenArt.add(new Form(value));
	}
	
	NP(Noun head) {
		this.isDefinite = head.isDefinite;
	}

    public void loadXML(InputSource is) throws Exception {
        // FIXME
    }
    public void writeXML(OutputStream os) throws Exception {
        // FIXME
    }
}
