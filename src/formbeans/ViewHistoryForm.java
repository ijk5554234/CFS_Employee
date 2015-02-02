/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package formbeans;

import org.mybeans.form.FormBean;

public class ViewHistoryForm extends FormBean {
	
	private int transactionId;
	private String type;
	private String fundName;
	private String share;
	private String amount;
	private String date;
	private String price;
	
	public void setTransactionId(int i)     { transactionId = i; }
	public void setType(String s)           { type          = s; }
	public void setFundName(String s)		{ fundName      = s; }
	public void setShare(String s) 			{ share         = s; }
	public void setPrice(String s) 	        { price         = s; }
	public void setAmount(String s)         { amount        = s; }
	public void setDate(String s)           { date          = s; }
	
	public int    getTransactionId() { return transactionId; }
	public String getType()          { return type;          }
    public String getFundName() 	 { return fundName;      }
	public String getShare() 		 { return share;         }
	public String getPrice()         { return price;         }
	public String getDate()          { return date;          }
	public String getAmount()        { return amount;        }

}
