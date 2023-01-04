package system.providers;

import system.services.*;
import system.forms.*;
import system.transaction.*;
import java.util.List;
import java.util.Vector;

public class LandlineServiceProvider extends ServiceProvider {
    private Landline service=new Landline();
    public static  Vector<String>names=new Vector<String>(List.of(new String[]{}));

    static private LandlineServiceProvider instance;

    private LandlineServiceProvider(){}

    public static LandlineServiceProvider getInstance()
    {
        if(instance==null)
        {
            instance=new LandlineServiceProvider();
        }
        return instance;
    }

    public void choose_form()
    {
        form= new LandlineForm();
        Vector<String> info=form.create_form(names);
        setPay_amount(Double.parseDouble(info.get(0)));
        getService().setProvider(info.get(1));
        getService().setLandline_num(info.get(2));
        getService().setReceipt_plan(info.get(3));

    }

    public void pay_transaction() {
        setPay_amount(getPay_amount()-(getService().getDiscount() * getPay_amount()));
        if (getPayment().pay(getPay_amount()) > -2) {
            transactions.addTransaction(getTrans());
            System.out.println("Transaction completed successfully ");
            System.out.println("Transaction ID: "+trans.getTrans_ID());
            System.out.println("For service: "+trans.getService_name());
            if(!trans.getProvider().equals(""))
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

    public Landline getService() {
        return service;
    }

    public void setService(Landline service) {
        this.service = service;
    }
    public static Vector<String> getNames() {
        return names;
    }

    public static void setNames(Vector<String> names) {
        LandlineServiceProvider.names = names;
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
