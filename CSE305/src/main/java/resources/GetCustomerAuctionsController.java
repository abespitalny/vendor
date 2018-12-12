package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuctionDao;
import model.Auction;

/**
 * Servlet implementation class GetCustomerAuctionsController
 */
public class GetCustomerAuctionsController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerAuctionsController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = (String)request.getSession().getAttribute("customerID");
        AuctionDao auctionDao = new AuctionDao();
        List<Auction> auctions = auctionDao.getAuctions(customerID);

        request.setAttribute("auctions", auctions);
        RequestDispatcher rd = request.getRequestDispatcher("showAllAuctions.jsp");
        rd.forward(request, response);
    }
}
