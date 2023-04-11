public class Customer {
    int id;
    String name;
    String address;
    int phone;
    String email;
    double amountPayable;
    double salesLimit;
    public Customer(int id, String name, String address, int phone, String email, double amountPayable,
            double salesLimit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.amountPayable = amountPayable;
        this.salesLimit = salesLimit;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getAmountPayable() {
        return amountPayable;
    }
    public void setAmountPayable(double amountPayable) {
        this.amountPayable = amountPayable;
    }
    public double getSalesLimit() {
        return salesLimit;
    }
    public void setSalesLimit(double salesLimit) {
        this.salesLimit = salesLimit;
    }
    public double getBalance() {
        return salesLimit - amountPayable;
    }
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
                + email + ", amountPayable=" + amountPayable + ", salesLimit=" + salesLimit + "]";
    }
}
