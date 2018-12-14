package resources;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BidDao;
import dao.ItemDao;
import java.math.BigDecimal;
import model.Item;

/**
 * Servlet implementation class SubmitBidController
 */
public class SubmitBidController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitBidController() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = (String)request.getSession().getAttribute("customerID");
        int auctionID = Integer.parseInt(request.getParameter("auctionID"));
        BigDecimal maxBid = BigDecimal.valueOf(Double.parseDouble(request.getParameter("maxBid")));
        BidDao bidDao = new BidDao();
        boolean success = bidDao.submitBid(auctionID, maxBid, customerID);
        
        if (success) {
            ItemDao itemDao = new ItemDao();
            Item item = itemDao.getItemFromAuctionID(auctionID);
            request.setAttribute("auctionID", auctionID);
            request.setAttribute("item", item);
        }
        RequestDispatcher rd = request.getRequestDispatcher("bidItem.jsp");
        rd.forward(request, response);
    }
}
