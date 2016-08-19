package ie.tcd.slscs.gramadanj;
import ie.tcd.slscs.gramadanj.Form;

public class FormSg extends Form implements Comparable<Form> {
	public Gender gender;
	FormSg (String value, Gender gender) {
		super(value);
		this.gender=gender;
	}
	public boolean equals(FormSg f) {
		return f.value.equals(value) && f.gender == gender;
	}
}
