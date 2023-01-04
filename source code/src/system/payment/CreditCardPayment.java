package system.payment;

import java.util.Scanner;

public class CreditCardPayment implements Payment {
    String creditID;
    public CreditCardPayment(String CID)
    {
        this.creditID=CID;
    }
    public double pay(double a)
    {  for(int i=0;i<2;i++)
    { if(creditID.length()==12) {
        return -1;
    }
    else{
        System.out.println("Credit card not valid Please re-enter your credit card number:");
        Scanner input = new Scanner(System.in);
        creditID = input.nextLine();

    }
    }
        return -2;
    }
}
