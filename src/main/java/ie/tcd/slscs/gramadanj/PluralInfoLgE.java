package ie.tcd.slscs.gramadanj;

public class PluralInfoLgE extends PluralInfo {
    public PluralInfoLgE(String bayse) {
        this(bayse, "");
    }
    public PluralInfoLgE(String bayse, String slenderisationTarget) {
        this.strength = Features.Strength.Weak;
        String form = Opers.Slenderise(bayse) + "e";
        this.nom.add(new Form(form));
        this.gen.add(new Form(Opers.Broaden(form)));
        this.voc.add(new Form(form));

    }
}
