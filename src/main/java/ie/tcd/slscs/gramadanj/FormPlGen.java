package ie.tcd.slscs.gramadanj;
import ie.tcd.slscs.gramadanj.Form;

public class FormPlGen extends Form {
    public Strength strength;
    FormPlGen (String value, Strength strength) {
        super(value);
        this.strength = strength;
    }
    public boolean equals(FormPlGen f) {
        return f.value.equals(value) && f.strength == strength;
    }
}
