package ie.tcd.slscs.gramadanj;

public class SingularInfoE extends SingularInfo {
    public SingularInfoE(String lemma, Features.Gender gender, boolean syncope, boolean doubleDative, String slenderisationTarget) {
        super();
        this.gender = gender;
        this.nom.add(new Form(lemma));
        this.voc.add(new Form(lemma));
        String form = lemma;
        if(syncope) {
            form = Opers.Syncope(form);
        }
        form = Opers.Slenderise(form, slenderisationTarget);
        this.dat.add(new Form(lemma));
        if(doubleDative) {
            this.dat.add(new Form(form));
        }
        form = Utils.s(form, "([" + Opers.VowelsSlender + "])ngt$", "$1ngth");
        form = Utils.s(form, "ú$", "ath");
        form = form + "e";
        this.gen.add(new Form(form));
    }
    public SingularInfoE(String lemma, Features.Gender gender, boolean syncope, boolean doubleDative) {
        this(lemma, gender, syncope, doubleDative, "");
    }  
}
