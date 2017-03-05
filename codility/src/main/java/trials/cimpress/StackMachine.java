package trials.cimpress;

import javax.management.OperationsException;
import java.util.Stack;

/**
 * Created by alex on 3/5/2017.
 */
public class StackMachine {

    public static final int MAX_VALUE = 0b111111111111;

    public static boolean check12bitOverflow(int v) {
        return v > MAX_VALUE;
    }

    private Stack<Integer> stack = new Stack<>();

    public int solution(String S) {

        try {

            for (int index = 0; index < S.length(); index++) {

                char ch = S.charAt(index);

                if (Character.isDigit(ch)) {
                    Integer val = Character.getNumericValue(ch);
                    stack.push(val);
                    continue;
                }

                if (ch == '+') {
                    sum();
                    continue;
                }

                if (ch == '*') {
                    multiply();
                    continue;
                }
            }
            checkMinimalStackDepth(1);

            return stack.pop();
        } catch (Exception e) {
            //log
            return -1;
        }
    }

    private void checkMinimalStackDepth(int minimalDepth) throws OperationsException {
        if (stack.size() < minimalDepth) {
            String message = "Current stack depth is not enought for perfoming the operation, at least " + minimalDepth + "elementes requered";
            throw new OperationsException(message);
        }
    }

    private void multiply() throws OperationsException {
        checkMinimalStackDepth(2);
        int a = stack.pop();
        int b = stack.pop();
        int result = Math.multiplyExact(a, b);
        if (check12bitOverflow(result)) {
            throw new ArithmeticException("12 bit variable overflow, cannot store value: " + result);
        } else {
            stack.push(result);
        }
    }

    private void sum() throws OperationsException {
        checkMinimalStackDepth(2);
        int a = stack.pop();
        int b = stack.pop();
        int result = Math.addExact(a, b);
        if (check12bitOverflow(result)) {
            throw new ArithmeticException("12 bit variable overflow, cannot store value: " + result);
        } else {
            stack.push(result);
        }
    }
}
