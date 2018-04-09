package common;

import java.util.Date;

public class Order {
    private int id;
    private int customerId;
    private Date dateCreated;

    public Order(int id, int customerId, Date dateCreated) {
        this.id = id;
        this.customerId = customerId;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
