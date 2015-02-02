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

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.CustomerCashBean;
import databeans.EmployeeBean;

public class ViewCustomerListAction extends Action {

    private CustomerDAO customerDAO;

    public ViewCustomerListAction(Model model) {
        customerDAO = model.getCustomerDAO();
    }

    public String getName() {
        return "customer_view_list.do";
    }

    public String perform(HttpServletRequest request) {
        EmployeeBean employ = (EmployeeBean) request.getSession(false)
                .getAttribute("employee");
        if (employ == null) {
            return "employee_login.do";
        }
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        request.setAttribute("title", "View Customer List");

        try {
            CustomerBean[] customers = customerDAO.match();
            CustomerCashBean[] cashRecords = new CustomerCashBean[customers.length];
            if (customers.length > 0) {
                for (int i = 0; i < customers.length; i++) {
                    cashRecords[i] = new CustomerCashBean();
                    double cash = ((double) (customers[i].getCash())) / 100.0;
                    DecimalFormat df = new DecimalFormat(",##0.00");
                    String formattedcash = df.format(cash);
                    cashRecords[i].setCustomerId(customers[i].getCustomerId());
                    cashRecords[i].setCash(formattedcash);
                    cashRecords[i].setEmail(customers[i].getEmail());
                    cashRecords[i].setAddrLine1(customers[i].getAddrLine1());
                    cashRecords[i].setAddrLine2(customers[i].getAddrLine2());
                    cashRecords[i].setCity(customers[i].getCity());
                    cashRecords[i].setFirstName(customers[i].getFirstName());
                    cashRecords[i].setLastName(customers[i].getLastName());
                    cashRecords[i].setState(customers[i].getState());
                    cashRecords[i].setZip(customers[i].getZip());
                }

            }
            request.setAttribute("customers", cashRecords);
        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "employee_view_customer_list.jsp";
    }

}
