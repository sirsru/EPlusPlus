package Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Tokenize {
    private static final Map<String, Integer> INFIX = new HashMap<>();
    private static final Map<String, Integer> WORDS = new HashMap<>();

    static {
        INFIX.put("+", 1);  INFIX.put("-", 2);  INFIX.put("*", 3);
        INFIX.put("/", 4);  INFIX.put("%", 5);  INFIX.put("<", 6);
        INFIX.put(">", 7);  INFIX.put("==", 13);

        WORDS.put("ISZERO", 8);  WORDS.put("AND", 9);   WORDS.put("OR", 10);
        WORDS.put("XOR", 11);    WORDS.put("NOT", 12);  WORDS.put("PUSH0", 16);
        WORDS.put("MLOAD", 18);  WORDS.put("MSTORE", 19);
        WORDS.put("SWAP1", 22);  WORDS.put("SWAP2", 23);
        WORDS.put("DUP1", 24);   WORDS.put("PRNT", 25);
    }

    private static final Map<String, Integer> variables = new HashMap<>();
    private static int nextAddress = 0;


    
    private static String preprocess(String src) {
        StringBuilder out = new StringBuilder();
        for (String line : src.split("\n", -1)) {
            int cut = line.indexOf("//");
            if (cut >= 0) line = line.substring(0, cut);
            out.append(line).append('\n');
        }
        return out.toString();
    }

    public static String[] splitByColon3(String input) {
        return input.split("\\s*:3\\s*");
    }

    public static String[][] splitBySpace(String[] input) {
        String[][] tokens = new String[input.length][];
        for (int i = 0; i < input.length; i++) {
            tokens[i] = input[i].trim().split("\\s+");
        }
        return tokens;
    }

    public static List<Integer> compile(String[][] lines) {
        List<Integer> code = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i];
            for (int j = 0; j < line.length; j++) {
                String t = line[j];
                Integer infix = INFIX.get(t);
                if (infix != null) {
                    if (j == 0 || j == line.length - 1)
                        throw new IllegalArgumentException(
                            "Line " + i + ": operator '" + t + "' is missing an operand :(");
                    emitValue(line[j - 1], code);
                    emitValue(line[j + 1], code);
                    code.add(infix);
                    j++;
                } else if (WORDS.containsKey(t)) {
                    code.add(WORDS.get(t));
                } else {
                    throw new IllegalArgumentException("Line " + i + ": unknown token '" + t + "' :(");
                }
            }
        }
        return code;
    }

    private static void emitValue(String token, List<Integer> code) {
        try {
            code.add(17); // PUSH
            code.add(Integer.parseInt(token));
        } catch (NumberFormatException e) {
            int addr = variables.computeIfAbsent(token, k -> nextAddress++);
            code.add(17);
            code.add(addr);
            code.add(18); // MLOAD
        }
    }

    public static Stack<Integer> load(List<Integer> code) {
        Stack<Integer> program = new Stack<>();
        for (int i = code.size() - 1; i >= 0; i--) program.push(code.get(i));
        return program;
    }
}
