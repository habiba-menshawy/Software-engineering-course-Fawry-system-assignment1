package system.transaction;

import java.util.HashMap;

// controller to handle all refund functionalities
public class RefundController {
    private TransactionsEntity Transactions = new TransactionsEntity();

    // get all refund requests
    public HashMap<Integer, Transaction> getRefunds()
    {
        return Transactions.getRefunds();
    }

    // request refund for the specified transaction
    public boolean requestRefund(int id) {
        // get transaction by id
        Transaction toBeRefunded = getTransactionByID(id);
        if(toBeRefunded!=null) {
            // set refund attribute with true to indicate the refund request
            toBeRefunded.setRefund(true);
            // call request refund funtion to add the transaction to the list of refund requests
            Transactions.requestRefund(toBeRefunded);
            return true;
        }
        else
            return false;
    }
    // search in map of transactions by transaction ID
    public Transaction getTransactionByID(int id)
    {
        return Transactions.findTransaction(id);
    }

    // accept refund request of the specified transaction
    public boolean acceptRequest(int id) {
        // find and get the transaction from the map of transactions
        Transaction acceptedRefund = Transactions.findRefund(id);
        if (acceptedRefund != null) {
            // get the pay amount of the transaction
            double amount = acceptedRefund.getPay_amount();
            // return pay amount to the customer wallet
            acceptedRefund.getCustomer().setWallet(acceptedRefund.getCustomer().getWallet() + amount);
            // set refund attribute to false to indicate the end of the request
            acceptedRefund.setRefund(false);
            // remove request from list of refunds
            Transactions.removeRefundRequest(id);
            return true;
        }
        return false;
    }
    // refuse refund request of the specified transaction
    public boolean refuseRequest(int id) {
        // find and get the transaction from the map of transactions
        Transaction refusedRefund = Transactions.findRefund(id);
        if(refusedRefund != null){
            // set refund attribute to false to indicate the end of the request
            refusedRefund.setRefund(false);
            // remove request from list of refunds
            Transactions.removeRefundRequest(id);
            return true;
        }
        return false;

    }
}
