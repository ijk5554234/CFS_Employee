/*
Jike Li
Andrew ID:jikel
08-600
Homework #9
Date: Nov. 29, 2014
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String cash;
	private String addrLine1;
	private String addrLine2;
	private String zip;
	private String city;
	private String action;
	
	public void setEmail(String s)     { email      = trimAndConvert(s,"<>\""); }
	public void setFirstName(String s) { firstName  = trimAndConvert(s,"<>\""); }
	public void setLastName(String s)  { lastName   = trimAndConvert(s,"<>\""); }	
	public void setAction(String s)    { action     = trimAndConvert(s,"<>\"");	}
	public void setPassword(String s)  { password   = trimAndConvert(s,"<>\"");	}
	public void setCash(String s) 	   { cash       = trimAndConvert(s,"<>\"");	}
	public void setAddrLine1(String s) { addrLine1  = trimAndConvert(s,"<>\""); }
	public void setAddrLine2(String s) { addrLine2  = trimAndConvert(s,"<>\""); }
	public void setCity(String s) 	   { city       = trimAndConvert(s,"<>\""); }
	public void setZip(String s) 	   { zip        = trimAndConvert(s,"<>\""); }
	
	public String getEmail()     { return email;	 }
	public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName;  }
	public String getPassword()  { return password;  }
	public String getAction()    { return action;    }
	public String getCash() 		{ return cash;		}
	public String getAddrLine1() 	{ return addrLine1; }
	public String getAddrLine2() 	{ return addrLine2;	}
	public String getZip() 			{ return zip;		}
	public String getCity() 		{ return city;		}

	public List<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();

		if (email == null || email.length() == 0)
			errors.add("Email is required");
		if (password == null || password.trim().length() == 0)
			errors.add("Password is required and cannot be blank");
		if (lastName == null || lastName.trim().length() == 0)
			errors.add("Last Name is required");
		if (firstName == null || firstName.trim().length() == 0)
			errors.add("First Name is required");
		if (action == null)
			errors.add("Button is required");
		if (errors.size() > 0)
			return errors;
		if (!action.equals("Register")) 
			errors.add("Invalid button");
		if (!email.matches("[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?"))
			errors.add("Invalid email address");

		return errors;
	}
}
