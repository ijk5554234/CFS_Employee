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
		return "CreateEmployee.do";
	}

	public String perform(HttpServletRequest request) {
		try {
			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			
			EmployeeRegisterForm form = (EmployeeRegisterForm) formBeanFactory.create(request);
			
			EmployeeBean employee = new EmployeeBean();
			
			employee.setUserName(form.getUserName());
			employee.setPassword(form.getPassword());
			employee.setFirstName(form.getFirstName());
			employee.setLastName(form.getLastName());
			employeeDAO.create(employee);
			
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (FormBeanException e) {

			e.printStackTrace();
		}

		return "CreateEmployee.jsp";

	}
}