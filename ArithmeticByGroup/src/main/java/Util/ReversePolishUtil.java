package Util;

import java.util.Stack;

/**
 * 生成逆波兰表达式
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/16 10:59]
 */

public class ReversePolishUtil {
    public static String ReversePolishNotation(String str) throws NumberFormatException {
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
