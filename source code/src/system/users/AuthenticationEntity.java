package system.users;

import java.util.HashMap;

public class AuthenticationEntity  {

    private static  HashMap<String, Customer> user = new HashMap<>();
    private static HashMap<String, Admin> admin = new HashMap<>();
    private static HashMap<String, Customer> username = new HashMap<>();



    public AuthenticationEntity() {
        Customer c= new Customer( "habibayasser",  "habibayasser@gmail.com",  "123");

        user.put(c.getEmail(),c);
        username.put(c.getCustomerName(),c);

        Admin a=new Admin("maimostafa","maimostafa@gmail.com","321");
        admin.put(a.getEmail(),a);

    }

     static public boolean CheckAccountForUser(String Email, String Password)//take the password and email to check for it in database(hashmap)for user
    {
        if (user.containsKey(Email))
        {
            return Password.equals(user.get(Email).getPassword());
        }
        else
        {
            return false;
        }
    }
    static boolean CheckAccountForAdmin(String Email, String Password )//take the password and email to check for it in database(hashmap)for admin
    {
         if(admin.containsKey(Email))
         {
             return Password.equals(admin.get(Email).getPassword());

        }
        else
        {
            return false;
        }
    }

     static String CreateAccount(String UserName, String Email,String Password)//create account for user
    {

        if(user.containsKey(Email))
        {
            return "This email is not valid";
        }
        else if(username.containsKey(UserName))
        {
           return "This UserName is not valid";
        }
        else
        {

            Customer cus=new Customer(UserName,Email,Password);
            user.put(cus.getEmail(), cus);
            username.put(cus.getCustomerName(), cus);
            return "Sign up successfully";

        }
    }
    static Customer details(String email)
    {
        return user.get(email);
    }
    static Admin Info(String email)
    {
        return admin.get(email);
    }



}
