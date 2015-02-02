/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package controller;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.TransactionBean;
import formbeans.ViewHistoryForm;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PriceDAO;
import model.TransactionDAO;

public class ViewCustomerHistoryAction extends Action {

    private CustomerDAO customerDAO;
    private FundDAO fundDAO;
    private TransactionDAO transactionDAO;

    private PriceDAO priceDAO;

    public ViewCustomerHistoryAction(Model model) {
        customerDAO = model.getCustomerDAO();
        fundDAO = model.geFundDAO();
        priceDAO = model.getPriceDAO();
        transactionDAO = model.getTransactionDAO();

    }

    public String getName() {
        return "employee_viewhistory.do";
    }

    public String perform(HttpServletRequest request) {
        EmployeeBean employ = (EmployeeBean) request.getSession(false)
                .getAttribute("employee");
        if (employ == null) {
            return "employee_login.do";
        }
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        request.setAttribute("title", "View Customer History");

        try {
            CustomerBean[] customers = customerDAO.match();
            request.setAttribute("customers", customers);
            String id = request.getParameter("customerId");
            if (id == null) {
                return "employee_view_customer_list.jsp";
            }
            int newId = Integer.parseInt(id);
            CustomerBean customer = customerDAO.read(newId);

            TransactionBean[] transactions = transactionDAO
                    .getTransactionsByCustomer(customer.getCustomerId());

            ViewHistoryForm[] records = new ViewHistoryForm[transactions.length];

            for (int i = 0; i < records.length; i++) {
                int fundId = transactions[i].getFundId();
                records[i] = new ViewHistoryForm();
                records[i].setTransactionId(transactions[i].getTransactionId());
                if (fundId != 0) {
                    String fundName = fundDAO.read(fundId).getFundName();
                    records[i].setFundName(fundName);
                }
                records[i].setType(transactions[i].getType());

                double share = (double) transactions[i].getShare() / 1000;
                DecimalFormat df = new DecimalFormat(",##0.000");
                String formattedShare = df.format(share);
                records[i].setShare(formattedShare);

                double lastPrice = (double) priceDAO.getLastDayByFund(fundId) / 100;
                DecimalFormat df2 = new DecimalFormat(",##0.00");
                String formattedLastPrice = df2.format(lastPrice);
                records[i].setPrice(formattedLastPrice);

                double amount = (double) transactions[i].getAmount() / 100;
                String formattedAmount = df2.format(amount);
                records[i].setAmount(formattedAmount);

                Date date = transactions[i].getDate();
                if (date == null) {
                    records[i].setDate("pending");
                    if (records[i].getType().equals("sell")
                            || records[i].getType().equals("buy")) {
                        records[i].setPrice("pending");
                        records[i].setShare("pending");
                    }
                } else {
                    SimpleDateFormat dsf = new SimpleDateFormat("MM/dd/yyyy");
                    records[i].setDate(dsf.format(date));
                }
                if (records[i].getType() != null && (records[i].getType().equals("request") || records[i].getType().equals("deposit"))) {
                	records[i].setPrice(" ");
                	records[i].setShare(" ");
                }              
            }
            request.setAttribute("msg", "This is the transaction history of "
                    + customer.toString());
            request.setAttribute("records", records);

        } catch (RollbackException e) {
            e.printStackTrace();
        }

        return "employee_viewhistory.jsp";
    }
}
