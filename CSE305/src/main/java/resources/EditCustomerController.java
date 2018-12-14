package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import javax.servlet.RequestDispatcher;
import model.Customer;

/**
 * Servlet implementation class EditCustomerController
 */
public class EditCustomerController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomerController() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = request.getParameter("customerID");
        CustomerDao customerDao = new CustomerDao();
        Customer editCustomer = customerDao.getCustomer(customerID);
        
        // old customerID
        request.getSession(true).setAttribute("oldCustomerID", customerID);
        request.setAttribute("editCustomer", editCustomer);
        RequestDispatcher rd = request.getRequestDispatcher("editCustomer.jsp");
        rd.forward(request, response);
    }
}
