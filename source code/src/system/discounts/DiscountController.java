package system.discounts;
import system.services.*;

import java.util.Vector;

public class DiscountController {

    private Services internetService = new InternetService();
    private Services mobileRecharge = new MobileRecharge();
    private Services donations = new Donations();
    private Services landline = new Landline();
    private Discount oDiscount = new OverallDiscount();
    private SpecificDiscount sDiscount = new SpecificDiscount();



    public boolean createOverallDiscount(double discAmount) {
        return oDiscount.setDiscount(discAmount);
    }
    public boolean createSpecificDiscount (double discAmount, String servName) {
        sDiscount.setService(servName);
        return sDiscount.setDiscount(discAmount);
    }
    public void removeAllDiscount(){
        oDiscount.removeDiscount();
    }
    public void removeSpecificDiscount(String name){
        SpecificDiscount rsDis = new SpecificDiscount();
        rsDis.setService(name);
        rsDis.removeDiscount();
    }

    public Vector<Double> returnDiscounts() {
        Vector<Double> discounts = new Vector<Double>();
        discounts.add(internetService.getDiscount() * 100);
        discounts.add(mobileRecharge.getDiscount()*100);
        discounts.add(donations.getDiscount()* 100);
        discounts.add(landline.getDiscount()*100);
        return discounts;
    }
}
