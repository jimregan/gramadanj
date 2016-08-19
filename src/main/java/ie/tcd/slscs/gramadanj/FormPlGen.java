package ie.tcd.slscs.gramadanj;

public class FormPlGen extends Form implements Comparable<Form> {
    public Features.Strength strength;
    FormPlGen (String value, Features.Strength strength) {
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
