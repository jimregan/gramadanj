package ie.tcd.slscs.gramadanj;

public class SingularInfoC extends SingularInfo {
    public SingularInfoC(String lemma, Features.Gender gender, String slenderisationTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        String form = Utils.s(lemma, "ch$", "gh");
        form = Opers.Slenderise(form, slenderisationTarget);
        if(gender == Features.Gender.Fem) {
            this.voc.add(new Form(lemma));
            form = Utils.s(form, "igh$", "í");
        } else {
            this.voc.add(new Form(form));
        }
        this.gen.add(new Form(form));
    }
    public SingularInfoC(String lemma, Features.Gender gender) {
        this(lemma, gender, "");
    }
}
