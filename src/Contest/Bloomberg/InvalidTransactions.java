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

    public class Transaction{
        String name, city, trans;
        int time;

        public Transaction(String name, int time, String city, String trans){
            this.name = name;
            this.time = time;
            this.city = city;
            this.trans = trans;
        }
    }

    public List<String> invalidTransactions(String[] transactions) {
        Set<String> res = new HashSet<>();
        Map<String, List<Transaction>> account = new HashMap<>();
        for(String t : transactions){
            String[] split = t.split(",");
            String name = split[0];
            int time = Integer.valueOf(split[1]);
            int amount = Integer.valueOf(split[2]);
            String city = split[3];
            if(amount > 1000) {
                res.add(t);
            }
            List<Transaction> others = account.get(name); // update the transactions
            if(others == null){
                others = new ArrayList<>();
                others.add(new Transaction(name, time, city, t));
                account.put(name, others);
            } else {
                for(Transaction tra : others){
                    if(!tra.city.equals(city) && Math.abs(tra.time - time) <= 60){
                        res.add(tra.trans);
                        res.add(t);
                    }
                }
                others.add(new Transaction(name, time, city, t));
            }
        }
        return new ArrayList<String>(res);
    }
}
