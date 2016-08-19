package ie.tcd.slscs.gramadanj;

import java.util.Comparator;

public class Form implements Comparable<Form> {
	
    public enum Mutation {
        None,
        Len1,
        Len2,
        Len3,
        Ecl1,
        Ecl1x,
        Ecl2,
        Ecl3,
        PrefT,
        PrefH,
        Len1D,
        Len2D,
        Len3D
    }
    public enum Strength {
        Strong,
        Weak
    }
    public enum Number {
        Sg,
        Pl
    }
    public enum Gender {
        Masc,
        Fem
    }
	
    String value;
    Form (String value) {
		this.value = value;
	}
    public boolean equals(Form f) {
        return f.value.equals(value);
    }

    public int compareTo(Form other) throws ClassCastException {
        if(!(other instanceof Form)) {
            throw new ClassCastException("Form object expected");
        }
        return this.value.compareTo(other.value);
    }
}
