package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

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
