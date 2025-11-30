
package dao;

import model.Transaction;
import java.util.*;

public class TransactionDAO {
    private Map<Integer, Transaction> transactions = new HashMap<>();
    private int nextId = 1;

    public synchronized Transaction addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions.values());
    }

    public Transaction findTransactionById(int id) {
        return transactions.get(id);
    }
}
