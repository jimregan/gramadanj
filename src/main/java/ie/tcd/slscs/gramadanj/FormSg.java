package ie.tcd.slscs.gramadanj;

public class FormSg extends Form implements Comparable<Form> {
    public Features.Gender gender;
    FormSg (String value, Features.Gender gender) {
        super(value);
        this.gender=gender;
    }
    public boolean equals(FormSg f) {
        if(f != null && (f instanceof FormSg)) {
            return f.value.equals(value) && f.gender == gender;
        } else {
            return false;
        }
    }
    public int hashCode() {
        return value.hashCode();
    }
}
