package JavaBasic.Java8.stream.stream_practise.practise_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/21 11:48
 *   @Description :
 *
 */
public class StreamInAction {

    public static void main(String[] args) {

        // 4 trader
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        // 6 transactions
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. Find all transactions in the year 2011 and sort them by value in ascending order
        List<Transaction> result = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(result);

        // 2. What are all unique cities where traders work?
        transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);
        System.out.println("==================");

        // 3. Find all traders from Cambridge and sort them by name
        transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Cambridge")).distinct()
                .sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);

        // 4. Return a string of all traders’ names sorted alphabetically
        String value = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (name1, name2) -> name1 + " " + name2);
        System.out.println(value);

        // 5. Are any traders based in Milan?
        boolean inMilan1 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        boolean inMilan2 = transactions.stream().map(Transaction::getTrader).anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(inMilan1 + " : " + inMilan2);

        // 6. Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);

        // 7. What’s the highest value of all the transactions?
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce((i, j) -> i > j ? i : j);
        System.out.println(max.get());

        // 8. Find the transaction with the smallest value.
        Optional<Integer> min = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
        System.out.println(min.get());

    }
}
