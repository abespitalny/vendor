package resources;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import model.Customer;

/**
 * Servlet implementation class GetHighestRevenueCustomerController
 */
public class GetHighestRevenueCustomerController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHighestRevenueCustomerController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getHighestRevenueCustomer();

        request.setAttribute("customer", customer);
        RequestDispatcher rd = request.getRequestDispatcher("showHighestRevenueCustomer.jsp");
        rd.forward(request, response);
    }
}
