
package system.users;

public class Customer extends User {
    private double wallet;
    public Customer(){}
    public Customer(String customerName, String email, String password)
    {
        this.setCustomerName(customerName);
        this.setEmail(email);
        this.setPassword(password);
        this.setWallet(0.0);

    }

    public void setCustomerName(String customerName) {
        this.name = customerName;
    }
    public String getCustomerName() {
        return name;
    }
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    public double getWallet() {
        return wallet;
    }
}
