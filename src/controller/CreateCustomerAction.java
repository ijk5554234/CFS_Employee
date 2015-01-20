package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import formbeans.RegisterForm;

public class CreateCustomerAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	private CustomerDAO customerDAO;

	public CreateCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "CreateCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		try {
			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);

			RegisterForm form = null;

			try {
				form = (RegisterForm) formBeanFactory.create(request);
			} catch (FormBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CustomerBean customer = new CustomerBean();
			customer.setEmail(form.getEmail());
			customer.setPassword(form.getPassword());
			customer.setFirstName(form.getFirstName());
			customer.setLastName(form.getLastName());
			customer.setAddrLine1(form.getAddrLine1());
			customer.setAddrLine2(form.getAddrLine2());
			customer.setCity(form.getCity());
			customer.setZip(form.getZip());

			Double cash = 0.0;
			try {
				cash = Double.parseDouble(form.getCash());
			} catch (NumberFormatException e1) {
				errors.add("Cash value is not valid.");
				return "error.jsp";
			}
			long formattedCash = (long) (cash / 100);
			
			
			customer.setCash(formattedCash);

			customerDAO.create(customer);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
