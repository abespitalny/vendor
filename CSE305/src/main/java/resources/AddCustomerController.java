package resources;

import dao.CustomerDao;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 * Servlet implementation class AddCustomerController
 */
public class AddCustomerController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerController() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state= request.getParameter("state");
        String zipcode = request.getParameter("zipCode");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String creditCardNum = request.getParameter("creditCardNum");
        BigDecimal rating = null;
        try {
            rating = BigDecimal.valueOf(Double.parseDouble(request.getParameter("rating")));
        } catch (Exception e) {}

        Customer customer = new Customer(username, password, firstName, lastName, address, city, state, zipcode, telephone,
            email, creditCardNum, rating);

        CustomerDao customerDao = new CustomerDao();
        String status = customerDao.addCustomer(customer);        
        if(status.equals("success"))
            response.sendRedirect("customerRepresentativeHome.jsp?status=addCustomerSuccess");
        else
            response.sendRedirect("addCustomer.jsp?status=error");
    }
}
