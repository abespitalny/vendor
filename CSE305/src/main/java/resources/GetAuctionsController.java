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
 * Servlet implementation class GetAuctionsController
 */
public class GetAuctionsController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAuctionsController() {
        super();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuctionDao auctionDao = new AuctionDao();
        List<Auction> auctions = auctionDao.getAllAuctions();

        request.setAttribute("auctions", auctions);
        RequestDispatcher rd = request.getRequestDispatcher("showAllAuctions.jsp");
        rd.forward(request, response);
    }
}
