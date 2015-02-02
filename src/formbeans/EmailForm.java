package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmailForm extends FormBean{
	private String email;
	private String action;
	
	public void setEmail(String s)     { email =  trimAndConvert(s,"<>>\"]"); }
	public void setAction(String s)    { action  = trimAndConvert(s,"<>\""); }
	
	public String getEmail()     { return email;  }
	public String getAction()    { return action; }


	public List<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (email == null && email.isEmpty()) {
			errors.add("Customer id is required");
			return errors;
		}
		if (action == null) {
			errors.add("Button is required");
			return errors;
		}
		if (!action.equals("View")) 
			errors.add("Invalid button");
		if (!email.matches("[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?"))
			errors.add("Invalid email address");

		return errors;
	}
}
