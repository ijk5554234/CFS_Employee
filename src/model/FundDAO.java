/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(FundBean.class, tableName, pool);
	}	

}
