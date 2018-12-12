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
 * Servlet implementation class GetAuctionHistoryController
 */
public class GetAuctionHistoryController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAuctionHistoryController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = (String)request.getSession(false).getAttribute("customerID");;
        BidDao bidDao = new BidDao();
        List<Bid> bids = bidDao.getAuctionHistory(customerID);

        request.setAttribute("bids", bids);
        RequestDispatcher rd = request.getRequestDispatcher("showAuctionHistory.jsp");
        rd.forward(request, response);
    }
}
