package ie.tcd.slscs.gramadanj;

public class PluralInfoLgA extends PluralInfo {
    public PluralInfoLgA(String bayse) {
        this(bayse, "");
    }
    public PluralInfoLgA(String bayse, String broadeningTarget) {
        this.strength = Features.Strength.Weak;
        String form = Opers.Broaden(bayse, broadeningTarget) + "a";
        this.nom.add(new Form(form));
        this.gen.add(new Form(Opers.Broaden(bayse)));
        this.voc.add(new Form(form));
    }
}
