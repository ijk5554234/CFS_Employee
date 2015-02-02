/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package databeans;

import java.sql.Date;
import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,date")
public class PriceBean {

	private int  fundId;
	private long price;
	private Date date;

		public int    getFundId()   { return fundId;}
		public long   getPrice()   	{ return price; }
		public Date   getDate()     { return date;  }

		public void setFundId(int i) 	{ fundId = i; }
		public void setPrice(long l) 	{ price  = l; }
	    public void setDate(Date d) 	{ date   = d; }
}
