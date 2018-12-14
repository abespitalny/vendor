package resources;

import dao.AuctionDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Auction;
import model.Customer;

/**
 * Servlet implementation class GetBidController
 */
public class GetBidController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBidController() {
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
        
        String text = "";
        if (data != null) {
            text += ((Auction)(data.get(0))).getCurrentHighestBidPrice();
            text += ",";
            Customer customer = ((Customer)(data.get(2)));
            text += (customer.getFirstName() + " " + customer.getLastName());
        }
        
        // Set content type of the response so that jQuery knows what it can expect.
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        // Write response body.
        response.getWriter().write(text);       	
    }
}
