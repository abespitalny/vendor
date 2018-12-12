package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuctionDao;

/**
 * Servlet implementation class BidInAuctionController
 */
public class BidInAuctionController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidInAuctionController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionID = Integer.parseInt(request.getParameter("auctionID"));
        AuctionDao auctionDao = new AuctionDao();
        List<Object> data = auctionDao.getAuctionData(auctionID);
        
        if (data != null) {
            request.setAttribute("auction", data.get(0));
            request.setAttribute("item", data.get(1));
            request.setAttribute("winningCustomer", data.get(2));
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("bidInAuction.jsp");
        rd.forward(request, response);
    }
}
