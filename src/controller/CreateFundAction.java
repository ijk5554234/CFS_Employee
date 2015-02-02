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

import model.FundDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.EmployeeBean;
import databeans.FundBean;
import formbeans.CreateFundForm;

public class CreateFundAction extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	private FundDAO fundDAO;

	public CreateFundAction(Model model) {
		fundDAO = model.geFundDAO();
	}

	public String getName() {
		return "employee_createfund.do";
	}

	public String perform(HttpServletRequest request) {
		EmployeeBean employ = (EmployeeBean) request.getSession(false).getAttribute("employee");
		if (employ == null) {
			return "employee_login.do";
		}
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CreateFundForm form = formBeanFactory.create(request);

			if (!form.isPresent()) {
				return "employee_createfund.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "employee_createfund.jsp";
			}

			FundBean fund = new FundBean();

			fund.setFundName(form.getFundName());
			fund.setSymbol(form.getSymbol());
			if (fundDAO.match(MatchArg.equals("fundName", fund.getFundName())).length != 0) {
				errors.add("FundName already exists");
				return "employee_createfund.jsp";
			}
			if (fundDAO.match(MatchArg.equals("symbol", fund.getSymbol())).length != 0) {
				errors.add("Ticker symbol already exists");
				return "employee_createfund.jsp";
			}

			fundDAO.create(fund);
			String msg = "Fund " + form.getFundName() + " has been created";
			request.setAttribute("msg", msg);

		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "employee_createfund.jsp";
	}

}