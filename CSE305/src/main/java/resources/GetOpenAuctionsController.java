package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuctionDao;
import model.Auction;

/**
 * Servlet implementation class GetOpenAuctionsController
 */
public class GetOpenAuctionsController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOpenAuctionsController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = (String)request.getSession().getAttribute("employeeID");
        AuctionDao auctionDao = new AuctionDao();
        List<?>[] data = auctionDao.getOpenAuctions(employeeID);

        request.setAttribute("auctions", data[0]);
        request.setAttribute("bids", data[1]);
        RequestDispatcher rd = request.getRequestDispatcher("showOpenAuctions.jsp");
        rd.forward(request, response);
    }
}
