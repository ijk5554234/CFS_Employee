package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	

	public EmployeeDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(EmployeeBean.class, tableName, pool);
	}
	
	public void setPassword(String userName, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	EmployeeBean dbEmployee = read(userName);
			
			if (dbEmployee == null) {
				throw new RollbackException("Employee "+ userName +" no longer exists");
			}
			
			dbEmployee.setPassword(password);
			
			update(dbEmployee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
