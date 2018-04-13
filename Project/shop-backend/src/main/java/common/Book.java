package common;

import dao.BookDao;

public class Book {
    private int id;
    private String name;
    private int year;
    private int price;
    private int publish;
    private String imgSrc;
    private String publishName;

    public Book(int id, String name, int year, int price, int publish, String imgSrc) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
        this.publish = publish;
        this.imgSrc = imgSrc;
        publishName = getPublishingName();
    }

    private String getPublishingName() {
        Publishing p = BookDao.getPublishingByBookId(this.id);
        return p.getName();
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }
}
