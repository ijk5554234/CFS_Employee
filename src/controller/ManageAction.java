/*
Jike Li
Andrew ID:jikel
08-600
Homework #9
Date: Nov. 29, 2014
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import databeans.EmployeeBean;

public class ManageAction extends Action {

	public ManageAction(Model model) {

	}

	public String getName() {
		return "manage.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		EmployeeBean employee = (EmployeeBean) request.getSession().getAttribute("employee");
		if (employee == null) return "employee-login.jsp";
		return "index.jsp";
	}
}
