/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package formbeans;

import org.mybeans.form.FormBean;

public class ViewAccountForm extends FormBean {
	
	private String fundName;
	private String symbol;
	private String share;
	private String lastPrice;
	private String originalPrice;
	
	public void setFundName(String s)		{ fundName      = s; }
	public void setSymbol(String s)		    { symbol        = s; }
	public void setShare(String s) 			{ share         = s; }
	public void setLastPrice(String s) 		{ lastPrice     = s; }
	public void setOriginalPrice(String s) 	{ originalPrice = s; }
	
	public String getFundName() 	 { return fundName;      }
	public String getSymbol()		 { return symbol;        }
	public String getShare() 		 { return share;         }
	public String getLastPrice()     { return lastPrice;     }
	public String getOriginalPrice() { return originalPrice; }

}
