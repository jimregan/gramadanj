package gramadanj;

import gramadanj.Form.Strength;

public class PluralInfoLgC extends PluralInfo {
	public PluralInfoLgC(String bayse) {
		this(bayse, "");
	}
	public PluralInfoLgC(String bayse, String slenderisationTarget) {
		this.strength = Strength.Weak;
		String form = Opers.Broaden(bayse);
	}
}
