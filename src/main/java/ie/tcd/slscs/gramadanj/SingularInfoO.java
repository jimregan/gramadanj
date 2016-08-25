package ie.tcd.slscs.gramadanj;

public class SingularInfoO extends SingularInfo {
    public SingularInfoO(String lemma, Features.Gender gender) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.gen.add(new Form(lemma));
        this.dat.add(new Form(lemma));
        this.voc.add(new Form(lemma));
    }
}
