package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuctionDao;

/**
 * Servlet implementation class RecordSaleController
 */
public class RecordSaleController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordSaleController() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionID = Integer.parseInt(request.getParameter("auctionID"));
        AuctionDao auctionDao = new AuctionDao();
        String status = auctionDao.recordSale(auctionID);

        response.sendRedirect("customerRepresentativeHome.jsp?auctionID=" + auctionID + "&status=" + status);
    }
}
