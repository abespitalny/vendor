package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;

/**
 * Servlet implementation class SearchItemsByNameController
 */
public class SearchItemsByNameController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItemsByNameController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        ItemDao itemDao = new ItemDao();
        List<Object> data = itemDao.getItemsByName(itemName);

        request.setAttribute("auctions", data.get(0));
        request.setAttribute("items", data.get(1));
        RequestDispatcher rd = request.getRequestDispatcher("showItemsForCustomer.jsp");
        rd.forward(request, response);
    }
}
