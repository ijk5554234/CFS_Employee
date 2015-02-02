/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean{
	
	private String amount;
	private String action;
	
	public String getAmount()  { return amount;   }
	public String getAction()  { return action;   }
	
	public void setAmount(String s)   { amount = trimAndConvert(s,"<>>\"]"); }
	public void setAction(String s)   { action = trimAndConvert(s,"<>\""); }
	
	public long getAmountAsLong() {
	    System.out.println(amount);
		double am = Double.parseDouble(amount);
		System.out.println(am);
		System.out.println(Math.round(am * 100));
		return Math.round(am * 100);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		try {
			Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			errors.add("Not valid Amount");
		}

		
		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		if (!action.equals("Deposit Confirm"))
            errors.add("Invalid button");
		return errors;
	}

}
