package resources;

import dao.BidDao;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;

/**
 * Servlet implementation class GetSalesReportController
 */
public class GetSalesReportController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSalesReportController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));

        BidDao bidDao = new BidDao();
        List<Item> items = bidDao.getSalesReport(month, year);

        request.setAttribute("items", items);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        System.out.println(month + year);
        RequestDispatcher rd = request.getRequestDispatcher("showSalesReport.jsp");
        rd.forward(request, response);
    }
}
