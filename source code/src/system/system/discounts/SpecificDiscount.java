package system.discounts;

import system.services.Donations;
import system.services.InternetService;
import system.services.Landline;
import system.services.MobileRecharge;

public class SpecificDiscount extends Discount {

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

    @Override
    public void removeDiscount() {
        if (services.size() > 0)
            services.get(0).updateDiscount(-services.get(0).getDiscount());
    }
}
