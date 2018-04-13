package servlets;

import common.Book;
import common.DBService;
import common.Global;
import dao.BookDao;
import dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProductPageServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int publishId = -1;

        DBService.createСonnection();

        if (request.getParameter("publish") != null) {
            publishId = Integer.parseInt(request.getParameter("publish"));
        }
        // Получение списка книг без фильтров, но по издатествам если выбрано
        ArrayList<Book> books = BookDao.getBooks(Global.NONE, 0, 999999, 1970, 2050, publishId);
        request.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int minPrice = 0;
        int maxPrice = 99999;
        int minYear = 1970;
        int maxYear = 2018;

        if (request.getParameter("type") != null && request.getParameter("type").equals("add")) {
            String productId = request.getParameter("productId");
            String login = request.getSession(false).getAttribute("login").toString();
            OrderDao.addBookToCart(login, Integer.parseInt(productId));
        } else {
            if (request.getParameter("minPrice") != null && !request.getParameter("minPrice").equals("")) {
                minPrice = Integer.parseInt(request.getParameter("minPrice"));
            }
            if (request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").equals("")) {
                maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            }
            if (request.getParameter("minYear") != null && !request.getParameter("minYear").equals("")) {
                minYear = Integer.parseInt(request.getParameter("minYear"));
            }
            if (request.getParameter("maxYear") != null && !request.getParameter("maxYear").equals("")) {
                maxYear = Integer.parseInt(request.getParameter("maxYear"));
            }
        }
        // Получение списка книг по фильтрам, кроме издательства
        ArrayList<Book> books = BookDao.getBooks(Global.NONE, minPrice, maxPrice, minYear, maxYear, -1);
        request.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
    }
}
