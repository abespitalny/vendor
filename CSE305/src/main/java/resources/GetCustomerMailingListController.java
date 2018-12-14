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
 * Servlet implementation class GetCustomerMailingListController
 */
public class GetCustomerMailingListController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerMailingListController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getCustomerMailingList(); 

        request.setAttribute("customers", customers);
        RequestDispatcher rd = request.getRequestDispatcher("showCustomerMailingList.jsp");
        rd.forward(request, response);
    }
}
