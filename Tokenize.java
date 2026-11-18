package Language;

import java.util.Stack;

public class Tokenize {
    public static void main(String[] args){
        
    }

    // outputs 1 dimensional String array. Each array element is a line of code
    // -> output is an array of lines of code
    public static String[] splitByColon3(String input){
        
        String[] tokens = input.split("\\s*:3\\s*");
        return tokens;
    }

    // outputs 2 dimensional String array. Each element of the first dimension is a line of code
    // each element of the second dimension is a token in that line of code
    // -> output is an array of tokens for each line of code
    public static String[][] splitBySpace(String[] input){
        String[][] tokens = new String[input.length][];
        for(int i = 0; i < input.length; i++){
            tokens[i] = input[i].trim().split("\\s+");
        }
        return tokens;
    }

    // outputs 2 dimensional String array. Each element of the first dimension is a line of code
    // each element in the second dimension is a token in that line of code, with arithmetic operators converted to function calls
    // -> output is an array of tokens for each line of code
    public static String[][] arithmeticToFunction(String[][] input){
        String[][] tokens = new String[input.length][];
        for(int i = 0; i < input.length; i++){
            tokens[i] = new String[input[i].length];
            for(int j = 0; j < input[i].length; j++){
                String token = input[i][j];
                switch(token){
                    case "+":
                        tokens[i][j] = "ADD(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case "-":
                        tokens[i][j] = "SUB(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case "*":
                        tokens[i][j] = "MUL(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case "/":
                        tokens[i][j] = "DIV(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case "%":
                        tokens[i][j] = "MOD(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case "<":
                        tokens[i][j] = "LT(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case ">":
                        tokens[i][j] = "GT(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    case "==":
                        tokens[i][j] = "EQ(" + tokens[i][j - 2] + "," + tokens[i][j + 1] + ")";
                        break;
                    default:
                        tokens[i][j] = token;
                }
            }
        }
        return tokens;
    }

    // ---------------------------
    //        post processing
    // ---------------------------

    // outputs 3 dimensional String array. Each element of the first dimension is a line of code
    // each element of the second dimension is a function, third dimesion is the parameters of that function
    // -> output is an array of functions with parameters for each line of code
    public static String[][][] findFunctions(String[][] input){
        String[][][] tokens = new String[input.length][][];
        for(int i = 0; i < input.length; i++){
            tokens[i] = new String[input[i].length][];
            for(int j = 0; j < input[i].length; j++){
                String[] functionAndParams = input[i][j].split("\\(");
                String functionName = functionAndParams[0];
                String[] params = functionAndParams[1].replace(")", "").split(",");
                tokens[i][j] = new String[params.length + 1];
                tokens[i][j][0] = functionName;
                for(int k = 0; k < params.length; k++){
                    tokens[i][j][k + 1] = params[k].trim();
                }
            }
        }
        return tokens;
    }

    public static Stack<Integer> toOPCodes(String[][][] input){
        Stack<Integer> opCodes = new Stack<>();
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[i].length; j++){
                String functionName = input[i][j][0];
                switch(functionName){
                    case "ADD":
                        opCodes.push(1);
                        break;
                    case "SUB":
                        opCodes.push(2);
                        break;
                    case "MUL":
                        opCodes.push(3);
                        break;
                    case "DIV":
                        opCodes.push(4);
                        break;
                    case "MOD":
                        opCodes.push(5);
                        break;
                    case "LT":
                        opCodes.push(6);
                        break;
                    case "GT":
                        opCodes.push(7);
                        break;
                    case "EQ":
                        opCodes.push(13);
                        break;
                    default:
                        System.out.println("Unknown function: " + functionName);
                }
                for(int k = 1; k < input[i][j].length; k++){
                    opCodes.push(Integer.parseInt(input[i][j][k]));
                }
            }
        }
        return opCodes;
    }
}
