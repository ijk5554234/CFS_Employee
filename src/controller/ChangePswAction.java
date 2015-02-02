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
import formbeans.EmployeeChangePwdForm;

public class ChangePswAction extends Action {
	private FormBeanFactory<EmployeeChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(EmployeeChangePwdForm.class);
	private EmployeeDAO employeeDAO;

	public ChangePswAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "employee_changepsw.do";
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

			EmployeeChangePwdForm form = (EmployeeChangePwdForm) formBeanFactory.create(request);
			
				if (!form.isPresent()) return "employee_changepsw.jsp";
				
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) return "employee_changepsw.jsp";
				
				EmployeeBean employee = (EmployeeBean) request.getSession().getAttribute("employee");
				
				employeeDAO.setPassword(employee.getUserName(), form.getNewPassword());
				request.setAttribute("msg", "Password of you has been changed");

		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "employee_changepsw.jsp";

	}
}

