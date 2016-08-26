package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

import java.util.Comparator;

public class Form implements Comparable<Form> {

    String value;
    Form (String value) {
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if(o != null && (o instanceof Form)) {
            final Form f = (Form) o;
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
