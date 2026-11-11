package Language;

import java.util.Stack;

public class EVMVirtualMachine {
    public static int memsize = 1024;
    public static int[] memory = new int[memsize];

    public static Stack<Integer> programStack = new Stack<>();

    public static int programCounter = 0;
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
    
    public static void ISZERO(){
        int value1 = programStack.pop();
	programStack.push((value1 == 0) ? 1 : 0);
    }

    public static void AND(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push((value1 != 0 && value2 != 0) ? 1 : 0);
    }

    public static void OR(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push((value1 != 0 || value2 != 0) ? 1 : 0);
    }

    public static void XOR(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1^value2);
    }

    public static void NOT(){
        int value1 = programStack.pop();
        programStack.push(~value1);
    }

    public static void EQ(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push((value1==value2) ? 1 : 0);
    }

    public static void SHL(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 << value2);
    }

    public static void SHR(){
        int value1 = programStack.pop();
        int value2 = programStack.pop();
        programStack.push(value1 >> value2);
    }

    public static void PUSH0(){
        programStack.push(0);
    }

    public static void PUSH(int value){
        programStack.push(value);
    }

    public static void MLOAD(int address){
        programStack.push(memory[address]);
    }

    public static void MSTORE(int address){
        memory[address] = programStack.pop();
    }

    public static void JUMP(){
        programCounter = programStack.pop();
    }

    public static void JUMPI(){
        int destination = programStack.pop();
        boolean jump = (programStack.pop() == 0);
        if(jump) programCounter = destination;
    }

    public static void execute(){
        while(programStack.size() > 0){
            int opcode = programStack.pop();
            switch(opcode){
                case 0: //STOP
                    return;
                case 1: //ADD
                    ADD();
                    break;
                case 2: //SUB
                    SUB();
                    break;
                case 3: //MUL
                    MUL();
                    break;
                case 4: //DIV
                    DIV();
                    break;
                case 5: //MOD
                    MOD();
                    break;
                case 6: //LT
                    LT();
                    break;
                case 7: //GT
                    GT();
                    break;
                case 8: //ISZERO
                    ISZERO();
                    break;
                case 9: //AND
                    AND();
                    break;
                case 10: //OR
                    OR();
                    break;
                case 11: //XOR
                    XOR();
                    break;
                case 12: //NOT
                    NOT();
                    break;
                case 13: //EQ
                    EQ();
                    break;
                case 14: //SHL
                    SHL();
                    break;
                case 15: //SHR
                    SHR();
                    break;
                default:
                    System.out.println("Invalid opcode");
            }
        }
    }

    public static void main(String[] args) {
        
    }
}
