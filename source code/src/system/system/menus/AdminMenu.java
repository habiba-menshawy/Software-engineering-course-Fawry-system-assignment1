package system.menus;

import system.discounts.DiscountController;
import system.users.Admin;
import system.users.SignInBoundry;
import system.providers.*;

import java.util.HashMap;
import java.util.Scanner;
import system.transaction.*;

public class AdminMenu {
    private final DiscountController discountController = new DiscountController();
    private final RefundController refundC = new RefundController();
    Scanner sc = new Scanner(System.in);
    public static String Email;
    public static String Password;

    static Admin currentAdmin = new Admin("maimostafa", "maimostafa@gmail.com", "321");


    public void ShowAdminMenu() {
        while (true) {
                System.out.print("Please Enter your email: ");
                Email = sc.next().toLowerCase();
                System.out.print("Please Enter your password: ");
                Password = sc.next();
                SignInBoundry s = new SignInBoundry(Email,Password);
                while (!s.SignInAdmin()) {
                    System.out.println("Email or Password is not correct");
                    System.out.println();
                    System.out.print("Please Enter your email: ");
                    Email = sc.next().toLowerCase();
                    System.out.print("Please Enter your password: ");
                    Password = sc.next();
                    System.out.println();
                    s = new SignInBoundry(Email,Password);
                    if (s.SignInAdmin()) {
                        currentAdmin = s.getAdmin(Email);
                    }
                }
                System.out.println("[1]Add service provider\n[2]Add Discount\n[3]Remove Discounts\n[4]Show Refunds");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Please choose a service from the following list:");
                        System.out.println("1: Internet Payment services");
                        System.out.println("2: Mobile recharge services");
                        System.out.println("3: Donations services");
                        System.out.println("4: Landline services");
                        System.out.println("5: cancel");
                        int option = sc.nextInt();
                        System.out.print("Please enter the name of the new service provider:");
                        sc.nextLine();
                        String provider= sc.nextLine();
                        switch (option) {
                            case 1 -> {
                                InternetServiceProvider ISP =  InternetServiceProvider.getInstance();
                                ISP.addService_provider(provider);
                                System.out.println("The providers for this service now are:");
                                ISP.printService_providers();
                            }
                            case 2 -> {
                                MobileServiceProvider MSP = MobileServiceProvider.getInstance();
                                MSP.addService_provider(provider);
                                System.out.println("The providers for this service now are:");
                                MSP.printService_providers();
                            }
                            case 3 -> {
                                DonationServiceProvider DSP =  DonationServiceProvider.getInstance();
                                DSP.addService_provider(provider);
                                System.out.println("The providers for this service now are:");
                                DSP.printService_providers();
                            }
                            case 4 -> {
                                LandlineServiceProvider LSP =  LandlineServiceProvider.getInstance();
                                LSP.addService_provider(provider);
                                System.out.println("The providers for this service now are:");
                                LSP.printService_providers();
                            }

                        }

                    }
                    case 2 -> {
                        System.out.println("[1]Add Overall Discount\n[2]Add a Specific Discount");
                        int ch2 = sc.nextInt();
                        System.out.print("Add discount amount [0..1]: ");
                        double amount = sc.nextDouble();
                        switch (ch2) {
                            case 1 -> createOverallDiscount(amount);
                            case 2 -> createSpecificDiscount(amount);
                        }
                    }
                    case 3 -> {
                        System.out.println("[1]Remove all discounts\n[2]Remove a Specific Discount");
                        int ch2 = sc.nextInt();
                        switch (ch2) {
                            case 1 -> removeAllDiscount();
                            case 2 -> removeSpecificDiscount();
                        }
                    }
                    case 4 -> {
                        getRefunds();
                        System.out.println("[1]Search by Transaction ID\n[2]Exit");
                        int ch2 = sc.nextInt();
                        switch (ch2) {
                            case 1 ->{
                                System.out.print("Enter Transaction ID: ");
                                int id = sc.nextInt();
                                System.out.println("[1]Accept Refund\n[2]Refuse Refund");
                                int acceptance = sc.nextInt();
                                switch (acceptance) {
                                    case 1 -> {
                                        acceptRefund(id);
                                    }
                                    case 2 -> {
                                        refuseRefund(id);
                                    }
                                }
                            }
                        }
                    }
                }
                //m.checkDiscounts();
                System.out.println("[1]Continue\n[2] Exit Program");
                System.out.print("===> ");
                int ch = sc.nextInt();
                if (ch == 2) break;

        }
    }
    
    public void getRefunds() {
        HashMap<Integer, Transaction> refunds = refundC.getRefunds();
        for (Integer key: refunds.keySet()){
            System.out.print(key+" ");
            System.out.print(refunds.get(key).getCustomer().getCustomerName()+" ");
            System.out.print(refunds.get(key).getPay_amount()+" ");
            System.out.print(refunds.get(key).getProvider()+" ");
            System.out.print(refunds.get(key).getService_name());
            System.out.println();
        }
    }
    public void acceptRefund(int id) {
        if(refundC.acceptRequest(id))
            System.out.println("Refund Accepted.");
        else
            System.out.println("Wrong Transaction ID");
    }
    public void refuseRefund(int id) {
        if(refundC.requestRefund(id))
            System.out.println("Refund Accepted.");
        else
            System.out.println("Wrong Transaction ID");
    }

    void createOverallDiscount(double amount) {
        if (!discountController.createOverallDiscount(amount)) {
            System.out.println("You cannot apply this discount, some services may reach 100% discount");
        }else System.out.println("Discount is added Successfully");

    }
    void createSpecificDiscount(double amount) {
        System.out.print("Add service name: ");
        sc.nextLine();
        String sname = sc.nextLine();
        if (!discountController.createSpecificDiscount(amount, sname))
            System.out.println("You cannot apply more discounts to this service");
        else
            System.out.println("Discount is added Successfully");
    }
    void removeAllDiscount(){
        discountController.removeAllDiscount();
    }
    void removeSpecificDiscount() {
        sc.nextLine();
        System.out.print("Add service name: ");
        String sname = sc.nextLine();
        discountController.removeSpecificDiscount(sname);
    }
}