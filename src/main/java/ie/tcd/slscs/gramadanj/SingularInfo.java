package ie.tcd.slscs.gramadanj;
import java.util.ArrayList;
import java.util.List;

import ie.tcd.slscs.gramadanj.Form;
import ie.tcd.slscs.gramadanj.Form.Gender;

public class SingularInfo {
	public Gender gender;
	public List<Form> nom = new ArrayList<Form>();
	public List<Form> gen = new ArrayList<Form>();
	public List<Form> dat = new ArrayList<Form>();
	public List<Form> voc = new ArrayList<Form>();
	
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
