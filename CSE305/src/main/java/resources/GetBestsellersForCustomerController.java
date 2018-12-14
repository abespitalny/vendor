package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import model.Item;

/**
 * Servlet implementation class GetBestsellersForCustomerController
 */
public class GetBestsellersForCustomerController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBestsellersForCustomerController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = (String)request.getSession(false).getAttribute("customerID");
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.getBestsellersForCustomer(customerID);

        request.setAttribute("items", items);
        RequestDispatcher rd = request.getRequestDispatcher("showBestsellersForCustomer.jsp");
        rd.forward(request, response);
    }
}
