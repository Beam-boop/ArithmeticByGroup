package Util;
import java.util.Stack;
/**
 * 生成逆波兰表达式
 * @author : [86135]
 * @version : [v1.0]
 */

public class ReversePolishUtil {
    public static String ReversePolishNotation(String str) throws Exception {
        Stack<Character> operators = new Stack<>(); //运算符
        Stack<String> output = new Stack(); //输出结果
        String[] strings = str.split(" ");

//        char[] chars = str.toCharArray();
        int pre = 0;
        boolean digital; //是否为数字（只要不是运算符，都是数字），用于截取字符串
        int len = strings.length;
        int bracket = 0; // 左括号的数量

        for (int i = 0; i < len; ) {
            pre = i;
            digital = false;
            //截取数字
            while (i < len && !Operator.isOperator(strings[i])) {
                i++;
                digital = true;
            }

            if (digital) {
                output.push(strings[i-1]);
            } else {
                String o = strings[i++]; //运算符
                if (o.equals("(")) {
                    bracket++;
                }
                if (bracket > 0) {
                    if (o.equals(")")) {
                        while (!operators.empty()) {
                            char top = operators.pop();
                            if (top == '(') {
                                break;
                            }
                            output.push(String.valueOf(top));
                        }
                        bracket--;
                    } else {
                        //如果栈顶为 ( ，则直接添加，不顾其优先级
                        //如果之前有 ( ，但是 ( 不在栈顶，则需判断其优先级，如果优先级比栈顶的低，则依次出栈
                        while (!operators.empty() && operators.peek() != '(' && Operator.cmp(o, String.valueOf(operators.peek())) <= 0) {
                            output.push(String.valueOf(operators.pop()));
                        }
                        operators.push(o.charAt(0));
                    }
                } else {
                    while (!operators.empty() && Operator.cmp(o, String.valueOf(operators.peek())) <= 0) {
                        output.push(String.valueOf(operators.pop()));
                    }
                    operators.push(o.charAt(0));
                }
            }

        }
        //遍历结束，将运算符栈全部压入output
        while (!operators.empty()) {
            output.push(String.valueOf(operators.pop()));
        }
        //计算结果
        String result = CalculateUtil.doCalculation(output);
        //对最终结果进行规格化
        return FormatUtil.finalResult(result);

//        System.out.println(output.pop());
    }
}

enum Operator {
    ADD("+", 1), SUBTRACT("-", 1),
    MULTIPLY("×", 2), DIVIDE("÷", 2),
    LEFT_BRACKET("(", 3), RIGHT_BRACKET(")", 3); //括号优先级最高
    String value;
    int priority;

    Operator(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    /**
     * 比较两个符号的优先级
     * @param s1
     * @param s2
     * @return c1的优先级是否比c2的高，高则返回正数，等于返回0，小于返回负数
     */
    public static int cmp(String s1, String s2) {
        int p1 = 0;
        int p2 = 0;
        for (Operator o : Operator.values()) {
            if (o.value.equals(s1)) {
                p1 = o.priority;
            }
            if (o.value.equals(s2)) {
                p2 = o.priority;
            }
        }
        return p1 - p2;
    }

    /**
     * 枚举出来的才视为运算符，用于扩展
     * @param s
     * @return
     */
    public static boolean isOperator(String s) {
        for (Operator o : Operator.values()) {
            if (o.value.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
