import java.util.Date;
public class Receipt {
    int receiptNum;
    Date date;
    int orderNum;
    double amount;
    public Receipt(int receiptNum, Date date, int orderNum, double amount) {
        this.receiptNum = receiptNum;
        this.date = date;
        this.orderNum = orderNum;
        this.amount = amount;
    }
    public int getReceiptNum() {
        return receiptNum;
    }
    public void setReceiptNum(int receiptNum) {
        this.receiptNum = receiptNum;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
