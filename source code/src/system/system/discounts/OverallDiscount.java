package system.discounts;

import system.services.*;

public class OverallDiscount extends Discount {

    OverallDiscount(){
        registerService(new MobileRecharge());
        registerService(new InternetService());
        registerService(new Landline());
        registerService(new Donations());
    }

    @Override
    public boolean setDiscount(double amount) {
        for (Services service : services) {
            if (service.getDiscount()+amount < 1)
                service.updateDiscount(amount);
            else return false;
        }
        return true;
    }

    @Override
    public void removeDiscount() {
        for (int i = 0; i < 4; i++){
            services.get(i).updateDiscount(-services.get(i).getDiscount());
        }
    }


}
