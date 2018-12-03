package resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuctionDao;
import dao.ItemDao;

/**
 * Servlet implementation class BidInAuctionController
 */
public class BidInAuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidInAuctionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customerID = (String)request.getSession(false).getAttribute("customerID");
		
		String auctionID = request.getParameter("auctionID");
		String itemID = request.getParameter("itemID");
		
		AuctionDao auctionDao = new AuctionDao();
		List data;
		try {
			data = auctionDao.getAuctionData(auctionID, itemID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		request.setAttribute("item", data.get(0));
		request.setAttribute("bid", data.get(1));
		request.setAttribute("auction", data.get(2));
		request.setAttribute("winningCustomer", data.get(3));
		request.setAttribute("customerID", customerID);
		
		RequestDispatcher rd = request.getRequestDispatcher("bidInAuction.jsp");
		rd.forward(request, response);

	}

}
