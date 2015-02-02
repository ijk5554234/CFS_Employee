/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package controller;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.PriceDAO;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.FundBean;
import databeans.PositionBean;
import databeans.PriceBean;
import databeans.TransactionBean;
import formbeans.TransitionDayForm;

public class TransitionDayAction extends Action {

	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private PriceDAO priceDAO;
	private PositionDAO positionDAO;

	public TransitionDayAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.geFundDAO();
		priceDAO = model.getPriceDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() {
		return "employee_transitionday.do";
	}

	public String perform(HttpServletRequest request) {
		EmployeeBean employ = (EmployeeBean) request.getSession(false).getAttribute("employee");
		if (employ == null) {
			return "employee_login.do";
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			TransitionDayForm form = new TransitionDayForm();
			form.setAction((String) request.getParameter("action"));
			form.setDate((String) request.getParameter("date"));
			request.setAttribute("form", form);

			FundBean[] fundList = fundDAO.match();
			if (fundList.length == 0) {
				errors.add("Thers is no fund, please add funds.");
				return "employee_transitionday.jsp";
			}
			request.setAttribute("fundList", fundList);

			NumberFormat formatter = new DecimalFormat("0.00");
			HashMap<Integer, String> prices = new HashMap<Integer, String>();
			String lastTradingDay = null;
			if (priceDAO.getLastDay() != null) lastTradingDay = priceDAO.getLastDay().toString();
			for (FundBean fb : fundList) {
				if (lastTradingDay == null) {
					prices.put(fb.getFundId(), "N/A");
					continue;
				}
				PriceBean price = priceDAO.read(fb.getFundId(), priceDAO.getLastDay());
				if (price == null) {
					prices.put(fb.getFundId(), "N/A");
				} else {
					prices.put(fb.getFundId(), formatter.format(price.getPrice() / 100.0));
				}
			}
			request.setAttribute("prices", prices);

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			inputDate.setLenient(false);
			Date lastDay = priceDAO.getLastDay();

			request.setAttribute("lastDay", lastDay == null ? "" : inputDate.format(lastDay));

			if (!form.isPresent()) {
				return "employee_transitionday.jsp";
			}

			HashMap<String, String> map = new HashMap<String, String>();
			for (FundBean fb : fundList) {
				map.put("fund_" + fb.getFundId(), request.getParameter("fund_" + fb.getFundId()));
			}
			errors.addAll(form.getValidationErrors(map));

			if (errors.size() != 0) {
				return "employee_transitionday.jsp";
			}

			Date today = new Date(inputDate.parse(form.getDate()).getTime());

			if (lastDay != null && today.compareTo(lastDay) <= 0) {
				errors.add("The input date should be after the last trading day.");
			}

			if (errors.size() != 0) {
				return "employee_transitionday.jsp";
			}

			try {
				Transaction.begin();

				for (FundBean fund : fundList) {
					PriceBean newPrice = new PriceBean();
					newPrice.setFundId(fund.getFundId());
					newPrice.setDate(today);
					newPrice.setPrice((long) (Double.parseDouble(request.getParameter("fund_" + fund.getFundId())) * 100));
					priceDAO.create(newPrice);
				}

				for (TransactionBean transaction : transactionDAO.match(MatchArg.equals("date", null))) {
					CustomerBean customer = customerDAO.read(transaction.getCustomerId());

					String type = transaction.getType();

					if (type.equals("sell")) {

						if (positionDAO.read(transaction.getCustomerId(), transaction.getFundId()) != null) {
							double price = priceDAO.read(transaction.getFundId(), today).getPrice() / 100.0;
							long amount = (long) (price * transaction.getShare() / 1000 * 100);
							customer.setCash(customer.getCash() + amount);
							customerDAO.update(customer);

							transaction.setAmount(amount);
						}
					}
					if (type.equals("buy")) {
						long shares = 0;
						if (positionDAO.read(transaction.getCustomerId(), transaction.getFundId()) == null) {
							double amount = transaction.getAmount() / 100.00;
							double price = priceDAO.read(transaction.getFundId(), today).getPrice() / 100.0;
							shares = (long) (amount / price * 1000);
							if (shares >= 0.01) {
								PositionBean position = new PositionBean();
								position.setCustomerId(customer.getCustomerId());
								position.setFundId(transaction.getFundId());
								position.setShare(shares);
								position.setOriginalPrice(priceDAO.read(transaction.getFundId(), today).getPrice());
								positionDAO.create(position);
							} else {
								long temp = customer.getCash();
								customer.setCash(temp + transaction.getAmount());
								customerDAO.update(customer);

								transaction.setType("buy(cancelled)");
							}

						} else {
							double amount = transaction.getAmount() / 100.00;
							double price = priceDAO.read(transaction.getFundId(), today).getPrice() / 100.0;
							shares = (long) (amount / price * 1000);

							PositionBean position = positionDAO.read(transaction.getCustomerId(), transaction.getFundId());
							double oPrice = position.getOriginalPrice() / 100.00;
							double newPrice = (price * amount + oPrice * (position.getShare() / 1000)) / (amount + position.getShare());
							position.setShare(shares + position.getShare());
							position.setOriginalPrice((long) newPrice * 100);
							positionDAO.update(position);
						}

						transaction.setShare(shares);

					}
					if (type.equals("request")) {
						customer.setCash(customer.getCash() - transaction.getAmount());
						customerDAO.update(customer);
					}
					if (type.equals("deposit")) {
						customer.setCash(customer.getCash() + transaction.getAmount());
						customerDAO.update(customer);
					}

					transaction.setDate(today);
					transactionDAO.update(transaction);
				}

				Transaction.commit();
			} finally {
				if (Transaction.isActive()) {
					Transaction.rollback();
				}
			}

			prices = new HashMap<Integer, String>();
			lastTradingDay = priceDAO.getLastDay().toString();
			for (FundBean fund : fundList) {
				if (lastTradingDay == null) {
					prices.put(fund.getFundId(), "N/A");
					continue;
				}
				PriceBean price = priceDAO.read(fund.getFundId(), priceDAO.getLastDay());
				if (price == null) {
					prices.put(fund.getFundId(), "N/A");
				} else {
					prices.put(fund.getFundId(), formatter.format(price.getPrice() / 100.0));
				}
			}
			request.setAttribute("prices", prices);

			lastDay = priceDAO.getLastDay();
			request.setAttribute("lastDay", lastDay == null ? "" : inputDate.format(lastDay));
			request.setAttribute("msg", "Transition successfully, the new date is " + lastDay + ".");

			return "employee_transitionday.jsp";

		} catch (RollbackException e) {
			e.printStackTrace();
			return "employee_transitionday.jsp";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "employee_transitionday.jsp";
	}
}