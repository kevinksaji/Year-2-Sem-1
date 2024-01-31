package dsaj.stacks;

import java.util.Scanner;
import net.datastructures.Stack;
import net.datastructures.LinkedStack;

import javax.lang.model.util.ElementScanner6;

public class Evaluate {
  public static void main(String[] args) {

    String example = "( 1 - ( (  2 / 10 ) * 24 ) )";
    Stack<String> ops = new LinkedStack<String>();
    Stack<Double> vals = new LinkedStack<Double>();

    Scanner scanner = new Scanner(example);
    while(scanner.hasNext()) {
      String s = scanner.next();
      if (s.equals("("));
      else if (s.equals("+") || s.equals ("-") || s.equals ("*") || s.equals ("/"))
        ops.push(s);
      else if (s.equals(")")) {
        String op = ops.pop();
        double val = vals.pop();
        if (op.equals("+"))        vals.push(vals.pop() + val);
        else if (op.equals("-"))   vals.push(vals.pop() - val);
        else if (op.equals("*"))   vals.push(vals.pop() * val);
        else if (op.equals("/"))   vals.push(vals.pop() / val);
      }
      else
        vals.push(Double.parseDouble(s));
      
    }
    System.out.println(vals.toString());
    
    scanner.close();
  }
}
