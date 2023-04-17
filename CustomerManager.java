import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
public class CustomerManager {
    ArrayList<Customer> customers;
    int id;
    public CustomerManager() {
        customers = new ArrayList<Customer>();
    }
    public void saveCustomers() {
        try {
            FileWriter fw = new FileWriter("Customer.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Customer customer : customers) {
                pw.println(customer.getId() + "," + customer.getName() + "," + customer.getAddress() + "," + customer.getPhone() + "," + customer.getEmail() + "," + customer.getAmountPayable() + "," + customer.getSalesLimit());
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadCustomers() {
        try {
            FileReader fr = new FileReader("Customer.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];
                int phone = Integer.parseInt(data[3]);
                String email = data[4];
                double amountPayable = Double.parseDouble(data[5]);
                double salesLimit = Double.parseDouble(data[6]);
                Customer customer = new Customer(id, name, address, phone, email, amountPayable, salesLimit);
                customers.add(customer);
            }
            br.close();
            fr.close();
            id = customers.size() + 1;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addCustomer() {
        JOptionPane.showMessageDialog(null, "Customer ID: " + id);
        String name;
        while(true) {
            name = JOptionPane.showInputDialog("Enter customer name");
            if(name.equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid name");
            } else {
                break;
            }
        }
        String address = JOptionPane.showInputDialog("Enter customer address");
        int phone;
        try {
            phone = Integer.parseInt(JOptionPane.showInputDialog("Enter customer phone"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid phone. Phone set to 0");
            phone = 0;
        }
        String email = JOptionPane.showInputDialog("Enter customer email");
        double amountPayable = 0;
        double salesLimit;
        while(true) {
            try {
                salesLimit = Double.parseDouble(JOptionPane.showInputDialog("Enter customer sales limit"));
                if(salesLimit <= 0)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid sales limit");
            }
        }
        Customer customer = new Customer(id, name, address, phone, email, amountPayable, salesLimit);
        customers.add(customer);
        id++;
        JOptionPane.showMessageDialog(null, "Customer added");
    }
    public void modifyCustomer() {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid id");
            return;
        }
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                JOptionPane.showMessageDialog(null, customer.toString());
                String name = JOptionPane.showInputDialog("Enter customer name");
                if (!name.equals("")) {
                    customer.setName(name);
                }
                String address = JOptionPane.showInputDialog("Enter customer address");
                if (!address.equals("")) {
                    customer.setAddress(address);
                }
                int phone;
                try {
                    phone = Integer.parseInt(JOptionPane.showInputDialog("Enter customer phone"));
                } catch (NumberFormatException e) {
                    phone = 0;
                }
                if (phone != 0) {
                    customer.setPhone(phone);
                }
                String email = JOptionPane.showInputDialog("Enter customer email");
                if (!email.equals("")) {
                    customer.setEmail(email);
                }
                double salesLimit;
                try {
                    salesLimit = Double.parseDouble(JOptionPane.showInputDialog("Enter customer sales limit"));
                } catch (NumberFormatException e) {
                    salesLimit = 0;
                }
                if (salesLimit != 0) {
                    customer.setSalesLimit(salesLimit);
                }
                JOptionPane.showMessageDialog(null, "Customer modified");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Customer not found");
    }
    public void findCustomer() {
        JOptionPane.showMessageDialog(null, "Leave fields blank to return to menu");
        boolean found = false;
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        String name = JOptionPane.showInputDialog("Enter customer name");
        String address = JOptionPane.showInputDialog("Enter customer address");
        int phone;
        try {
            phone = Integer.parseInt(JOptionPane.showInputDialog("Enter customer phone"));
        } catch (NumberFormatException e) {
            phone = 0;
        }
        String email = JOptionPane.showInputDialog("Enter customer email");
        if(id == 0 && name.equals("") && address.equals("") && phone == 0 && email.equals("")) {
            return;
        }
        for (Customer customer : customers) {
            if(id == customer.getId() || name.equals(customer.getName()) || address.equals(customer.getAddress()) || phone == customer.getPhone() || email.equals(customer.getEmail())) {
                JOptionPane.showMessageDialog(null, 
                    "Customer ID: " + customer.getId() + "\n" +
                    " Customer Name: " + customer.getName() + "\n" +
                    " Customer Address: " + customer.getAddress() + "\n" +
                    " Customer Phone: " + customer.getPhone() + "\n" +
                    " Customer Email: " + customer.getEmail() + "\n" +
                    " Customer Amount Payable: " + customer.getAmountPayable() + "\n" +
                    " Customer Sales Limit: " + customer.getSalesLimit()
                    );
                found = true;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Customer not found");
        }
    }
    public Customer getCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    } 
    public void removeCustomer() {
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid id");
            return;
        }
        //TODO check if customer has any orders before removing
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                JOptionPane.showMessageDialog(null, "Customer removed");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Customer not found");
    }
}
