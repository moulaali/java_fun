/**
 * Validate Paranthesis
 * 
 * <P>[{()}] : Valid
 */
import java.util.*;

class ValidateParathesis {
 
    public static void main(String[] args)  {
        String input = "[{()}]{";
        Stack<Character> stack = new Stack<Character>();
        
        Map<Character, Character> match = new HashMap<>();
        match.put('{', '}');
        match.put('(', ')');
        match.put('[', ']');
        
        boolean valid = true;
        

        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                  stack.push(c);
              } else if (c == '}' || c == ']' || c == ')') {
                  if (!match.get(stack.pop()).equals(c)) {
                    valid = false;
                  }
              } else {
                System.out.println("Invalid char found " + c);   
                valid = false;
              }
        }
        
        if (!stack.isEmpty()) {
            System.out.println("Stack is not empty " + stack.pop());
            valid = false;
        }
        System.out.println("valid: " + valid);
    }
}
