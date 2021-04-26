package Contest.NineChapter.HighFreq.String;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description
 * Given an expression s contains numbers, letters and brackets. Number represents the number of repetitions inside the brackets(can be a string or another expression)．Please expand expression to be a string.
 *
 * Numbers can only appear in front of “[]”.
 *
 * Example
 * Example1
 *
 * Input: S = abc3[a]
 * Output: "abcaaa"
 * Example2
 *
 * Input: S = 3[2[ad]3[pf]]xyz
 * Output: "adadpfpfpfadadpfpfpfadadpfpfpfxyz"
 */
public class DecodeString {

    public String expressionExpand(String s){
        Deque<Object> stack = new LinkedList<>();
        int number = 0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                number = number * 10 + c - '0';
            } else if(c == '['){
                stack.push(Integer.valueOf(number));
                number = 0;
            } else if(c == ']'){
                String newStr = popStack(stack);
                Integer count = (Integer) stack.pop();
                for(int i = 0; i < count; i ++){
                    stack.push(newStr);
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }
        return popStack(stack);
    }

    private String popStack(Deque<Object> stack){
        Deque<String> buffer = new LinkedList<>();
        while(!stack.isEmpty() && (stack.peek() instanceof String)){
            buffer.push((String) stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while(!buffer.isEmpty()){
            sb.append(buffer.pop());
        }
        return sb.toString();
    }


}
