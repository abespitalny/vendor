package resources;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import model.Employee;

/**
 * Servlet implementation class GetHighestRevenueController
 */
public class GetHighestRevenueController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHighestRevenueController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.getHighestRevenueEmployee();

        request.setAttribute("employee", employee);
        RequestDispatcher rd = request.getRequestDispatcher("showHighestRevenueEmployee.jsp");
        rd.forward(request, response);
    }
}
