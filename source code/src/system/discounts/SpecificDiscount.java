package system.discounts;

import system.services.Donations;
import system.services.InternetService;
import system.services.Landline;
import system.services.MobileRecharge;

public class SpecificDiscount extends Discount {

    // a function to check which service to be registered in the list to be notified according
    // to a string representing or containing the service name
    public void setService(String s){
        if (s.toLowerCase().contains("internet")){
            registerService(new InternetService());
        }
        else if (s.toLowerCase().contains("mobile")) {
            registerService(new MobileRecharge());
        }
        else if (s.toLowerCase().contains("donation")) {
            registerService(new Donations());
        }
        else if (s.toLowerCase().contains("landline")) {
            registerService(new Landline());
        }
    }

    // check first if the list is not empty in order to avoid null errors
    //  check if the service can handle another discount addition without exceeding 1
    @Override
    public boolean setDiscount(double amount) {
        if (services.size() > 0) {
            if (services.get(0).getDiscount() + amount < 1)
                services.get(0).updateDiscount(amount);
            else
                return false;
        }
        return true;
    }

    // remove the discount for the service specified
    @Override
    public void removeDiscount() {
        if (services.size() > 0)
            services.get(0).updateDiscount(-services.get(0).getDiscount());
    }
}
