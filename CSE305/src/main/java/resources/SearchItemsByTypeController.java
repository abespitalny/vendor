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
 * Servlet implementation class SearchItemsByTypeController
 */
public class SearchItemsByTypeController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItemsByTypeController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemType = request.getParameter("itemType");
        ItemDao itemDao = new ItemDao();
        List<Object> data = itemDao.getItemsByType(itemType);

        request.setAttribute("auctions", data.get(0));
        request.setAttribute("items", data.get(1));
        RequestDispatcher rd = request.getRequestDispatcher("showItemsForCustomer.jsp");
        rd.forward(request, response);
    }
}
