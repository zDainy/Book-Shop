package dao;

import common.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class OrderDao {

    public static ArrayList<Book> getBooksInOrder(String login) {
        String bookIds = "";
        boolean isFirst = true;
        try {
            int custId = CustomerDao.getCustomerId(login);
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT book FROM product_order" +
                            " WHERE \"order\" = (SELECT id FROM \"order\" WHERE customer = ?);"

            );
            statement.setInt(1, custId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                bookIds += isFirst ? "" : ",";
                bookIds += res.getInt("book");
                isFirst = false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return BookDao.getBooks(bookIds, 0, 999999, Global.NONE, Global.NONE, Global.NONE);
    }

    public static ArrayList<Order> getOrders(int customerId) {
        ArrayList<Order> orders = new ArrayList<>();
        int orderId;
        Date dateCreated;
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT id, customer, date_created FROM \"order\""+
                            " WHERE customer = ?;"
            );
            statement.setInt(1, customerId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                orderId = res.getInt("id");
                dateCreated = res.getDate("date_created");
                orders.add(new Order(orderId, customerId, dateCreated));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return orders;
    }

    public static void clearShopCart(String login) {
        try {
            int custId = CustomerDao.getCustomerId(login);
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "DELETE FROM product_order WHERE id = (SELECT id FROM \"order\" WHERE customer = ?);"
            );
            statement.setInt(1, custId);
            statement.executeQuery();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void deleteProductFromCart(String login, int productId) {
        try {
            int custId = CustomerDao.getCustomerId(login);
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "DELETE FROM product_order WHERE book = ? AND id = (SELECT id FROM \"order\" WHERE customer = ?);"
            );
            statement.setInt(1, productId);
            statement.setInt(2, custId);
            statement.executeQuery();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void addBookToCart(String login, int productId) {
        int count = 0;
        int orderId = 0;
        try {
            int custId = CustomerDao.getCustomerId(login);
            PreparedStatement searchStatement = DBService.getConnection().prepareStatement(
                    "SELECT COUNT(*) FROM \"order\"" +
                            " WHERE customer = ?;"
            );
            searchStatement.setInt(1, custId);
            ResultSet searchRes = searchStatement.executeQuery();
            if (searchRes.next()) {
                count = searchRes.getInt("count");
            }
            if (count == 0) {
                PreparedStatement createCartStatement = DBService.getConnection().prepareStatement(
                        "INSERT INTO \"order\" (customer)" +
                                " VALUES (?);"
                );
                createCartStatement.setInt(1, custId);
                createCartStatement.executeQuery();
            }

            PreparedStatement getIdCartOrderStatement = DBService.getConnection().prepareStatement(
                    "SELECT id FROM \"order\"" +
                            " WHERE customer = ?"
            );
            getIdCartOrderStatement.setInt(1, custId);
            ResultSet cartOrderRes = getIdCartOrderStatement.executeQuery();
            if (cartOrderRes.next()) {
                orderId = cartOrderRes.getInt("id");
            }

            PreparedStatement addProductToOrder = DBService.getConnection().prepareStatement(
                    "INSERT INTO product_order (book, \"order\")" +
                            " VALUES (?, ?);"
            );
            addProductToOrder.setInt(1, productId);
            addProductToOrder.setInt(2, orderId);
            addProductToOrder.executeQuery();

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
