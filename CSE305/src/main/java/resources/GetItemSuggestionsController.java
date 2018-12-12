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
 * Servlet implementation class GetItemSuggestionsController
 */
public class GetItemSuggestionsController extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemSuggestionsController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sellerID = request.getParameter("customerID");
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.getItemSuggestions(sellerID);

        request.setAttribute("items", items);
        RequestDispatcher rd = request.getRequestDispatcher("showItemSuggestions.jsp");
        rd.forward(request, response);
    }
}
