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

public class CreateFundForm extends FormBean{
	private String fundName;
	private String symbol;
	private String action;
	
	public String getFundName()   { return fundName; }
	public String getSymbol()     { return symbol;   }
	public String getAcction()    { return action;   }
	
	public void setFundName(String s) { fundName = trimAndConvert(s,"<>\""); }
	public void setSymbol(String s)   { symbol   = trimAndConvert(s,"<>\""); }
	public void setAction(String s)   { action   = trimAndConvert(s,"<>\""); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund name is required");
		}
		
		if (symbol == null || symbol.length() == 0) {
			errors.add("Ticker is required");
			return errors;
		}
		
		if (symbol.length() > 6) {
			errors.add("Ticker symbol is too long");
			return errors;
		}
		if (!action.equals("Create Fund")) {
			errors.add("Invalid Button");
		}

		return errors;
	}

}
