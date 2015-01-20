
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
public class LoginForm extends FormBean{
	private String userName;
	private String password;
	private String action;
	
	public void setUserName(String s) { userName = trimAndConvert(s,"<>\""); }
	public void setAction(String s)   { action   = trimAndConvert(s,"<>\""); }
	public void setPassword(String s) { password = s.trim();                 }

	public String getUserName() { return userName; }
	public String getPassword() { return password; }
	public String getAction()   { return action;   }


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0)
			errors.add("UserName is required");
		if (password == null || password.length() == 0)
			errors.add("Password is required");
		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!action.equals("Login"))
			errors.add("Invalid button");
		if (userName.matches(".*[<>\"].*"))
			errors.add("Username may not contain angle brackets or quotes");

		return errors;
	}
}
