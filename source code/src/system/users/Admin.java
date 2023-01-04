package system.users;

public class Admin extends User {

    public Admin(String adminName, String email, String password)
    {
        this.setAdminName(adminName);
        this.setEmail(email);
        this.setPassword(password);

    }

    public void setAdminName(String customerName) {
        this.name = customerName;
    }
    public String getAdminName() {
        return name;
    }


}

