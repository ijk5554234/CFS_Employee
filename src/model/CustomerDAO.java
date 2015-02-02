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
import org.genericdao.Transaction;

import databeans.CustomerBean;


public class CustomerDAO extends GenericDAO<CustomerBean> {
	

	public CustomerDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CustomerBean.class, tableName, pool);
	}
	
	public CustomerBean[] getCustomers() throws RollbackException {
		CustomerBean[] customers = match();
		Arrays.sort(customers);  // We want them sorted by last and first names (as per Customer.compareTo());
		return customers;
	}
	
	public void setPassword(int customerId, String password) throws RollbackException {
        try {
        	Transaction.begin();
			CustomerBean dbCustomer = read(customerId);
			
			if (dbCustomer == null) {
				throw new RollbackException("Email Address "+ customerId +" no longer exists");
			}
			
			dbCustomer.setPassword(password);
			
			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public CustomerBean getCustomerByEmail(String email) throws RollbackException {
			CustomerBean[] dbCustomer = match(MatchArg.equals("email", email));
			if (dbCustomer.length == 0) return null;
			return dbCustomer[0];

	}
}
