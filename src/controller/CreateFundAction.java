package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FundBean;
import formbeans.CreateFundForm;

public class CreateFundAction extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	private FundDAO fundDAO;
	
	public CreateFundAction(Model model) {
		fundDAO = model.geFundDAO();
	}

	public String getName() {
		return "createFund.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CreateFundForm fundForm = formBeanFactory.create(request);
			errors.addAll(fundForm.getValidationErrors());
			if (errors.size() > 0) {
				return "error.jsp";
			}

			FundBean fund = new FundBean();
			fund.setFundName(fundForm.getFundName());
			fund.setSymbol(fundForm.getSymbol());

			fundDAO.create(fund);

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
