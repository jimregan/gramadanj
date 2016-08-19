package ie.tcd.slscs.gramadanj;

import ie.tcd.slscs.gramadanj.Form.Strength;

public class PluralInfoLgC extends PluralInfo {
	public PluralInfoLgC(String bayse) {
		this(bayse, "");
	}
	public PluralInfoLgC(String bayse, String slenderisationTarget) {
		this.strength = Strength.Weak;
		String form = Opers.Broaden(bayse);
		this.gen.add(new Form(form));

        form = form + "a";
        this.voc.add(new Form(form));

        form = bayse;
        form = Utils.s(form, "ch$", "gh");
        //FIXME: form = Opers.
        this.nom.add(new Form(form));
	}
}
