package ie.tcd.slscs.gramadanj;

import java.util.Comparator;

public class Form implements Comparable<Form> {

    String value;
    Form (String value) {
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        System.err.println(".equals()");
        if(o != null && (o instanceof Form)) {
            final Form f = (Form) o;
            System.err.println("Comparison: " + value + " " + f.value + " : " + (f.value.equals(value) ? "true" : "false"));
            return f.value.equals(value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        if(value != null) {
            return value.hashCode();
        } else {
            return 0;
        }
    }

    public int compareTo(Form other) throws ClassCastException {
        if(!(other instanceof Form)) {
            throw new ClassCastException("Form object expected");
        }
        return this.value.compareTo(other.value);
    }
    public String toString() {
        return value;
    }
}
