import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class ItemManager {
    ArrayList<Item> items;
    int id;
    public ItemManager() {
        items = new ArrayList<Item>();
    }
    public void saveItems() {
        try {
            FileWriter fw = new FileWriter("Item.txt");
            PrintWriter pw = new PrintWriter(fw);
            for(Item item : items) {
                pw.println(item.getId() + "," + item.getDesc() + "," + item.getPrice() + "," + item.getQuantity() + "," + item.getDate());
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadItems() {
        try {
            FileReader fr = new FileReader("Item.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String desc = data[1];
                double price = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date = sdf.parse(data[4]);
                Item item = new Item(id, desc, price, quantity, date);
                items.add(item);
            }
            br.close();
            fr.close();
            id = items.size() + 1;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addItem() {
        JOptionPane.showMessageDialog(null, "Item ID: " + id);
        String desc = JOptionPane.showInputDialog("Enter item description");
        double price;
        try {
            price = Double.parseDouble(JOptionPane.showInputDialog("Enter item price"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price. Price set to 0");
            price = 0;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter item quantity"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity. Quantity set to 0");
            quantity = 0;
        }
        Date date = new Date();
        Item item = new Item(id, desc, price, quantity, date);
        items.add(item);
        id++;
        JOptionPane.showMessageDialog(null, "Item added");
    }
    public void modifyItem() {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter item id"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid id");
            return;
        }
        for (Item item : items) {
            if (item.getId() == id) {
                JOptionPane.showMessageDialog(null, item.toString());
                String desc = JOptionPane.showInputDialog("Enter item description");
                if (!desc.equals("")) {
                    item.setDesc(desc);
                }
                double price;
                try {
                    price = Double.parseDouble(JOptionPane.showInputDialog("Enter item price"));
                } catch (NumberFormatException e) {
                    price = 0;
                }
                if (price != 0) {
                    item.setPrice(price);
                }
                int quantity;
                try {
                    quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter item quantity"));
                } catch (NumberFormatException e) {
                    quantity = 0;
                }
                if (quantity != 0) {
                    item.setQuantity(quantity);
                }
                Date date = new Date();
                item.setDate(date);
                JOptionPane.showMessageDialog(null, "Item modified");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Item not found");
    }
    public void findItem() {
        JOptionPane.showMessageDialog(null, "Leave fields blank to return to menu");
        boolean found = false;
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter item id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        String desc = JOptionPane.showInputDialog("Enter item description");
        double price;
        try {
            price = Double.parseDouble(JOptionPane.showInputDialog("Enter item price"));
        } catch (NumberFormatException e) {
            price = 0;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter item quantity"));
        } catch (NumberFormatException e) {
            quantity = 0;
        }
        if(id == 0 && desc.equals("") && price == 0 && quantity == 0) {
            return;
        }
        for (Item i : items) {
            if (i.getId() == id || i.getDesc().equals(desc) || i.getPrice() == price || i.getQuantity() == quantity) {
                JOptionPane.showMessageDialog(null, 
                "Item Id: " + i.getId() + "\n" + 
                "Description: " + i.getDesc() + "\n" + 
                "Price: " + i.getPrice() + "\n" + 
                "Quantity: " + i.getQuantity() + "\n" + 
                "Date: " + i.getDate()
                );
                found = true;
            }
        }
        if(!found) {
            JOptionPane.showMessageDialog(null, "Item not found");
        }
    }
    public Item getItem(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public void removeItem() {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter item id"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid id");
            return;
        }
        //TODO: check if item exists in active sale before removing
        for (Item item : items) {
            if (item.getId() == id) {
                items.remove(item);
                JOptionPane.showMessageDialog(null, "Item removed");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Item not found");
    }
}

// implement the check in removeitem to see if the item exists in active sale before removing.