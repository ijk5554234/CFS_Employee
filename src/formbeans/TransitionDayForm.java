/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package formbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransitionDayForm {
	private String date;
	private String action;

	public void setDate(String s)   { date   = s; }
	public void setAction(String s) { action = s; }

	public String getDate ()		{	return date;	}
	public String getAction()		{	return action;	}
	
	public boolean isPresent() { return action != null; }

	public List<String> getValidationErrors (HashMap<String, String> map) {
		List<String> errors = new ArrayList<String>();

		if (date == null || date.length() == 0 )
			errors.add("Date is required");
		if (action == null) errors.add("Button is required");

		if (errors.size() > 0) 	return errors;
		
        if (!action.equals("create")) {
        	errors.add("Invalid button");
        	return errors;
        }

        try {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	dateFormat.setLenient(false);
        	dateFormat.parse(date);
        } catch (Exception e) {
        	errors.add("Invalid date input");
        }
        
        for (String price : map.values()) {
        	try {
        		if (price == null || price.length() == 0)
        			throw new Exception();
            	double d = Double.parseDouble(price);
            	if (d <= 0 || d > Integer.MAX_VALUE) {
            		throw new Exception();
            	}
            } catch (Exception e) {
            	 errors.add("Price should be a positive number");
            	 break;
            }
        }
    
		return errors;
	}
}
