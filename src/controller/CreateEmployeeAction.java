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

import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;



import databeans.EmployeeBean;
import formbeans.EmployeeRegisterForm;


public class CreateEmployeeAction extends Action {
	private FormBeanFactory<EmployeeRegisterForm> formBeanFactory = FormBeanFactory.getInstance(EmployeeRegisterForm.class);
	private EmployeeDAO employeeDAO;

	public CreateEmployeeAction(Model model) { 
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "employee_createemployee.do";
	}

	public String perform(HttpServletRequest request) {
		try {
		    EmployeeBean employ = (EmployeeBean) request.getSession(false)
                    .getAttribute("employee");
            if (employ == null) {
                return "employee_login.do";
            }
			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			
			EmployeeRegisterForm form = (EmployeeRegisterForm) formBeanFactory.create(request);
			if (!form.isPresent()) return "employee_createemployee.jsp";
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "employee_createemployee.jsp";
			}
			
			if (employeeDAO.read(form.getUserName()) != null) {
				errors.add("UserName already exists.");
				return "employee_createemployee.jsp";
			}
			EmployeeBean employee = new EmployeeBean();
			
			employee.setUserName(form.getUserName());
			employee.setPassword(form.getPassword());
			employee.setFirstName(form.getFirstName());
			employee.setLastName(form.getLastName());
			employeeDAO.create(employee);
			request.setAttribute("msg", "Successful created employee account: " +  employee.getUserName());
			
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (FormBeanException e) {

			e.printStackTrace();
		}
		
		return "employee_createemployee.jsp";

	}
}