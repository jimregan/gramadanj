package ie.tcd.slscs.gramadanj;

public class SingularInfoL extends SingularInfo {
    public SingularInfoL(String lemma, Features.Gender gender, String broadeningTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        String form = Opers.Broaden(lemma, broadeningTarget);
        this.gen.add(new Form(form));
    }
    public SingularInfoL(String lemma, Features.Gender gender) {
        this(lemma, gender, "");
    }
}
