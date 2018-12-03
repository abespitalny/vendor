package resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BidDao;
import model.Bid;

/**
 * Servlet implementation class BidHistoryController
 */
public class BidHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidHistoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String auctionID = request.getParameter("auctionID");
		
		BidDao bidDao = new BidDao();
		List<Bid> bids = new ArrayList<Bid>(); 
		try {
			bids = bidDao.getBidHistory(auctionID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		request.setAttribute("bids", bids);
		RequestDispatcher rd = request.getRequestDispatcher("showBidHistory.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
