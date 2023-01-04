package system.transaction;

import java.util.HashMap;

public class RefundController {
    private TransactionsEntity Transactions = new TransactionsEntity();

    public HashMap<Integer, Transaction> getRefunds()
    {
        return Transactions.getRefunds();
    }
    public boolean requestRefund(int id) {
        Transaction toBeRefunded = getTransactionByID(id);
        if(toBeRefunded!=null) {
            toBeRefunded.setRefund(true);
            Transactions.requestRefund(toBeRefunded);
            return true;
        }
        else
            return false;
    }
    public Transaction getTransactionByID(int id)
    {
        return Transactions.findTransaction(id);
    }

    public boolean acceptRequest(int id) {
        Transaction acceptedRefund = Transactions.findRefund(id);
        if (acceptedRefund != null) {
            double amount = acceptedRefund.getPay_amount();
            acceptedRefund.getCustomer().setWallet(acceptedRefund.getCustomer().getWallet() + amount);
            acceptedRefund.setRefund(false);
            Transactions.removeRefundRequest(id);
            return true;
        }
        return false;
    }

    public boolean refuseRequest(int id) {
        Transaction refusedRefund = Transactions.findRefund(id);
        if(refusedRefund != null){
            refusedRefund.setRefund(false);
            Transactions.removeRefundRequest(id);
            return true;
        }
        return false;

    }
}
