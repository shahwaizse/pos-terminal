import java.util.Date;
public class Item {
    int id;
    String desc;
    double price;
    int quantity;
    Date date;
    public Item(int id, String desc, double price, int quantity, Date date) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Item [id=" + id + ", desc=" + desc + ", price=" + price + ", quantity=" + quantity + ", date=" + date
                + "]";
    }
}
