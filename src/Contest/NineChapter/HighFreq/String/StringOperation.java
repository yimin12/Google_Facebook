package Contest.NineChapter.HighFreq.String;

import java.util.ArrayList;
import java.util.List;

public class StringOperation {

    /**
     * Reverse Single word
     */
    public String reverseSingleWords(String input){
        if(input == null || input.length() <= 1) return input;
        char[] array = input.toCharArray();
        for(int left = 0, right = input.length() - 1; left < right; left ++, right --){
            swap(array, left, right);
        }
        return new String(array);
    }

    private void swap(char[] array, int left, int right){
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * Reverse the sentence
     */
    public String reverseSentence(String input){
        char[] array = input.toCharArray();
        reverse(array, 0, array.length - 1);
        int start = 0;
        for(int i = 0; i < array.length; i ++){
            if(array[i] != ' ' && (i == 0 || array[i - 1]==' ')){
                start = i;
            }
            if(array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')){
                reverse(array, start, i);
            }
        }
        return new String(array);
    }

    private void reverse(char[] c, int left, int right){
        while(left < right){
            swap(c, left ++, right --);
        }
    }

    /**
     * Right Shift By N Characters
     */
    public String rightShift(String s, int n){
        if(s == null || s.length() == 0) return "";
        n %= (s.length());
        char[] c = s.toCharArray();
        reverse(c, c.length - n, c.length - 1);
        reverse(c, 0, c.length - 1 - n);
        reverse(c, 0, c.length - 1);
        return new String(c);
    }

    /**
     * String Replacement
     */
    public String replace(String input, String s, String t){
        char[] c = input.toCharArray();
        if(s.length() > t.length()){
            // getting shorter
            return replaceShorter(c, s, t);
        }
        return replaceLonger(c, s, t);
    }

    private String replaceShorter(char[] c, String s, String t){
        int slow = 0, fast = 0;
        while(fast < c.length){
            if(fast <= c.length - s.length() && equalsString(c, fast, s)){
                copy(c, slow, t);
                slow += t.length();
                fast += s.length();
            } else {
                c[slow ++] = c[fast ++];
            }
        }
        return new String(c, 0, slow);
    }

    public String replaceLonger(char[] c, String s, String t){
        ArrayList<Integer> matches = getAllMatches(c, s);
        char[] res = new char[c.length + matches.size() * (t.length() - s.length())];
        int lastIndex = matches.size() - 1;
        int fast = c.length - 1; // filling backword
        int slow = res.length - 1;
        while(fast >= 0){
            if(lastIndex >= 0 && fast == matches.get(lastIndex)){
                copy(res, slow - t.length() + 1, t);
                slow -= t.length();
                fast -= s.length();
                lastIndex --;
            } else {
                res[slow--] = c[fast --];
            }
        }
        return new String(res);
    }

    private boolean equalsString(char[] c, int from, String s){
        for(int i = 0; i < s.length(); i ++){
            if(c[from + i] != s.charAt(i)){
                return false;
            }
        }
        return true;
    }

    private void copy(char[] c, int from, String s){
        for(int i = 0; i < s.length(); i ++){
            c[from + i] = s.charAt(i);
        }
    }

    private ArrayList<Integer> getAllMatches(char[] c, String s){
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0;
        while(i <= c.length - s.length()){
            if(equalsString(c, i, s)){
                res.add(i, s.length() - 1);
                i += s.length();
            } else {
                i ++;
            }
        }
        return res;
    }

    /**
     * String Shuffle
     * {N1, N2, N3, N4... N2k} -> {N1, Nk + 1, N2, Nk+2...Nk, N2k}
     * {N1, N2, N3, N4... N2k, N2k + 1} -> {N1, Nk + 1, N2, Nk+2...Nk, N2k, N2k + 1}
     * try to do it in-place
     */
    public int[] reOrder(int[] array){
        if((array.length & 1) == 0){
            reorder(array, 0, array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorder(int[] array, int left, int right){
        int len = right - left + 1;
        if(len <= 2){
            return;
        }
        int mid = left + len / 2;
        int lmid = left + len /4;
        int rmid = left + 3*len /4;
        reverse(array, lmid, mid - 1);
        reverse(array, mid, rmid);
        reverse(array, lmid, right - 1);;
        reorder(array, left, left + (lmid - left) * 2 - 1);
        reorder(array, left + (lmid - left) * 2, right);
    }

    private void reverse(int[] array, int left, int right){
        while(left < right){
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left ++;
            right --;
        }
    }


}
