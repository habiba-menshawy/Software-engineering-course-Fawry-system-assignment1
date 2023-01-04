package system.providers;

import system.services.*;
import system.forms.*;
import system.transaction.*;
import java.util.List;
import java.util.Vector;

public class MobileServiceProvider extends ServiceProvider {
    private MobileRecharge service=new MobileRecharge();
    public static  Vector<String>names=new Vector<String>(List.of(new String[]{"Vodafone","Orange","Etisalat","We"}));

    static private MobileServiceProvider instance;

    private MobileServiceProvider(){}

    public static MobileServiceProvider getInstance()
    {
        if(instance==null)
        {
            instance=new MobileServiceProvider();
        }
        return instance;
    }

    public void choose_form()
    {
        form= new MobileForm();
        Vector<String>info=form.create_form(names);
        setPay_amount(Double.parseDouble(info.get(0)));
        getService().setProvider(info.get(1));
        getService().setMobile_num(info.get(2));

    }

    public void pay_transaction() {
        setPay_amount(getPay_amount()-(getService().getDiscount() * getPay_amount()));
        if (getPayment().pay(getPay_amount()) > -2) {
            transactions.addTransaction(getTrans());
            System.out.println("Transaction completed successfully ");
            System.out.println("Transaction ID: "+trans.getTrans_ID());
            System.out.println("For service: "+trans.getService_name());
            System.out.println("The service provider: "+trans.getProvider());
            System.out.println("The amount paid: "+ trans.getPay_amount());
        } else if (getPayment().pay(getPay_amount()) > -1) {
            getCustomer().setWallet(getPayment().pay(getPay_amount()));
        } else if (getPayment().pay(getPay_amount()) == -2) {
            System.out.println("Transaction Payment Failed.");
        }

    }

    public  void create_Trans()
    {
        setTrans(new Transaction(getService().getName(), getCustomer(), getPay_amount(), getTrans_ID(),getService().getProvider()));
    }


    public MobileRecharge getService() {
        return service;
    }

    public void setService(MobileRecharge service) {
        this.service=service;
    }

    public static Vector<String> getNames() {
        return names;
    }

    public static void setNames(Vector<String> names) {
        MobileServiceProvider.names = names;
    }
    public void addService_provider(String name)
    {
        names.add(name);
    }
    public void printService_providers()
    {   int i=1;
        for (String name : names) {
            System.out.println(i+" - " + name);
            i++;
        }
    }

}
