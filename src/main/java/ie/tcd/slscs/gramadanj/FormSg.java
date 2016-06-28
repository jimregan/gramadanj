package ie.tcd.slscs.gramadanj;
import ie.tcd.slscs.gramadanj.Form;

public class FormSg extends Form {
	public Gender gender;
	FormSg (String value, Gender gender) {
		super(value);
		this.gender=gender;
	}
}
