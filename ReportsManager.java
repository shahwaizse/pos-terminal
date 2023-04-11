import javax.swing.*;
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
                "Name: " + customer.getName() +
                "Address: " + customer.getAddress() +
                "Phone: " + customer.getPhone() +
                "Email: " + customer.getEmail() +
                "Balance: " + customer.getBalance()
                );
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Customer not found");
    }
    public void salesReport() {}
    public void outstandingSales() {}
}
