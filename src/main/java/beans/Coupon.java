package beans;

import java.sql.Date;

public class Coupon {
    private int id;
    private int companyID;
    private int categoryID;
    private String title;
    private String description;
    private Date starDate;
    private Date endDate;
    private int amount;
    private double price;

    private String image;

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public Coupon(int companyID, int categoryID, String title, String description, Date starDate, Date endDate, int amount, double price, String image) {
        this.companyID = companyID;
        this.categoryID = categoryID;
        this.title = title;
        this.description = description;
        this.starDate = starDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(int id, int companyID, int categoryID, String title, String description, Date starDate, Date endDate, int amount, double price, String image) {
        this(companyID, categoryID, title, description, starDate, endDate, amount, price, image);
        this.id = id;

    }


    public int getId() { return id; }

    public int getCompanyID() {
        return companyID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStarDate() {
        return starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", categoryIDyID=" + categoryID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", starDate=" + starDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
