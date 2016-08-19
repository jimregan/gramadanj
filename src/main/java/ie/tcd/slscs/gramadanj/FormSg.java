package ie.tcd.slscs.gramadanj;
import ie.tcd.slscs.gramadanj.Form;

public class FormSg extends Form implements Comparable<Form> {
    public Gender gender;
    FormSg (String value, Gender gender) {
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
