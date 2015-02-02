/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

	public LogoutAction(Model model) { }

	public String getName() { return "employee_logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("employee",null);
        
        request.setAttribute("message","You've successfully logged out!");

        return "employee_login.do";
    }
}
