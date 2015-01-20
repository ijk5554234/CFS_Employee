package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import formbeans.LoginForm;
import formbeans.RegisterForm;

public class CreateCustomerAction extends Action {

	public String getName() {
		return "CreateCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		
		RegisterForm form;
		try {
			form = (RegisterForm) FormBeanFactory.create(request);
			
			
			
		
		return null;
	}
		
		f
	
	
				
		
	}
	
	

}
