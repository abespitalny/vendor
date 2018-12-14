package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import model.Customer;

/**
 * Servlet implementation class GetSellersController
 */
public class GetSellersController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSellersController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> sellers = customerDao.getSellers();

        request.setAttribute("sellers", sellers);
        RequestDispatcher rd = request.getRequestDispatcher("showSellers.jsp");
        rd.forward(request, response);
    }
}
