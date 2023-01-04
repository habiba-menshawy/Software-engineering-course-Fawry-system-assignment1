package system.menus;

import system.discounts.DiscountController;
import system.providers.DonationServiceProvider;
import system.providers.InternetServiceProvider;
import system.providers.LandlineServiceProvider;
import system.providers.MobileServiceProvider;
import system.users.Customer;
import system.users.CustomerController;
import system.users.SignInBoundry;
import java.util.Scanner;
import java.util.Vector;

import system.payment.*;
import system.transaction.*;


public class CustomerMenu {
    CustomerController customerController = new CustomerController();
    public RefundController refundC = new RefundController();
    DiscountController dis = new DiscountController();
    Scanner sc = new Scanner(System.in);
    public static String Email;
    public static String Password;
    public static String userName;
    private String service;
    private String creditcard;
    static Customer currentCustomer = new Customer("habibayasser",  "habibayasser@gmail.com",  "123");
    private double amount;
    private Payment payment;


    private final DiscountController discountController = new DiscountController();


    public void ShowUserMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("1: Search for services");
            System.out.println("2: Check for discount");
            System.out.println("3: Add to wallet");
            System.out.println("4: Ask for refund");
            System.out.println("5: Check wallet");
            System.out.println("6: Pay for a service");
            System.out.println("7: Sign Out");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Please Enter service name: ");
                    service = sc.next().toLowerCase();
                    System.out.println(customerController.Search(service));
                }
                case 2 -> {
                    Vector<Double> showDiscounts = new Vector<Double>();
                    showDiscounts = discountController.returnDiscounts();
                    System.out.println("Discount Applied on Internet Payment Services: " + showDiscounts.get(0) + "%");
                    System.out.println("Discount Applied on Mobile Recharge Services: " + showDiscounts.get(1) + "%");
                    System.out.println("Discount Applied on Donations Services: " + showDiscounts.get(2) + "%");
                    System.out.println("Discount Applied on Landline Services: " + showDiscounts.get(3) + "%");
                }
                case 3 -> {
                    System.out.println("Please enter credit card number [12 number]");
                    creditcard = sc.next();
                    System.out.println("Please enter the amount you'd like to add to your wallet");
                    amount = sc.nextDouble();
                    System.out.println("Your wallet now contains " + customerController.AddToWallet(currentCustomer, creditcard, amount));
                }
                case 4 -> {
                    System.out.println("Please Enter Transaction id");
                    int id = sc.nextInt();
                    requestRefund(id);
                }
                case 5 -> {
                    System.out.println("You have" + currentCustomer.getWallet() + " L.E. in your wallet");
                }
                case 6 -> {
                    System.out.println("Please choose a payment method:");
                    System.out.println("1: Pay by cash");
                    System.out.println("2: Pay by credit card");
                    System.out.println("3: Pay by wallet");
                    int ans = sc.nextInt();

                    switch (ans) {
                        case 1 -> {
                            payment = new CashPayment();
                        }
                        case 2 -> {
                            System.out.print("Please enter your credit card number:");
                            String creditcard = sc.next();
                            payment = new CreditCardPayment(creditcard);
                        }
                        case 3 -> {
                            System.out.println("the wallet contains " + currentCustomer.getWallet());
                            payment = new WalletPayment(currentCustomer.getWallet());
                        }
                    }

                    System.out.println("1: Internet Payment services");
                    System.out.println("2: Mobile recharge services");
                    System.out.println("3: Donations services");
                    System.out.println("4: Landline services");
                    System.out.println("5: cancel");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1 -> {
                            InternetServiceProvider ISP = InternetServiceProvider.getInstance();

                            ISP.create_transaction(currentCustomer, payment);
                        }
                        case 2 -> {
                            MobileServiceProvider MSP = MobileServiceProvider.getInstance();
                            MSP.printService_providers();
                            int sp = sc.nextInt();
                            MSP.create_transaction(currentCustomer, payment);
                        }
                        case 3 -> {
                            DonationServiceProvider DSP = DonationServiceProvider.getInstance();
                            DSP.create_transaction(currentCustomer, payment);
                        }
                        case 4 -> {
                            LandlineServiceProvider LSP = LandlineServiceProvider.getInstance();
                            LSP.create_transaction(currentCustomer, payment);
                        }

                    }


                }
                case 7 ->
                        {
                    flag=false;
                    break;


                }
            }


        }
    }
    public void requestRefund(int id)
    {
        if(currentCustomer.getCustomerName()==refundC.getTransactionByID(id).getCustomer().getCustomerName())
        {
            if(refundC.requestRefund(id))
                System.out.println("Refund request done successfully.");
            else
                System.out.println("Wrong Transaction ID.");
        }

        else
            System.out.println("No such Transaction for the current user.");

    }
}






