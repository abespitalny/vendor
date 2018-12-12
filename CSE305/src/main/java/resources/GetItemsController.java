package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import model.Item;

/**
 * Servlet implementation class GetItemsController
 */
public class GetItemsController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemsController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.getItems();

        request.setAttribute("items", items);
        RequestDispatcher rd = request.getRequestDispatcher("showItems.jsp");
        rd.forward(request, response);
    }
}
