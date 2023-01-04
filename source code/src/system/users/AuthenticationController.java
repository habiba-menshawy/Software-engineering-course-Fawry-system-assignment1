package system.users;

public class AuthenticationController  {

    AuthenticationController(){
        AuthenticationEntity c=new AuthenticationEntity();
    }



    boolean  SignInUser(String Email,String Password) //send email and password to the entity to check about the email and password for user
    {
         return AuthenticationEntity.CheckAccountForUser(Email, Password);

    }
    boolean SignInAdmin(String Email,String Password)//send email and password to the entity to check about the email and password for admin
    {
       return AuthenticationEntity.CheckAccountForAdmin(Email, Password);

    }
    String SignUpUser(String Username,String Email,String password)//send email and password and username to check if there is user has the
            //same user name or email
    {

        return AuthenticationEntity.CreateAccount(Username, Email,password);

   }

   Customer getCurrentCustomer(String Email) {
        return AuthenticationEntity.details(Email);
   }

   Admin getCurrentAdmin(String Email){
        return AuthenticationEntity.Info(Email);
   }



}
