/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import formbeans.RegisterForm;

public class CreateCustomerAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	private CustomerDAO customerDAO;

	public CreateCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "employee_createcustomer.do";
	}

	public String perform(HttpServletRequest request) {
		EmployeeBean employ = (EmployeeBean) request.getSession(false).getAttribute("employee");
		if (employ == null) {
			return "employee_login.do";
		}
		try {

			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);

			RegisterForm form = (RegisterForm) formBeanFactory.create(request);

			if (!form.isPresent()) return "employee_createcustomer.jsp";

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "employee_createcustomer.jsp";
			}

			CustomerBean[] cb = customerDAO.match(MatchArg.equals("email", form.getEmail()));
			if (cb.length != 0) {
				errors.add("Email already exists.");
				return "employee_createcustomer.jsp";
			}

			CustomerBean customer = new CustomerBean();
			customer.setEmail(form.getEmail());
			customer.setFirstName(form.getFirstName());
			customer.setLastName(form.getLastName());
			customer.setAddrLine1(form.getAddrLine1());
			customer.setAddrLine2(form.getAddrLine2());
			customer.setCity(form.getCity());
			customer.setState(form.getState());
			customer.setZip(form.getZip());

			Double cash = 0.0;
			try {
				cash = Double.parseDouble(form.getCash());
			} catch (NumberFormatException e1) {
				errors.add("Cash value is not valid.");
				return "employee_createcustomer.jsp";
			}
			long formattedCash = (long) (cash * 100);
			if (cash < 0) {
				errors.add("Cash should be more than 0 ");
			}
			customer.setCash(formattedCash);

			request.setAttribute("msg", "Customer account for " + customer.getFirstName() + " " + customer.getLastName() + " has been created");
			SimpleEmail email = new SimpleEmail();

			email.setTLS(true);
			email.setSSL(true);
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);

			email.setAuthentication("cfsteam5help@gmail.com", "helphelph");
			int i = 8;
			String pwd = "";
			while (i-- > 0) {
				int a = (int) (Math.random() * 26) + 'a';
				char b = (char) a;
				pwd += b;
			}
			customer.setPassword(pwd);
			customerDAO.create(customer);
			System.out.print(pwd);
			try {
				email.addTo(customer.getEmail());
				email.setFrom("cfsteam5help@gmail.com");
				email.setSubject("Account Create Confirm");
				email.setCharset("utf-8");

				email.setContent(new MimeMultipart("text/html"));
				email.setMsg("Dear " + customer.getFirstName() + "," + "\n" + " Thanks for using Carnegie Financial Service! Your temp-password is:"
						+ pwd + "." + "\n" + "You can change password through this link: http://54.173.57.219:8080/home%20page/");
				email.send();
				request.setAttribute("pwd", pwd);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "employee_createcustomer.jsp";

	}
}
