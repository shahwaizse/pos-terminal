import javax.swing.*;
import java.util.Date;
public class ReportsManager {
    SalesManager sm;
    public ReportsManager(SalesManager sm) {
        this.sm = sm;
    }
    public void setSm(SalesManager sm) {
        this.sm = sm;
    }
    public void stockInHand() {
        JTable table = new JTable(sm.items.items.size() + 1, 4);
        table.getColumnModel().getColumn(0).setHeaderValue("Item ID");
        table.getColumnModel().getColumn(1).setHeaderValue("Description");
        table.getColumnModel().getColumn(2).setHeaderValue("Price");
        table.getColumnModel().getColumn(3).setHeaderValue("Quantity");
        int i = 0;
        for (Item item : sm.items.items) {
            table.setValueAt(item.getId(), i, 0);
            table.setValueAt(item.getDesc(), i, 1);
            table.setValueAt(item.getPrice(), i, 2);
            table.setValueAt(item.getQuantity(), i, 3);
            i++;
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }
    public void customerInfo() {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid id");
            return;
        }
        for (Customer customer : sm.customers.customers) {
            if (customer.getId() == id) {
                JOptionPane.showMessageDialog(null, 
                "Name: " + customer.getName() + "\n" +
                "Address: " + customer.getAddress() + "\n" +
                "Phone: " + customer.getPhone() + "\n" +
                "Email: " + customer.getEmail() + "\n" +
                "Balance: " + customer.getBalance()
                );
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Customer not found");
    }
    public void salesReport() {
        Date startDate;
        Date endDate;
        String formatStart = JOptionPane.showInputDialog("Enter start date");
        startDate = new Date();
        String formatEnd = JOptionPane.showInputDialog("Enter end date");
        endDate = new Date();
        JOptionPane.showMessageDialog(null, 
        "Sales from " + startDate + " to " + endDate + ":\n"
        );
        //TODO: Check variables according to date and store them in table.
        JTable table = new JTable(sm.sales.size() + 1, 4);
        table.getColumnModel().getColumn(0).setHeaderValue("Item ID");
        table.getColumnModel().getColumn(1).setHeaderValue("Description");
        table.getColumnModel().getColumn(2).setHeaderValue("Quantity Sold"); 
        table.getColumnModel().getColumn(3).setHeaderValue("Amount");
        int i = 0;
        if(sm.saleLineItems.size() == 0) {
            JOptionPane.showMessageDialog(null, "No sales yet");
            return;
        }
        for (Item item : sm.items.items) {
            table.setValueAt(item.getId(), i, 0);
            table.setValueAt(item.getDesc(), i, 1);
            int quantity = 0;
            double amount = 0;
            for (SaleLineItem saleLineItem : sm.saleLineItems) {
                if (saleLineItem.getItemId() == item.getId()) {
                    quantity += saleLineItem.getQuantity();
                    amount += saleLineItem.getAmount();
                }
            }
            table.setValueAt(quantity, i, 2);
            table.setValueAt(amount, i, 3);
            i++;
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }
    public void outstandingSales() {
        Date startDate;
        Date endDate;
        String formatStart = JOptionPane.showInputDialog("Enter start date");
        startDate = new Date();
        String formatEnd = JOptionPane.showInputDialog("Enter end date");
        endDate = new Date();
        JOptionPane.showMessageDialog(null, 
        "Outstanding sales from " + startDate + " to " + endDate + ":\n"
        );
        //TODO: Take date input and check sales according to date.
        JTable table = new JTable(sm.sales.size() + 1, 4);
        table.getColumnModel().getColumn(0).setHeaderValue("Sale ID");
        table.getColumnModel().getColumn(1).setHeaderValue("Customer Name");
        table.getColumnModel().getColumn(2).setHeaderValue("Total Amount");
        table.getColumnModel().getColumn(3).setHeaderValue("Remaining Amount");
        int i = 0;
        for (Sale sale : sm.sales) {
            if (sale.isStatus()) {
                table.setValueAt(sale.getOrderId(), i, 0);
                for(int c = 0; c < sm.customers.customers.size(); c++) {
                    if(sm.customers.customers.get(c).getId() == sale.getCustomerId()) {
                        table.setValueAt(sm.customers.customers.get(c).getName(), i, 1);
                    }
                }
                double totalSalesAmount = 0;
                for (int a = 0; a < sm.saleLineItems.size(); a++) {
                    if(sm.saleLineItems.get(a).getOrderId() == sale.getOrderId()) {
                        totalSalesAmount += sm.saleLineItems.get(a).getQuantity() * sm.saleLineItems.get(a).getAmount();
                    }
                }
                double prevPaid = 0;
                for(int a = 0; a < sm.receipts.size(); a++) {
                    if (sm.receipts.get(a).getOrderNum() == sale.getOrderId()) {
                        prevPaid += sm.receipts.get(a).getAmount();
                    }
                }
                double remainingAmount = totalSalesAmount - prevPaid;
                table.setValueAt(totalSalesAmount, i, 2);
                table.setValueAt(remainingAmount, i, 3);
                i++;
            }
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }
}
