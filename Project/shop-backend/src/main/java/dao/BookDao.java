package dao;

import common.Book;
import common.DBService;
import common.Global;
import common.Publishing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class BookDao {

    public static Book getBook(Integer id) {
        String name ="";
        Date year = new Date();
        int price = 0;
        int publish = 0;
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT name, year, price, quantity, publish FROM book " +
                            " WHERE id = ?;"
            );
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                name = res.getString("name");
                price = res.getInt("price");
                year = res.getDate("year");
                publish = res.getInt("publish");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new Book(id, name, year, price, publish);
    }

    public static ArrayList<Book> getBooks(String bookIds, int minPriceFilter, int maxPriceFilter, String minYearFilter, String maxYearFilter, String nameFilter) {
        ArrayList<Book> books = new ArrayList<>();
        int id;
        String query;
        String name;
        Date year;
        int price;
        int publish;
        try {
            query = "SELECT id, name, year, price, publish FROM book";

            query += " WHERE price BETWEEN " + minPriceFilter + " AND " + maxPriceFilter;

            if (!bookIds.equals(Global.NONE)) {
                query += " AND " + "id IN (" + bookIds + ")";
            }
            if (!minYearFilter.equals(Global.NONE) && !maxYearFilter.equals(Global.NONE)) {
                query += " AND " + "year BETWEEN " + minYearFilter + " AND " + maxYearFilter;
            }
            if (!nameFilter.equals(Global.NONE)) {
                query += " AND " + "name LIKE '%" + nameFilter + "%'";
            }

            ResultSet res = DBService.getConnection().createStatement().executeQuery(query);
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                price = res.getInt("price");
                year = res.getDate("year");
                publish = res.getInt("publish");
                books.add(new Book(id, name, year, price, publish));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return books;
    }

    public static Publishing getPublishingByBookId(int bookId) {
        int id = 0;
        String name = "";
        String address = "";
        try {
            PreparedStatement statement = DBService.getConnection().prepareStatement(
                    "SELECT p.id, p.name, p.address FROM publishing p, book b" +
                   " WHERE b.publish = p.id" +
                   " AND b.id = ?;"
            );
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                address = res.getString("address");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new Publishing(id, name, address);
    }
}
