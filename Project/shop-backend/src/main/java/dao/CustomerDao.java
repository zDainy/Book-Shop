package dao;

import common.Customer;
import common.DBService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao {

    public static boolean isAuthenticationSuccess(String login, String password) {
        int count = 0;
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT COUNT(*) FROM customer" +
                            " WHERE login = ?" +
                            " AND password = ?;"
            );
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                count = res.getInt("count");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return count == 1;
    }

    public static int getCustomerId(String login) {
        int id = 0;
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT id FROM customer" +
                            " WHERE login = ?;"
            );
            statement.setString(1, login);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                id = res.getInt("id");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return id;
    }

    public static boolean isCustomerExist(String login) {
        int count = 0;
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT COUNT(*) FROM customer" +
                            " WHERE login = ?;"
            );
            statement.setString(1, login);
            ResultSet searchRes = statement.executeQuery();
            if (searchRes.next()) {
                count = searchRes.getInt("count");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return count == 1;
    }

    public static void createCustomer(String login, String password) {
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "INSERT INTO customer (login, password)" +
                            " VALUES (?, ?);"
            );
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeQuery();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
