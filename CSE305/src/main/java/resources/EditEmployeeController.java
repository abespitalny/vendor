package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import javax.servlet.RequestDispatcher;
import model.Employee;

/**
 * Servlet implementation class EditEmployeeController
 */
public class EditEmployeeController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeController() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");
        EmployeeDao employeeDao = new EmployeeDao();
        Employee editEmployee = employeeDao.getEmployee(employeeID);
        
        // old employeeID
        request.getSession(true).setAttribute("oldEmployeeID", employeeID);
        request.setAttribute("editEmployee", editEmployee);
        RequestDispatcher rd = request.getRequestDispatcher("editEmployee.jsp");
        rd.forward(request, response);
    }
}
