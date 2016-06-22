package gramadanj;

import gramadanj.Form.Strength;
import java.util.List;
import java.util.ArrayList;

public class PluralInfo {
	public Strength strength;
	public List<Form> nom = new ArrayList<Form>();
	public List<Form> gen = new ArrayList<Form>();
	public List<Form> voc = new ArrayList<Form>();

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
