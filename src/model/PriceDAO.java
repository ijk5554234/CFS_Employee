/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package model;

import java.sql.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.PriceBean;

public class PriceDAO extends GenericDAO<PriceBean>{

	public PriceDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(PriceBean.class, tableName, pool);
	}
	
	public PriceBean[] getAllPrices( ) throws RollbackException {
		PriceBean[] prices = match();
		return prices;
	}
	
	public Date getLastDay() throws RollbackException {
		PriceBean[] prices = match(MatchArg.max("date"));
		if (prices.length == 0){
			return null;
		}
		return prices[0].getDate();
	}
	
	public PriceBean[] getLastDayPrices() throws RollbackException {
		Date lastDay = getLastDay();
		if (lastDay == null) return null;
		PriceBean[] lastDayPrices = match(MatchArg.equals("date", lastDay));
		return lastDayPrices;
	} 
	
	public long getLastDayByFund(int fundId) throws RollbackException {
		Date lastDay = getLastDay();
		if (lastDay == null) return 0;
		PriceBean lastDayPrice = read(fundId, lastDay);
		if (lastDayPrice == null) return 0;
		return lastDayPrice.getPrice();
	} 
}
