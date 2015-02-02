/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;
import databeans.EmployeeBean;
import databeans.TransactionBean;

public class ManageAction extends Action {
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;

	public ManageAction(Model model) {
		customerDAO = model.getCustomerDAO();
		fundDAO = model.geFundDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "employee_welcome.do";
	}

	public String perform(HttpServletRequest request) {
	    EmployeeBean employ = (EmployeeBean) request.getSession(false)
                .getAttribute("employee");
        if (employ == null) {
            return "employee_login.do";
        }
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		EmployeeBean employee = (EmployeeBean) request.getSession().getAttribute("employee");
		if (employee == null) return "employee_login.do";

		try {
			TransactionBean[] pendingTrans;

			pendingTrans = transactionDAO.match(MatchArg.equals("date", null));
			int customerSum = customerDAO.match().length;
			int fundSum = fundDAO.getCount();
			int totalTrans = pendingTrans.length;
			int buy = 0;
			long buySum = 0;
			int sell = 0;
			long sellSum = 0;
			int requestCheck = 0;
			long requestSum = 0;
			int depositCheck = 0;
			long depositSum = 0;

			for (int i = 0; i < pendingTrans.length; i++) {
				if (pendingTrans[i].getType().equals("buy")) {
					buy++;
					buySum += pendingTrans[i].getAmount();
				}
				
				if (pendingTrans[i].getType().equals("sell")) {
					sell++;
					sellSum += pendingTrans[i].getShare();
				}
				
				if (pendingTrans[i].getType().equals("request")) {
					requestCheck++;
					requestSum += pendingTrans[i].getAmount();
				}
				
				if (pendingTrans[i].getType().equals("deposit")) {
					depositCheck++;
					depositSum += pendingTrans[i].getAmount();
				}
			}
			DecimalFormat df = new DecimalFormat("0.00");
			DecimalFormat df2 = new DecimalFormat("0.000");
			request.setAttribute("customerSum", customerSum);
			request.setAttribute("fundSum", fundSum);
			request.setAttribute("totalTrans", totalTrans);
			request.setAttribute("buy", buy);
			request.setAttribute("buySum", df.format((double)buySum / 100));
			request.setAttribute("buydouble", Double.parseDouble(df.format((double) buySum / 100)));
			request.setAttribute("sell", sell);
			request.setAttribute("sellSum", df2.format((double) sellSum / 1000));
			request.setAttribute("requestCheck", requestCheck);
			request.setAttribute("requestSum", df.format((double)requestSum / 100));
			request.setAttribute("requestdouble", (double) requestSum / 100);
			request.setAttribute("depositCheck", depositCheck);
			request.setAttribute("depositSum", df.format((double)depositSum / 100));
			request.setAttribute("depositdouble", (double) depositSum / 100);
			

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "employee_welcome.jsp";
	}
}
