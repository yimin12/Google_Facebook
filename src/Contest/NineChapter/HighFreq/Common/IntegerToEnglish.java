package Contest.NineChapter.HighFreq.Common;

public class IntegerToEnglish {

    public static String[] IN_TWENTY = new String[] {
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };

    public static String[] IN_HUNDRED = new String[]{
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    public String numberToWords(int num) {
        // Write your code here
        if(num == 0)
            return "Zero";
        return helper(num);
    }

    public String helper(int i) {
        String res = "";
        if (i == 0) {
            return res;
        } else if (i > 0 && i < 20) {
            res = IN_TWENTY[i];
        } else if (i >= 20 && i < 100) {
            res = IN_HUNDRED[i / 10 - 1] + " " + IN_TWENTY[i % 10];
        } else if (i >= 100 && i < 1000) {
            res = helper(i / 100) + " Hundred " + helper(i % 100);
        } else if (i >= 1000 && i < 1000000) {
            res = helper(i / 1000) + " Thousand " + helper(i % 1000);
        } else if (i >= 1000000 && i < 1000000000) {
            res = helper(i / 1000000) + " Million " + helper(i % 1000000);
        } else {
            res = helper(i / 1000000000) + " Billion " + helper(i % 1000000000);
        }
        return res.trim();
    }
}
