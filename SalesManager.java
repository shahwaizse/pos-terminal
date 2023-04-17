import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class SalesManager {
    int id;
    int receiptNum;
    ArrayList<Sale> sales;
    ArrayList<Receipt> receipts;
    ArrayList<SaleLineItem> saleLineItems;
    ItemManager items;
    CustomerManager customers;
    public SalesManager(ItemManager items, CustomerManager customers) {
        sales = new ArrayList<Sale>();
        receipts = new ArrayList<Receipt>();
        saleLineItems = new ArrayList<SaleLineItem>();
        this.items = items;
        this.customers = customers;
    }
    public void setItems(ItemManager items) {
        this.items = items;
    }
    public void setCustomers(CustomerManager customers) {
        this.customers = customers;
    }
    public void saveSales() {
        try {
            FileWriter fw = new FileWriter("Sale.txt");
            PrintWriter pw = new PrintWriter(fw);
            for(Sale sale : sales) {
                pw.println(sale.getOrderId() + "," + sale.getCustomerId() + "," + sale.getDate() + "," + sale.isStatus());
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadSales() {
        try {
            FileReader fr = new FileReader("Sale.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int orderId = Integer.parseInt(data[0]);
                int customerId = Integer.parseInt(data[1]);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date = sdf.parse(data[2]);
                boolean status = Boolean.parseBoolean(data[3]);
                Sale sale = new Sale(orderId, customerId, date, status);
                sales.add(sale);
            }
            br.close();
            fr.close();
            id = sales.size() + 1;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void saveSaleLineItems() {
        try {
            FileWriter fw = new FileWriter("SaleLineItem.txt");
            PrintWriter pw = new PrintWriter(fw);
            for(SaleLineItem saleLineItem : saleLineItems) {
                pw.println(saleLineItem.getLineNum() + "," + saleLineItem.getOrderId() + "," + saleLineItem.getItemId() + "," + saleLineItem.getQuantity() + "," + saleLineItem.getAmount());
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadSaleLineItems() {
        try {
            FileReader fr = new FileReader("SaleLineItem.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int lineNum = Integer.parseInt(data[0]);
                int orderId = Integer.parseInt(data[1]);
                int itemId = Integer.parseInt(data[2]);
                int quantity = Integer.parseInt(data[3]);
                double amount = Double.parseDouble(data[4]);
                SaleLineItem saleLineItem = new SaleLineItem(lineNum, orderId, itemId, quantity, amount);
                saleLineItems.add(saleLineItem);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void saveReceipts() {
        try {
            FileWriter fw = new FileWriter("Receipt.txt");
            PrintWriter pw = new PrintWriter(fw);
            for(Receipt receipt : receipts) {
                pw.println(receipt.getReceiptNum() + "," + receipt.getDate() + "," + receipt.getOrderNum() + "," + receipt.getAmount());
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadReceipts() {
        try {
            FileReader fr = new FileReader("Receipt.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int receiptNum = Integer.parseInt(data[0]);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date = sdf.parse(data[1]);
                int orderNum = Integer.parseInt(data[2]);
                double amount = Double.parseDouble(data[3]);
                Receipt receipt = new Receipt(receiptNum, date, orderNum, amount);
                receipts.add(receipt);
            }
            br.close();
            fr.close();
            receiptNum = receipts.size() + 1;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void makeNewSale() {
        HashMap<Integer, Integer> quantities = new HashMap<Integer, Integer>();
        int lineNum = 1;
        double subtotal = 0;
        int customerId;
        while(true){
            try {
                customerId = Integer.parseInt(JOptionPane.showInputDialog(
                new Date() + "\n" +    
                "Enter customer id"
                ));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid customer id");
            }
        }
        Customer currentCustomer = customers.getCustomer(customerId);
        if (currentCustomer == null) {
            JOptionPane.showMessageDialog(null, "Customer not found");
            return;
        }
        int itemId;
        while(true){
            try {
                itemId = Integer.parseInt(JOptionPane.showInputDialog("Enter item id"));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid item id");
            }
        }
        Item currentItem = items.getItem(itemId);
        if (currentItem == null) {
            JOptionPane.showMessageDialog(null, "Item not found");
        }
        else if(items.getItem(itemId).getQuantity() == 0) {
            JOptionPane.showMessageDialog(null, "Item out of stock");
        }
        else {
            int quantity;
            while(true) {
                quantity = Integer.parseInt(JOptionPane.showInputDialog(
                "Description: " + currentItem.getDesc() + "\n" +
                "Price: " + currentItem.getPrice() + "\n" +
                "Items in stock: : " + items.getItem(itemId).getQuantity() + "\n" +
                "Enter quantity: "
                ));
                if(quantity == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity");
                }
                if(quantity > items.getItem(itemId).getQuantity()) {
                    JOptionPane.showMessageDialog(null, "Not enough items in stock.");
                }
                else {
                    quantities.put(itemId, quantity);
                    for(int i = 0; i < items.items.size(); i++) {
                        if(items.items.get(i).getId() == itemId) {
                            items.items.get(i).setQuantity(currentItem.getQuantity() - quantity);
                        }
                    }
                    break;
                }
            }
            SaleLineItem saleLineItem = new SaleLineItem(lineNum, id, currentItem.getId(), quantity, currentItem.getPrice());
            saleLineItems.add(saleLineItem);
            subtotal += currentItem.getPrice() * quantity;
            lineNum++;
            JOptionPane.showMessageDialog(
                null,
                "Id: " + currentItem.getId() + "\n" +
                "Description: " + currentItem.getDesc() + "\n" +
                "Price: " + currentItem.getPrice() + "\n" +
                "Quantity: " + quantity + "\n" +
                "Subtotal: " + subtotal
            );
        }
        while(true){
            int choice;
            while(true) {
                try {
                    choice = Integer.parseInt(JOptionPane.showInputDialog(
                        "Press 1 to Enter New Item \n" +
                        "Press 2 to End Sale \n" +
                        "Press 3 to Remove an existing Item from the current sale \n" +
                        "Press 4 to Cancel Sale"
                    ));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid choice");
                }
            }
            if (choice == 1) {
                while(true){
                    try {
                        itemId = Integer.parseInt(JOptionPane.showInputDialog("Enter item id"));
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid item id");
                    }
                }
                currentItem = items.getItem(itemId);
                if (currentItem == null) {
                    JOptionPane.showMessageDialog(null, "Item not found");
                }
                else if(items.getItem(itemId).getQuantity() == 0) {
                    JOptionPane.showMessageDialog(null, "Item out of stock");
                }
                else if(quantities.get(itemId) != null && quantities.get(itemId) > 0) {
                    JOptionPane.showMessageDialog(null, "Item already in sale");
                }
                else {
                    int quantity;
                    while(true) {
                        quantity = Integer.parseInt(JOptionPane.showInputDialog(
                        "Description: " + currentItem.getDesc() + "\n" +
                        "Price: " + currentItem.getPrice() + "\n" +
                        "Items in stock: : " + items.getItem(itemId).getQuantity() + "\n" +
                        "Enter quantity: "
                        ));
                        if(quantity == 0) {
                            JOptionPane.showMessageDialog(null, "Invalid quantity");
                        }
                        if(quantity > items.getItem(itemId).getQuantity()) {
                            JOptionPane.showMessageDialog(null, "Not enough items in stock.");
                        }
                        else {
                            quantities.put(itemId, quantity);
                            for(int i = 0; i < items.items.size(); i++) {
                                if(items.items.get(i).getId() == itemId) {
                                    items.items.get(i).setQuantity(currentItem.getQuantity() - quantity);
                                }
                            }
                            break;
                        }
                    }
                    SaleLineItem saleLineItem = new SaleLineItem(lineNum, id, currentItem.getId(), quantity, currentItem.getPrice());
                    saleLineItems.add(saleLineItem);
                    subtotal += currentItem.getPrice() * quantity;
                    lineNum++;
                    JOptionPane.showMessageDialog(
                        null,
                        "Id: " + currentItem.getId() + "\n" +
                        "Description: " + currentItem.getDesc() + "\n" +
                        "Price: " + currentItem.getPrice() + "\n" +
                        "Quantity: " + quantity + "\n" +
                        "Subtotal: " + subtotal
                    );
                }
            }
            else if (choice == 2) {
                if(subtotal > customers.getCustomer(customerId).getBalance()) {
                    JOptionPane.showMessageDialog(null, "Customer does not have enough balance, please try removing some items.");
                }
                else {
                    for(int i = 0; i < customers.customers.size(); i++) {
                        if(customers.customers.get(i).getId() == customerId) {
                            customers.customers.get(i).setAmountPayable(customers.customers.get(i).getAmountPayable() + subtotal);
                        }
                    }
                    Sale sale = new Sale(id, customerId, new Date(), true);
                    sales.add(sale);
                    JOptionPane.showMessageDialog(
                        null,
                        "Sale id: " + id + "\n" +
                        "Customer id: " + sale.getCustomerId() + "\n" +
                        "Date: " + sale.getDate() + "\n" +
                        "Subtotal: " + subtotal + "\n"
                    );
                    id++;
                    JTable table = new JTable(saleLineItems.size() + 1, 4);
                    table.getColumnModel().getColumn(0).setHeaderValue("Item ID");
                    table.getColumnModel().getColumn(1).setHeaderValue("Description");
                    table.getColumnModel().getColumn(2).setHeaderValue("Quantity");
                    table.getColumnModel().getColumn(3).setHeaderValue("Amount");
                    for (int i = 0; i < saleLineItems.size(); i++) {
                        if(saleLineItems.get(i).getOrderId() == id - 1) {
                            table.setValueAt(saleLineItems.get(i).getItemId(), i, 0);
                            table.setValueAt(items.getItem(saleLineItems.get(i).getItemId()).getDesc(), i, 1);
                            table.setValueAt(saleLineItems.get(i).getQuantity(), i, 2);
                            table.setValueAt(saleLineItems.get(i).getQuantity() * items.getItem(saleLineItems.get(i).getItemId()).getPrice(), i, 3);
                        }
                    }
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                    break;
                }
            }
            else if (choice == 3) {
                while(true){
                    try {
                        itemId = Integer.parseInt(JOptionPane.showInputDialog("Enter item id"));
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid item id");
                    }
                }
                for (SaleLineItem sli : saleLineItems) {
                    if (sli.getItemId() == itemId && sli.getOrderId() == id) {
                        subtotal -= sli.getQuantity() * items.getItem(sli.getItemId()).getPrice();
                        JOptionPane.showMessageDialog(
                            null,
                            "Id: " + sli.getItemId() + "\n" +
                            "Description: " + items.getItem(sli.getItemId()).getDesc() + "\n" +
                            "Price: " + items.getItem(sli.getItemId()).getPrice() + "\n" +
                            "Quantity: " + sli.getQuantity() + "\n" +
                            "Subtotal: " + subtotal
                        );
                        for(int i = 0; i < items.items.size(); i++) {
                            if(items.items.get(i).getId() == itemId) {
                                items.items.get(i).setQuantity(currentItem.getQuantity() + quantities.get(itemId));
                            }
                        }
                        quantities.remove(itemId);
                        if(saleLineItems.remove(sli)) {
                            JOptionPane.showMessageDialog(null, "Item removed from sale");
                        }
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "Item not found in sale");
            }
            else if (choice == 4) {
                quantities.forEach((k, v) -> {
                    for(int i = 0; i < items.items.size(); i++) {
                        if(items.items.get(i).getId() == k) {
                            items.items.get(i).setQuantity(items.items.get(i).getQuantity() + v);
                        }
                    }
                });
                break;
            }
        }
    }
    public void makePayment() {
        int salesId;
        while(true){
            try {
                salesId = Integer.parseInt(JOptionPane.showInputDialog("Enter sales id"));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid sales id");
            }
        }
        boolean check = false;
        for(int i = 0; i < sales.size(); i++) {
            if(sales.get(i).getOrderId() == salesId) {
                if(sales.get(i).isStatus() == false) {
                    JOptionPane.showMessageDialog(null, "Sale already paid");
                    return;
                }
                check = true;
                break;
            }
        }
        if(!check) {
            JOptionPane.showMessageDialog(null, "Sale not found");
            return;
        }
        String customerName = "";
        for(int i = 0; i < customers.customers.size(); i++) {
            for(int j = 0; j < sales.size(); j++) {
                if(sales.get(j).getOrderId() == salesId && customers.customers.get(i).getId() == sales.get(j).getCustomerId()) {
                    customerName = customers.customers.get(i).getName();
                }
            }
        }
        double totalSalesAmount = 0;
        for(int i = 0; i < saleLineItems.size(); i++) {
            if(saleLineItems.get(i).getOrderId() == salesId) {
                totalSalesAmount += saleLineItems.get(i).getAmount() * saleLineItems.get(i).getQuantity();
            }
        }
        double prevPaid = 0;
        for(int i = 0; i < receipts.size(); i++) {
            if(receipts.get(i).getOrderNum() == salesId) {
                prevPaid += receipts.get(i).getAmount();
            }
        }
        double remainingAmount = totalSalesAmount - prevPaid;
        double amountPaid;
        while(true){
            try {
                amountPaid = Double.parseDouble(JOptionPane.showInputDialog(
                "Customer name: " + customerName + "\n" +
                "Total sales amount: " + totalSalesAmount + "\n" +
                "Previous paid: " + prevPaid + "\n" +
                "Remaining amount: " + remainingAmount + "\n" +
                "Enter amount paid: "
                ));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount");
            }
        }
        for(int i = 0; i < customers.customers.size(); i++) {
            for(int j = 0; j < sales.size(); j++) {
                if(sales.get(j).getOrderId() == salesId && customers.customers.get(i).getId() == sales.get(j).getCustomerId()) {
                    customers.customers.get(i).setAmountPayable(customers.customers.get(i).getAmountPayable() - amountPaid);
                }
            }
        }
        if(amountPaid >= remainingAmount) {
            for(int i = 0; i < sales.size(); i++) {
                if(sales.get(i).getOrderId() == salesId) {
                    sales.get(i).setStatus(false);
                }
            }
        }
        Receipt receipt = new Receipt(receiptNum, new Date(), salesId, amountPaid);
        receipts.add(receipt);
        receiptNum++;
    }
}
