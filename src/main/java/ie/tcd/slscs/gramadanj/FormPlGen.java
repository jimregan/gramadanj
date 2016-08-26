package ie.tcd.slscs.gramadanj;
/*
 * Copyright 2016 Trinity College, Dublin
 * 
 * Based on Gramadán
 * Copyright 2015-2016 Michal Boleslav Měchura and Foras na Gaeilge
 * Licence: CC-BY 4.0 International
 * http://creativecommons.org/licenses/by/4.0/
 */

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
