public class SaleLineItem {
    int lineNum;
    int orderId;
    int itemId;
    int quantity;
    double amount;
    public SaleLineItem(int lineNum, int orderId, int itemId, int quantity, double amount) {
        this.lineNum = lineNum;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.amount = amount;
    }
    public int getLineNum() {
        return lineNum;
    }
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
