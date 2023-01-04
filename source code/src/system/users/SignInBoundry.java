package system.users;

import system.menus.AdminMenu;
import system.menus.CustomerMenu;

import java.util.Scanner;

public class SignInBoundry {
    static Customer currentCustomer = new Customer("habibayasser", "habibayasser@gmail.com", "123");
    Scanner sc = new Scanner(System.in);
    static Admin currentAdmin = new Admin("maimostafa", "maimostafa@gmail.com", "321");
    CustomerMenu customerMenu = new CustomerMenu();
    AdminMenu adminMenu = new AdminMenu();
    int userType;
    private String Email;
    private String Password;
    private String user;
    AuthenticationController controller = new AuthenticationController();

    public SignInBoundry(){};

    public SignInBoundry(String userName, String email, String password) {
        Email = email;
        Password = password;
        user = userName;

    }

    public SignInBoundry(String e, String p) {
        Email = e;
        Password = p;
    }

    void setEmail(String e) {
        Email = e;
    }

    void setPassword(String p) {
        Password = p;
    }

    String getEmail() {
        return Email;
    }

    String getPassword() {
        return Password;
    }

    void setUsername(String e) {
        user = e;
    }

    String getuser() {
        return user;
    }


    public boolean SignInAdmin() {
        return controller.SignInAdmin(Email, Password);
    }

    public boolean SignInUSer() {
        return controller.SignInUser(Email, Password);
    }

    public String SignUp() {
        return controller.SignUpUser(user, Email, Password);
    }

    public Customer getCustomer(String Email) {
        return controller.getCurrentCustomer(Email);
    }

    public Admin getAdmin(String Email) {
        return controller.getCurrentAdmin(Email);
    }

    public void Show() {
        //show signInboundry for the user or admin to choose if he is admin or user or want to sign up
        System.out.println("[1]: Admin");
        System.out.println("[2]: User");
        System.out.println("[3]: Exit");
        System.out.print("Enter your choice: ");
        userType = sc.nextInt();
        if(userType ==1) {
            System.out.print("Please Enter your email: ");
            Email = sc.next().toLowerCase();
            System.out.print("Please Enter your password: ");
            Password = sc.next();
            SignInBoundry s = new SignInBoundry(Email, Password);
            while (!s.SignInAdmin()) {
                System.out.println("Email or Password is not correct");
                System.out.println();
                System.out.print("Please Enter your email: ");
                Email = sc.next().toLowerCase();
                System.out.print("Please Enter your password: ");
                Password = sc.next();
                System.out.println();
                s = new SignInBoundry(Email, Password);
                if (s.SignInAdmin()) {
                    currentAdmin = s.getAdmin(Email);
                }
            }
            adminMenu.ShowAdminMenu();
            Show();

        }

        else if(userType ==2){
            int y;
            System.out.println("1 :Sign in");
            System.out.println("2: Sign up");
            y = sc.nextInt();

            if (y == 1) {
                System.out.print("Please Enter your email: ");
                Email = sc.next().toLowerCase();
                System.out.print("Please Enter your password: ");
                Password = sc.next();
                System.out.println();
                SignInBoundry s = new SignInBoundry(Email, Password);
                while (!s.SignInUSer()) {
                    System.out.println("Email or Password is not correct");
                    System.out.println();
                    System.out.print("Please Enter your email: ");
                    Email = sc.next().toLowerCase();
                    System.out.print("Please Enter your password: ");
                    Password = sc.next();
                    System.out.println();
                    s = new SignInBoundry(Email, Password);
                    if (s.SignInUSer()) {
                        currentCustomer = s.getCustomer(Email);
                    }
                }

                customerMenu.ShowUserMenu();}
            else if (y == 2)//sign up user
            {
                System.out.print("Please Enter user name: ");
                user = sc.next();
                sc.nextLine();
                System.out.print("Please Enter your email: ");
                Email = sc.next().toLowerCase();
                System.out.print("Please Enter your password: ");
                Password = sc.next();
                SignInBoundry s = new SignInBoundry(user, Email, Password);
                System.out.println(s.SignUp());
                currentCustomer = s.getCustomer(Email);
                customerMenu.ShowUserMenu();
                Show();

            }

        }

        while(userType !=3) {
            while (userType != 1 && userType != 2) {
                System.out.print("Wrong Choice, Enter again: ");
                userType = sc.nextInt();
            }

            if (userType == 1) {
                Show();
            } else if (userType == 2) {
                Show();
            }

        }

    }


    }


