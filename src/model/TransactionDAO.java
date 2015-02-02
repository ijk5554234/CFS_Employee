/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	public TransactionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(TransactionBean.class, tableName, pool);
	}
	
	public TransactionBean[] getTransactions() throws RollbackException {
		TransactionBean[] transactions = match();
		Arrays.sort(transactions); 
		return transactions;
	}
	
	public TransactionBean[] getTransactionsByCustomer(int customerId) throws RollbackException {
		TransactionBean[] transactions = match(MatchArg.equals("customerId", customerId));
		return transactions;
	}
}
