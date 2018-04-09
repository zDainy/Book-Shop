package common;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private Date year;
    private int price;
    private int publish;

    public Book(int id, String name, Date year, int price, int publish) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
        this.publish = publish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPublish() {
        return publish;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }
}
