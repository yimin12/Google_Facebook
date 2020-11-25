package Contest.TicTok;

import java.util.Deque;
import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/22 17:37
 *   @Description :
 *      Return the result of evaluating a given expression
 */
public class ValueExpression {

    // There 4 different type of operation
    final int NOT = 5, AND = 6, OR = 7, XOR = 8;

   public boolean evaluate(String input){
       // Assumption: return true if invalid
       if(input == null || input.length() < 2){
           return true;
       }
       Deque<Integer> stack = new LinkedList<>();
       char[] array = input.toCharArray();
       for(int i = 0; i < array.length; i++){
           if(array[i] == '('){
               stack.offerFirst(-1);
           } else if(array[i] == ')'){
               compress(stack, i, array);
           } else if(array[i] != ','){
               i = addValue(stack, array, i) - 1;
           }
       }
       return stack.peekFirst() == 1 ? true:false;
   }

   private void compress(Deque<Integer> stack, int index, char[] array){
        int operator = AND;
        if(stack.peekFirst() >= 5){
            operator = stack.pollFirst();
        }
        int cur = stack.pollFirst();
        while(stack.peekFirst() != -1){
            int value = stack.pollFirst();
            cur = calculate(cur, value, operator);
        }
        stack.pollFirst();
        stack.offerFirst(cur);
   }

   private int calculate(int i, int j, int operator){
       switch (operator){
           case 5:
               return ~(i & j);
           case 6:
               return (i & j);
           case 7:
               return (i | j);
           default:
               return (i ^ j);
       }
   }

   private int addValue(Deque<Integer> stack, char[] array, int index){
       char c = array[index];
       switch (c){
           case 'F':
               stack.offerFirst(0);
               return index + 5;
           case 'N':
               stack.offerFirst(NOT);
               return index + 3;
           case 'A':
               stack.offerFirst(AND);
               return index + 3;
           case 'O':
               stack.offerFirst(OR);
               return index + 2;
           case 'X':
               stack.offerFirst(XOR);
               return index + 3;
           default:
               stack.offerFirst(1);
               return index + 4;
       }
   }

    public static void main(String[] args) {
        ValueExpression solution = new ValueExpression();
        boolean res = solution.evaluate("((True,(False,NOT),True,AND),(True,NOT),XOR)");
        System.out.println(res);
    }
}
