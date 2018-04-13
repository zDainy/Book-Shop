package dao;

import common.Book;
import common.DBService;
import common.Global;
import common.Publishing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDao {
    // Функция получения списка книг с фильтрами
    public static ArrayList<Book> getBooks(String bookIds, int minPriceFilter, int maxPriceFilter, int minYearFilter, int maxYearFilter, int publishId) {
        ArrayList<Book> books = new ArrayList<>();
        int id;
        String query;
        String name;
        int year;
        int price;
        int publish;
        String imgSrc;
        try {
            query = "SELECT id, name, year, price, publish, img_src FROM book";
            // цена между minPriceFilter и maxPriceFilter
            query += " WHERE price BETWEEN " + minPriceFilter + " AND " + maxPriceFilter;
            // цена между minYearFilter и maxYearFilter
            query += " AND " + "year BETWEEN " + minYearFilter + " AND " + maxYearFilter;
            // id книг
            if (!bookIds.equals(Global.NONE)) {
                query += " AND " + "id IN (" + bookIds + ")";
            }
            // Издательство
            if (publishId != -1) {
                query += " AND " + "publish = " + publishId + "";
            }

            ResultSet res = DBService.getConnection().createStatement().executeQuery(query);
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                price = res.getInt("price");
                year = res.getInt("year");
                publish = res.getInt("publish");
                imgSrc = res.getString("img_src");
                books.add(new Book(id, name, year, price, publish, imgSrc));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return books;
    }
    // Получение издатество по id книги
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
            statement.setInt(1, bookId);
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
