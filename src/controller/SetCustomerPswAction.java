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
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import formbeans.CustomerChangePwdForm;

public class SetCustomerPswAction extends Action {
    private FormBeanFactory<CustomerChangePwdForm> formBeanFactory = FormBeanFactory
            .getInstance(CustomerChangePwdForm.class);
    private CustomerDAO customerDAO;

    public SetCustomerPswAction(Model model) {
        customerDAO = model.getCustomerDAO();
    }

    public String getName() {
        return "employee_setcustomerpsw.do";
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
            request.setAttribute("title", "Change Customer Password");
            
            String ctmId= request.getParameter("customerId");
            request.setAttribute("customerId", ctmId);

            CustomerBean customer = customerDAO.read(Integer.parseInt(ctmId));
            request.setAttribute("customer", customer);
            
            CustomerChangePwdForm form = (CustomerChangePwdForm) formBeanFactory
                    .create(request);
            

            if (!form.isPresent())
                return "employee_setcustomerpsw.jsp";
      
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0)
                return "employee_setcustomerpsw.jsp";
            
            customerDAO.setPassword(customer.getCustomerId(),
                    form.getNewPassword());
            
            request.setAttribute("msg",
                    "Password of customer " + customer.toString()
                            + " has been changed");

        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (FormBeanException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "employee_setcustomerpsw.jsp";

    }
}
