package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import java.math.BigDecimal;
import model.Employee;

/**
 * Servlet implementation class AddCustomerController
 */
public class AddEmployeeController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeController() {
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
        String zipCode = request.getParameter("zipCode");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String ssn = request.getParameter("ssn");
        BigDecimal hourlyRate = null;
        try {
            hourlyRate = BigDecimal.valueOf(Double.parseDouble(request.getParameter("hourlyRate")));
        } catch (Exception e) {}

        Employee employee = new Employee(username, password, firstName, lastName, address, city, state, zipCode, telephone,
            email, ssn, hourlyRate);

        EmployeeDao employeeDao = new EmployeeDao();
        String status = employeeDao.addEmployee(employee);
        if(status.equals("success"))
            response.sendRedirect("managerHome.jsp?status=addEmployeeSuccess");
        else
            response.sendRedirect("addEmployee.jsp?status=error");
    }
}
