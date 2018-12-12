package resources;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;
import model.Customer;
import model.Employee;
import model.EmployeeLevel;
import model.VendorUser;
/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

    /**
     * This method is called by the login button.
     * It receives the username and password, and sends them to LoginDao's login method for processing.
     * On Success, you enter the vendor portal; else, you get redirected back to the Home page.
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginDao loginDao = new LoginDao();
        VendorUser user = loginDao.login(username, password);

        if(user != null) {
            // Role values:
            // - manager
            // - customer_rep
            // - customer
            String role = null;
            if (user instanceof Customer) {
                role = "customer";
                request.getSession(true).setAttribute("customerID", username);
            // must be an instance of Employee
            } else {
                Employee employee = (Employee)user;
                if (employee.getLevel() == EmployeeLevel.Manager)
                    role = "manager";
                else
                    role = "customer_rep";

                request.getSession(true).setAttribute("employeeID", username);
            }

            request.getSession(true).setAttribute("email", user.getEmail());
            request.getSession(true).setAttribute("role", role);

            if(role.equals("manager"))				
                response.sendRedirect("managerHome.jsp");
            else if(role.equals("customer_rep"))				
                response.sendRedirect("customerRepresentativeHome.jsp");
            // the user is a Customer
            else
                response.sendRedirect("home.jsp");
        }
        // login wasn't successful for whatever reason then redirect to Home page and display error message
        else
            response.sendRedirect("index.jsp?status=false");
    }
}
