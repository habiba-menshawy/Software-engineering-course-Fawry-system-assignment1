package system.users;

public class AuthenticationController  {

    AuthenticationController(){
        AuthenticationEntity c=new AuthenticationEntity();
    }



    boolean  SignInUser(String Email,String Password)
    {
         return AuthenticationEntity.CheckAccountForUser(Email, Password);

    }
    boolean SignInAdmin(String Email,String Password)
    {
       return AuthenticationEntity.CheckAccountForAdmin(Email, Password);

    }
    String SignUpUser(String Username,String Email,String password)
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
