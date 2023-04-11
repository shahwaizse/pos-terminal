import javax.swing.JOptionPane;
public class POS {
    ItemManager itemManager;
    CustomerManager customerManager;
    SalesManager salesManager;
    ReportsManager reportsManager;
    public POS() {
        itemManager = new ItemManager();
        customerManager = new CustomerManager();
        salesManager = new SalesManager(itemManager, customerManager);
        reportsManager = new ReportsManager(salesManager);
        loadPOS();
    }
    public void loadPOS() {
        itemManager.loadItems();
        customerManager.loadCustomers();
        salesManager.loadSales();
        salesManager.loadSaleLineItems();
        salesManager.loadReceipts();
    }
    public void savePOS() {
        itemManager.saveItems();
        customerManager.saveCustomers();
        salesManager.saveSales();
        salesManager.saveSaleLineItems();
        salesManager.saveReceipts();
    }
    public void itemMenu() {
        int choice = 0;
        while(choice != 5) {
            choice = Integer.parseInt(JOptionPane.showInputDialog("1. Add item \n2. Update item \n3. Find item \n4. Remove existing item \n5. Back"));
            switch (choice) {
                case 1:
                    itemManager.addItem();
                    break;
                case 2:
                    itemManager.modifyItem();
                    break;
                case 3:
                    itemManager.findItem();
                    break;
                case 4:
                    itemManager.removeItem();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }
    public void customerMenu() {
        int choice = 0;
        while(choice != 5) {
            choice = Integer.parseInt(JOptionPane.showInputDialog("1. Add new customer \n2. Update customer details \n3. Find customer \n4. Remove existing customer \n5. Back"));
            switch (choice) {
                case 1:
                    customerManager.addCustomer();
                    break;
                case 2:
                    customerManager.modifyCustomer();
                    break;
                case 3:
                    customerManager.findCustomer();
                    break;
                case 4:
                    customerManager.removeCustomer();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }
    public void reportsMenu() {
        int choice = 0;
        while(choice != 5) {
            choice = Integer.parseInt(JOptionPane.showInputDialog("1. Stock in hand \n2. Outstanding orders \n3. Customer balance \n4. Sales Receipts \n5. Back"));
            switch (choice) {
                case 1:
                    reportsManager.stockInHand();
                    break;
                case 2:
                    reportsManager.outstandingSales();
                    break;
                case 3:
                    reportsManager.customerInfo();
                    break;
                case 4:
                    reportsManager.salesReport();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }
    public void mainMenu() {
        int choice = 0;
        while(choice != 6) {
            choice = Integer.parseInt(JOptionPane.showInputDialog("1. Manage Items \n2. Manage Customers \n3. Make New Sale \n4. Make Payment \n5. Print Reports \n6. Exit"));
            switch (choice) {
                case 1:
                    itemMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    salesManager.setItems(itemManager);
                    salesManager.setCustomers(customerManager);
                    salesManager.makeNewSale();
                    break;
                case 4:
                    salesManager.setItems(itemManager);
                    salesManager.setCustomers(customerManager);
                    salesManager.makePayment();
                    break;
                case 5:
                    reportsManager.setSm(salesManager);
                    reportsMenu();
                    break;
                case 6:
                    savePOS();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }
}
