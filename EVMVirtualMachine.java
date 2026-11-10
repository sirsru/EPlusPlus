package Language;

import java.util.Stack;

public class EVMVirtualMachine {
    public static Stack<Integer> programStack = new Stack<>();
    //check for STOP in computation loop instead of function

    public static void ADD(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 + value2);
    }

    public static void SUB(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 - value2);
    }

    public static void MUL(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 * value2);
    }

    public static void DIV(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 / value2);
    }

    public static void MOD(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 % value2);
    }
    
    public static void LT(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push((value1 < value2) ? 1 : 0);
    }
    
    public static void GT(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push((value1 > value2) ? 1 : 0);
    }

    public static void main(String[] args) {
        
    }
}