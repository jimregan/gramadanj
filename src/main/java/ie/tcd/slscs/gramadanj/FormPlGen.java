package ie.tcd.slscs.gramadanj;
import ie.tcd.slscs.gramadanj.Form;

public class FormPlGen extends Form implements Comparable<Form> {
    public Strength strength;
    FormPlGen (String value, Strength strength) {
        super(value);
        this.strength = strength;
    }
    public boolean equals(FormPlGen f) {
        if(f != null && (f instanceof FormPlGen)) {
            return f.value.equals(value) && f.strength == strength;
        } else {
            return false;
        }
    }
    public int hashCode() {
        return value.hashCode();
    }
}
