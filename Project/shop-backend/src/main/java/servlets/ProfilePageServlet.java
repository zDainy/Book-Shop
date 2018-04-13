package servlets;

import common.Book;
import common.Customer;
import common.DBService;
import common.Global;
import dao.BookDao;
import dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ProfilePageServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBService.createСonnection();

        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        // Вход
        if (type.equals("logIn") && CustomerDao.isAuthenticationSuccess(login, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            ArrayList<Book> books = BookDao.getBooks(Global.NONE, 0, 999999,1970,2050, -1);
            request.setAttribute("books", books);
            getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
        }
        // Регистрация
        else if (type.equals("registration")) {
            if (CustomerDao.isCustomerExist(login)) {
                // ошибка
            } else {
                CustomerDao.createCustomer(login, password);
                // успех
            }
        }
        processRequest(request, response);
    }
}
