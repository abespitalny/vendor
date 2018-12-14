package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import model.Employee;

/**
 * Servlet implementation class GetEmployeesController
 */
public class GetEmployeesController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeesController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.getEmployees(); 

        request.setAttribute("employees", employees);
        RequestDispatcher rd = request.getRequestDispatcher("showEmployee.jsp");
        rd.forward(request, response);
    }
}
