package ie.tcd.slscs.gramadanj;

import java.util.Comparator;

public class Form implements Comparable<Form> {

    String value;
    Form (String value) {
		this.value = value;
	}
    public boolean equals(Form f) {
        if(f != null && (f instanceof Form)) {
            return f.value.equals(value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return value.hashCode();
    }

    public int compareTo(Form other) throws ClassCastException {
        if(!(other instanceof Form)) {
            throw new ClassCastException("Form object expected");
        }
        return this.value.compareTo(other.value);
    }
}
