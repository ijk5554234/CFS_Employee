package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean{
	private String email;
	private String amount;
	
	public String getEmail()   { return email;    }
	public String getAmount()  { return amount;   }
	
	public void setFundName(String s) { email  = trimAndConvert(s,"<>>\"]"); }
	public void setSymbol(String s)   { amount = trimAndConvert(s,"<>>\"]"); }
	
	public long getAmountAsLong() {
		double am = Double.parseDouble(amount);
		return (long) am * 100;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		try {
			Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			errors.add("Not valid Amount");
		}

		if (email == null || email.length() == 0) {
			errors.add("Customer's email is required");
		}
		
		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}

}
