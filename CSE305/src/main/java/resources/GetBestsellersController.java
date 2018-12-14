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
 * Servlet implementation class GetBestsellersController
 */
public class GetBestsellersController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBestsellersController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.getBestsellerItems();

        request.setAttribute("items", items);
        RequestDispatcher rd = request.getRequestDispatcher("showItems.jsp");
        rd.forward(request, response);
    }
}
