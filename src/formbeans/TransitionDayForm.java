package formbeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransitionDayForm {
	private String date;
	private String price;
	private String fundId;
	
	public String getDate()   { return date;  }
	public String getPrice()  { return price; }
	public String getFundId() { return fundId;}
	
	public void setDate(String s)   { date = s;   }
	public void setPrice(String s)  { price = s;  }
	public void setFundId(String s) { fundId = s; }
	
	public int getFundIdAsInt() {
		return Integer.parseInt(fundId);
		}
	
	public long getPriceAsLong() { 
		double price; 
		price = Double.parseDouble(this.price)/ 100;
		return (long) price;
	}
	public Date getDateAsDate() {
		
		String 
		Date date = new Date(this.date);
		
		
		return date;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundId == null || fundId.length() == 0) {
			errors.add("FundId is required");
			return errors;
		}

		try {
			Integer.parseInt(fundId);
			Double.parseDouble(this.price);
		} catch (NumberFormatException e) {
			errors.add("FundId is not an integer");
		}
		
		return errors;
	}
}
