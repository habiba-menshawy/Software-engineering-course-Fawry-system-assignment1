package system.discounts;
import system.services.*;

import java.util.Vector;

public class DiscountController {

    Services internetService = new InternetService();
    Services mobileRecharge = new MobileRecharge();
    Services donations = new Donations();
    Services landline = new Landline();

    public boolean createOverallDiscount(double discAmount) {
        Discount oDiscount = new OverallDiscount();
        return oDiscount.setDiscount(discAmount);
    }
    public boolean createSpecificDiscount (double discAmount, String servName) {
        SpecificDiscount sDiscount = new SpecificDiscount();
        sDiscount.setService(servName);
        return sDiscount.setDiscount(discAmount);
    }
    public void removeAllDiscount(){
        OverallDiscount remALlDiscount = new OverallDiscount();
        remALlDiscount.removeDiscount();
    }
    public void removeSpecificDiscount(String name){
        SpecificDiscount remSpecDiscount = new SpecificDiscount();
        remSpecDiscount.setService(name);
        remSpecDiscount.removeDiscount();
    }

    public Vector<Double> returnDiscounts() {
        Vector<Double> discounts = new Vector<Double>();
        discounts.add(internetService.getDiscount() * 100);
        discounts.add(mobileRecharge.getDiscount()*100);
        discounts.add(donations.getDiscount()*100 * 100);
        discounts.add(landline.getDiscount()*100);
        return discounts;
    }
}
