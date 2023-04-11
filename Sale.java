import java.util.Date;
public class Sale {
    int orderId;
    int customerId;
    Date date;
    boolean status;
    public Sale(int orderId, int customerId, Date date, boolean status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.status = status;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
