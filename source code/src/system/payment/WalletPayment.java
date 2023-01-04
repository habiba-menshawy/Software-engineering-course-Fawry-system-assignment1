package system.payment;

public class WalletPayment implements Payment {
    private double wallet;
    public WalletPayment(double w)
    {
        wallet=w;
    }
    public double pay(double a)
    {
        if(wallet>=a)
        {
            return wallet-a;
        }
        else
        {
            return -2;
        }
    }
}
