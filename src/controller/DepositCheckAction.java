package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.TransactionBean;
import formbeans.DepositCheckForm;

public class DepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(DepositCheckForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	
	public DepositCheckAction(model.Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "employee-depositCheck.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			DepositCheckForm depositForm = formBeanFactory.create(request);
			errors.addAll(depositForm.getValidationErrors());
			if (errors.size() > 0) {
				return "error.jsp";
			}
			
			CustomerBean[] customers = customerDAO.match();
			String email = depositForm.getEmail();
			int customerId = customerDAO.getCustomerByEmail(email).getCustomerId();
			long check = depositForm.getAmountAsLong();
			
			TransactionBean trans = new TransactionBean();
			trans.setAmount(check);
			trans.setType("deposit");
			trans.setCustomerId(customerId);
			
			transactionDAO.create(trans);
			request.setAttribute("customers",customers);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "employee_depositcheck.jsp";
	}

}