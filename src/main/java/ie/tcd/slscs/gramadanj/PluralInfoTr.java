package ie.tcd.slscs.gramadanj;

public class PluralInfoTr extends PluralInfo {
    public PluralInfoTr(String bayse) {
        this.strength = Features.Strength.Strong;
        this.nom.add(new Form(bayse));
        this.gen.add(new Form(bayse));
        this.voc.add(new Form(bayse));
    }
}