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
 * Servlet implementation class GetCustomersController
 */
public class GetCustomersController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomersController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKeyword = request.getParameter("searchKeyword");
        // return all customers if searchKeyword isn't specified
        if (searchKeyword == null)
            searchKeyword = "";
        
        CustomerDao dao = new CustomerDao();
        List<Customer> customers = dao.getCustomers(searchKeyword); 

        request.setAttribute("customers", customers);
        RequestDispatcher rd = request.getRequestDispatcher("showCustomer.jsp");
        rd.forward(request, response);
    }
}
