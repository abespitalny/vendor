package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BidDao;

/**
 * Servlet implementation class GetSalesController
 */
public class GetSalesController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSalesController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchBy = request.getParameter("searchBy");
        String searchKeyword = request.getParameter("searchKeyword");
        BidDao bidDao = new BidDao();
        List<?>[] data = bidDao.getSalesListing(searchBy, searchKeyword); 

        request.setAttribute("bids", data[0]);
        request.setAttribute("auctions", data[1]);
        RequestDispatcher rd = request.getRequestDispatcher("showSalesListing.jsp");
        rd.forward(request, response);
    }
}
