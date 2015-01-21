package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean{
	private String fundName;
	private String symbol;
	
	public String getFundName()   { return fundName; }
	public String getSymbol()     { return symbol;   }
	
	public void setFundName(String s) { fundName = trimAndConvert(s,"<>>\"]"); }
	public void setSymbol(String s)   { symbol   = trimAndConvert(s,"<>>\"]"); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (symbol.length() > 6) {
			errors.add("Ticker symbol is too long");
		}
		
		if (symbol == null || symbol.length() == 0) {
			errors.add("Confirm Pwd is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}

}
