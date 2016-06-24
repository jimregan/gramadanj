package gramadanj;
import gramadanj.Form;

public class FormSg extends Form {
	public Gender gender;
	FormSg (String value, Gender gender) {
		super(value);
		this.gender=gender;
	}
}
