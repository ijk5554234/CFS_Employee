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

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.FundBean;
import databeans.PositionBean;
import formbeans.ViewAccountForm;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.PriceDAO;

public class ViewCustomerAccountAction extends Action {

    private CustomerDAO customerDAO;
    private FundDAO fundDAO;
    private PositionDAO positionDAO;
    private PriceDAO priceDAO;

    public ViewCustomerAccountAction(Model model) {
        customerDAO = model.getCustomerDAO();
        fundDAO = model.geFundDAO();
        priceDAO = model.getPriceDAO();
        positionDAO = model.getPositionDAO();
    }

    public String getName() {
        return "employee_viewaccount.do";
    }

    public String perform(HttpServletRequest request) {
        EmployeeBean employ = (EmployeeBean) request.getSession(false)
                .getAttribute("employee");
        if (employ == null) {
            return "employee_login.do";
        }
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        request.setAttribute("title", "View Customer Account");

        try {
             
            String id = request.getParameter("customerId");
            if(id == null){
                return "employee_view_customer_list.jsp";
            }
            
            int newId = Integer.parseInt(id);
            CustomerBean customer = customerDAO.read(newId);
            PositionBean[] positions = positionDAO
                    .getPositionByCustomer(customer.getCustomerId());
            String formattedCash = null;
            double cash = (double) customer.getCash() / 100;
            DecimalFormat df0 = new DecimalFormat(",##0.00");
            formattedCash = df0.format(cash);

            ViewAccountForm[] records = new ViewAccountForm[positions.length];
            for (int i = 0; i < records.length; i++) {
            	records[i] = new ViewAccountForm();
                int fundId = positions[i].getFundId();
                FundBean fund = null;
                try {
                    fund = fundDAO.read(fundId);
                } catch (RollbackException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                records[i] = new ViewAccountForm();
                records[i].setFundName(fund.getFundName());
                records[i].setSymbol(fund.getSymbol());

                double share = positions[i].getShare() / 1000;
                DecimalFormat df = new DecimalFormat(",##0.000");
                String formattedShare = df.format(share);
                records[i].setShare(formattedShare);

                double lastPrice = priceDAO.getLastDayByFund(fundId) / 100;
                DecimalFormat df2 = new DecimalFormat(",##0.00");
                String formattedLastPrice = df2.format(lastPrice);
                records[i].setLastPrice(formattedLastPrice);
            }


            request.setAttribute("msg", "This is the account information of "
                    + customer.toString());
            request.setAttribute("customer", customer);
            request.setAttribute("records", records);
            request.setAttribute("cash", formattedCash);

        } catch (RollbackException e) {
            e.printStackTrace();
        }

        return "employee_viewaccount.jsp";
    }
}
