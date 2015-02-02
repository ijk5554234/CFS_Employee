/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.Model;
import databeans.EmployeeBean;
import formbeans.LoginForm;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);

	private EmployeeDAO employeeDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "employee_login.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm form = formBeanFactory.create(request);

			if (!form.isPresent()) {
				
				return "employee_login.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "employee_login.jsp";
			}

			// Look up the user
			EmployeeBean employee = employeeDAO.read(form.getUserName());
			

			if (employee == null) {
				errors.add("UserName not found");
				return "employee_login.jsp";
			}

			// Check the password
			if (!(employee.getPassword().equals(form.getPassword()))) {
				errors.add("Incorrect password");
				return "employee_login.jsp";
			}

			
			
			// Attach (this copy of) the user bean to the session
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);

			
			EmployeeBean employee1 = (EmployeeBean) request.getSession().getAttribute("employee");
	        if (employee1 == null) 
	            System.out.println("not sessioned");

		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "employee_welcome.do";
	}
}