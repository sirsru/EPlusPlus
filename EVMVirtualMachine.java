package Language;

import java.util.Stack;

public class EVMVirtualMachine {
    public static final int memSize = 1024;
    public static int[] memory = new int[memSize];

    public static Stack<Integer> programStack = new Stack<>();
    public static Stack<Boolean> isOPCODE = new Stack<>();
    public static Stack<Integer> execStack = new Stack<>();
    
    public static void ADD(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 + value2);
        isOPCODE.push(false);
    }

    public static void SUB(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 - value2);
        isOPCODE.push(false);
    }

    public static void MUL(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 * value2);
        isOPCODE.push(false);
    }

    public static void DIV(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 / value2);
        isOPCODE.push(false);
    }

    public static void MOD(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 % value2);
        isOPCODE.push(false);
    }
    
    public static void LT(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push((value1 < value2) ? 1 : 0);
        isOPCODE.push(false);
    }
    
    public static void GT(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push((value1 > value2) ? 1 : 0);
        isOPCODE.push(false);
    }
    
    public static void ISZERO(){
        int value1 = execStack.pop();
        isOPCODE.pop();

	    execStack.push((value1 == 0) ? 1 : 0);
        isOPCODE.push(false);
    }

    public static void AND(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push((value1 != 0 && value2 != 0) ? 1 : 0);
        isOPCODE.push(false);
    }

    public static void OR(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();
        
        execStack.push((value1 != 0 || value2 != 0) ? 1 : 0);
        isOPCODE.push(false);
    }

    public static void XOR(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1^value2);
        isOPCODE.push(false);
    }

    public static void NOT(){
        int value1 = execStack.pop();
        isOPCODE.pop();

        execStack.push(~value1);
        isOPCODE.push(false);
    }

    public static void EQ(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push((value1==value2) ? 1 : 0);
        isOPCODE.push(false);
    }

    public static void SHL(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 << value2);
        isOPCODE.push(false);
    }

    public static void SHR(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        isOPCODE.pop();
        isOPCODE.pop();

        execStack.push(value1 >> value2);
        isOPCODE.push(false);
    }

    public static void PUSH0(){
        execStack.push(0);
        isOPCODE.push(false);
    }


    //FOR DEBUG ONLY
    public static void PUSH(int value){
        execStack.push(value);
        isOPCODE.push(false);
    }

    public static void MLOAD(){
        int address = execStack.pop();
        execStack.push(memory[address]);
    }

    public static void MSTORE(){
        int address = execStack.pop();
        memory[address] = execStack.pop();
    }

    public static void JUMP(){
        int programIndex = execStack.pop();

        
    }

    public static void JUMPI(){
        int destination = execStack.pop();
        boolean jump = (execStack.pop() == 0);
    }

    public static void SWAP1(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        Boolean isOP1 = isOPCODE.pop();
        Boolean isOP2 = isOPCODE.pop();

        execStack.push(value1);
        execStack.push(value2);
        isOPCODE.push(isOP1);
        isOPCODE.push(isOP2);
    }

    public static void SWAP2(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        int value3 = execStack.pop();
        Boolean isOP1 = isOPCODE.pop();
        Boolean isOP2 = isOPCODE.pop();
        Boolean isOP3 = isOPCODE.pop();

        execStack.push(value3);
        execStack.push(value2);
        execStack.push(value1);
        isOPCODE.push(isOP3);
        isOPCODE.push(isOP2);
        isOPCODE.push(isOP1);
    }

    public static void SWAP3(){
        int value1 = execStack.pop();
        int value2 = execStack.pop();
        int value3 = execStack.pop();
        int value4 = execStack.pop();
        Boolean isOP1 = isOPCODE.pop();
        Boolean isOP2 = isOPCODE.pop();
        Boolean isOP3 = isOPCODE.pop();
        Boolean isOP4 = isOPCODE.pop();

        execStack.push(value4);
        execStack.push(value2);
        execStack.push(value3);
        execStack.push(value1);
        isOPCODE.push(isOP4);
        isOPCODE.push(isOP2);
        isOPCODE.push(isOP3);
        isOPCODE.push(isOP1);
    }

    public static void DUP1(){
        int value1 = execStack.pop();
        Boolean isOP1 = isOPCODE.pop();

        execStack.push(value1);
        execStack.push(value1);
        isOPCODE.push(isOP1);
        isOPCODE.push(isOP1);
    }

    public static void DUP2(){
        int value1 = execStack.pop();
        Boolean isOP1 = isOPCODE.pop();

        execStack.push(value1);
        execStack.push(value1);
        execStack.push(value1);
        isOPCODE.push(isOP1);
        isOPCODE.push(isOP1);
        isOPCODE.push(isOP1);
    }

    public static void DUP3(){
        int value1 = execStack.pop();
        Boolean isOP1 = isOPCODE.pop();

        execStack.push(value1);
        execStack.push(value1);
        execStack.push(value1);
        execStack.push(value1);
        isOPCODE.push(isOP1);
        isOPCODE.push(isOP1);
        isOPCODE.push(isOP1);
    }

    public static void PRNT(){
        System.out.println(execStack.pop());
    }

    public static void CASTNOT(){
        Boolean isOP1 = isOPCODE.pop();

        isOPCODE.push(!isOP1);
    }

    public static void CASTINT(){
        Boolean isOP1 = isOPCODE.pop();

        isOPCODE.push(false);
    }

    public static void CASTOPCODE(){
        Boolean isOP1 = isOPCODE.pop();

        isOPCODE.push(true);
    }

    public static void execute() {
        while (!execStack.isEmpty()) {
            int opcode = execStack.pop();
            switch (opcode) {
                case 0:  return;
                case 1:  ADD(); break;
                case 2:  SUB(); break;
                case 3:  MUL(); break;
                case 4:  DIV(); break;
                case 5:  MOD(); break;
                case 6:  LT(); break;
                case 7:  GT(); break;
                case 8:  ISZERO(); break;
                case 9:  AND(); break;
                case 10: OR(); break;
                case 11: XOR(); break;
                case 12: NOT(); break;
                case 13: EQ(); break;
                case 14: SHL(); break;
                case 15: SHR(); break;
                case 16: PUSH0(); break;
                case 17: { int v = execStack.pop(); PUSH(v); break; }
                case 18: { int a = execStack.pop(); MLOAD(); break; }
                case 19: { int a = execStack.pop(); MSTORE(); break; }
                case 20: JUMP(); break;
                case 21: JUMPI(); break;
                case 22: SWAP1(); break;
                case 23: SWAP2(); break;
                case 24: DUP1(); break;
                case 25: PRNT(); break;
                default: System.out.println("Invalid opcode: " + opcode);
            }
        }
    }

    public static void main(String[] args) {
        
    }
}