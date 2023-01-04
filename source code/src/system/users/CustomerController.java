package system.users;

import java.util.ArrayList;

public class CustomerController {
    ArrayList<String> arraylist_1 = new ArrayList<String>();
    private String name;

    public CustomerController()
    {
        arraylist_1.add("Mobile Recharge Services");
        arraylist_1.add("Internet Payment Services");
        arraylist_1.add("Landline Services");
        arraylist_1.add("Donations");

    }
    public String Search(String s)
    {
        char c=s.charAt(0);
        if(c=='m' )
        {
            name=arraylist_1 .get(0);
        }
        else if(c=='i')
        {
            name=arraylist_1 .get(1);
        }
        else if(c=='l' )
        {
            name=arraylist_1 .get(2);
        }
        else if(c=='d')
        {
            name=arraylist_1 .get(3);
        }
        else
        {
            name="No result for "+s;
        }
        return name;
    }
    public double AddToWallet(Customer customer, String cc, double amount)
    {
        if(cc.length()==12)
        {
            customer.setWallet(customer.getWallet()+amount);
        }
        return customer.getWallet();
    }

}
