
	/*
	Jike Li
	Date: Jan. 19, 2015
	 */

	package controller;

	import java.text.DecimalFormat;
	import java.util.ArrayList;
	import java.util.List;

	import javax.servlet.http.HttpServletRequest;

	import org.genericdao.RollbackException;

	import databeans.FundBean;
	import databeans.PositionBean;
	import formbeans.PositionRecordBean;
	import model.CustomerDAO;
	import model.FundDAO;
	import model.Model;
	import model.PositionDAO;
	import model.PriceDAO;

	public class viewCustomerAccountAction extends Action {

		private CustomerDAO customerDAO;
		private FundDAO fundDAO;
		private PositionDAO positionDAO;
		private PriceDAO priceDAO;

		public viewCustomerAccountAction(Model model) {
			customerDAO = model.getCustomerDAO();
			fundDAO = model.geFundDAO();
			priceDAO = model.getPriceDAO();
			positionDAO = model.getPositionDAO();
		}

		public String getName() {
			// TODO Auto-generated method stub
			return "viewCustomerAccount.do";
		}

		public String perform(HttpServletRequest request) {
			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			
			
			
			int customerId = (int) request.getAttribute("customerId");

			PositionBean[] positions = null;

			try {
				positions = positionDAO.getPositionByCustomer(customerId);
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String formattedCash = null;
			try {
				double cash = customerDAO.read(customerId).getCash() / 100;
				DecimalFormat df2 = new DecimalFormat("0.00");
				formattedCash = df2.format(cash);
				
				
			} catch (RollbackException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			PositionRecordBean[] records = new PositionRecordBean[positions.length];

			for (int i = 0; i < records.length; i++) {
				int fundId = positions[i].getFundId();
				FundBean fund = null;
				try {
					fund = fundDAO.read(fundId);
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				records[i].setFundName(fund.getFundName());
				records[i].setSymbol(fund.getSymbol());

				double share = positions[i].getShare() / 1000;
				DecimalFormat df = new DecimalFormat("0.000");
				String formattedShare = df.format(share);
				records[i].setShare(formattedShare);

				try {
					double lastPrice = priceDAO.getLastDayByFund(fundId) / 100;
					DecimalFormat df2 = new DecimalFormat("0.00");
					String formattedLastPrice = df2.format(lastPrice);
					records[i].setLastPrice(formattedLastPrice);
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			request.setAttribute("records", records);
			request.setAttribute("cash", formattedCash);

			return "viewAccount.jsp";
		}
	}

