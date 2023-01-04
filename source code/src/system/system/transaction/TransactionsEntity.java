package system.transaction;

import java.util.HashMap;

public class TransactionsEntity {
    private static HashMap<Integer, Transaction> refunds = new HashMap<Integer, Transaction>();
    private static HashMap<Integer, Transaction> Transactions = new HashMap<Integer, Transaction>();

    public static HashMap<Integer, Transaction> getRefunds() {
        return refunds;
    }

    public static HashMap<Integer, Transaction> getTransactions() {
        return Transactions;
    }
    public void addTransaction(Transaction T)
    {
        Transactions.put(T.getTrans_ID(),T);
    }
    public Transaction findTransaction(int id)
    {
        return Transactions.get(id);
    }
    public Transaction findRefund(int id)
    {
        return refunds.get(id);
    }
    public void removeRefundRequest(int id)
    {
        if(refunds.containsKey(id)) {
            refunds.remove(id);
        }
    }
    public void requestRefund(Transaction T)
    {
        refunds.put(T.getTrans_ID(),T);
    }

}
