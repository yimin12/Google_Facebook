package Contest.Bloomberg;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 21:02
 *   @Description :
 *
 */
public class InvalidTransactions {

    public List<String> invalidTransactions(final String[] transactions) {
        final List<String> invalid = new ArrayList<>();
        final Map<String, List<Transaction>> map = new HashMap<>();

        /*
         * build a map with name as key and value as list of transactions for that name
         */
        for (final String transaction : transactions) {
            final Transaction tran = new Transaction(transaction);

            if (map.containsKey(tran.name)) {
                map.get(tran.name).add(tran);
            } else {
                final List<Transaction> list = new ArrayList<>();
                list.add(tran);
                map.put(tran.name, list);
            }
        }

        for (final String transaction : transactions) {
            final Transaction tran = new Transaction(transaction);

            if (!isValid(map.get(tran.name), tran)) {
                invalid.add(transaction);
            }

        }

        return invalid;
    }

    public boolean isValid(final List<Transaction> transactions, final Transaction transaction) {

        /* if there is only one transaction and the amount is less than 1000 */
        if (transactions.size() <= 1 && transaction.amount < 1000)
            return true;

        /* check against all other transaction to check it this one is valid */
        for (final Transaction tran : transactions) {
            if (transaction.invalidTransaction(tran.city, tran.time)) {
                return false;
            }
        }
        return true;
    }

    class Transaction {
        String name;
        int time;
        int amount;
        String city;

        Transaction(final String transaction) {
            final String[] t = transaction.split(",");
            this.name = t[0];
            this.time = Integer.parseInt(t[1]);
            this.amount = Integer.parseInt(t[2]);
            this.city = t[3];
        }

        /*
         * the amount exceeds $1000, or;
         *
         * if it occurs within (and including) 60 minutes of another transaction with
         * the same name in a different city. Each transaction string transactions[i]
         * consists of comma separated values representing the name, time (in minutes),
         * amount, and city of the transaction.
         */
        public boolean invalidTransaction(final String city, final int time) {
            return invalidAmount() || differentCity(city, time);
        }

        private boolean differentCity(final String city, final int time) {
            return !this.city.equals(city) && Math.abs(this.time - time) <= 60;
        }

        private boolean invalidAmount() {
            return this.amount > 1000;
        }
    }
}
