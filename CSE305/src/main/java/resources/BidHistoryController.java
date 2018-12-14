package resources;

import java.io.IOException;
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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidHistoryController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionID = Integer.parseInt(request.getParameter("auctionID"));
        BidDao bidDao = new BidDao();
        List<Bid> bids = bidDao.getBidHistory(auctionID); 

        request.setAttribute("bids", bids);
        RequestDispatcher rd = request.getRequestDispatcher("showBidHistory.jsp");
        rd.forward(request, response);
    }
}
