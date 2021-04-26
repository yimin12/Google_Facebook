package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * 1 -> A
 * 2 -> B
 * 3 -> C
 *  ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * Example
 * Example1
 *
 * Input: 28
 * Output: "AB"
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        if(n <= 0) return "";
        StringBuilder str = new StringBuilder();

        while (n > 0) {
            n--;
            str.append ( (char) ( (n % 26) + 'A'));
            n /= 26;
        }
        return str.reverse().toString();
    }
}
