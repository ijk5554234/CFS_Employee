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

import model.CustomerDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.TransactionBean;
import formbeans.DepositCheckForm;

public class DepositCheckAction extends Action {
    private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory
            .getInstance(DepositCheckForm.class);
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;

    public DepositCheckAction(model.Model model) {
        customerDAO = model.getCustomerDAO();
        transactionDAO = model.getTransactionDAO();
    }

    public String getName() {
        return "employee_depositcheck.do";
    }

    public String perform(HttpServletRequest request) {
        EmployeeBean employ = (EmployeeBean) request.getSession(false)
                .getAttribute("employee");
        if (employ == null) {
            return "employee_login.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        request.setAttribute("title", "Deposit Check");
        try {

            DepositCheckForm form = formBeanFactory.create(request);
            String ctmId = request.getParameter("customerId");
            request.setAttribute("customerId", ctmId);
            if (!form.isPresent()) {
                return "employee_depositcheck.jsp";

            }

            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "employee_depositcheck.jsp";
            }

            CustomerBean customer = customerDAO.read(Integer.parseInt(ctmId));
            long check = form.getAmountAsLong();
            if (check < 100) {
                errors.add("Amount should be at least one dollar.");
                return "employee_depositcheck.jsp";
            }
            TransactionBean trans = new TransactionBean();
            trans.setAmount(check);
            trans.setType("deposit");
            trans.setCustomerId(customer.getCustomerId());
            transactionDAO.create(trans);

            request.setAttribute("msg", "You have deposited "
                    + ((double) check / 100.0) + " for " + customer.toString());

        } catch (FormBeanException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "employee_depositcheck.jsp";
    }

}