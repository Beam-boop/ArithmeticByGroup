package Util;

import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

/**
 * 生成表达式，并进行计算
 * @author : [86135]
 * @version : [v1.0]
 */

public class GenerateUtil {

    public static String[] createExpression(int range, int num) {
        HashSet<String> strHashSet = new HashSet<>();

        String operator = "+-×÷";//定义四则运算符号，+-*/
        Vector<String> expression = new Vector<>();//表达式
        Vector<String> result = new Vector<>();




        for (int i = 0; i < num; i++) {
            try {
                //先进行一次一元运算式生成
                int opNum = getRandomNum(1,3);
                char op1 = operator.charAt(getRandomNum(0, 3));
                char op2 = operator.charAt(getRandomNum(0, 3));

                String left;
                String right;

                String valueNow;

                left = fraction(range);
//                left = String.valueOf(getRandomNum(0,1));

                right = fraction(range);
//                right = String.valueOf(getRandomNum(0,1));

                StringBuilder res = new StringBuilder("( " + left + " " + op1 + " " + right + " )");
                valueNow = ReversePolishUtil.ReversePolishNotation(res.toString());
                if (op1 == '-' && valueNow.contains("-")) { //交换左右
                    valueNow = valueNow.substring(1);
                    res = new StringBuilder("( " + right + " " + op1 + " " + left + " )");
                } else if ("+×".contains(String.valueOf(op1)) && //去重
                        !ReversePolishUtil.ReversePolishNotation(
                                "( " + left + " - " + right + " )").contains("-")) {
                    res = new StringBuilder("( " + right + " " + op1 + " " + left + " )");
                }

                for (int j = 0; j < opNum-1; j++) {
                    if (getRandomNum(0, 1) == 0) {
                        left = fraction(range);
//                        left = String.valueOf(getRandomNum(0,1));
                        right = res.toString();

                        if ("×÷".contains(String.valueOf(op1)) && "+-".contains(String.valueOf(op2))) { //若原符号是乘除，新符号是加减，不需要括号 如5+(3*4)应去掉括号
                            res = new StringBuilder("( " + left + " " + op2 + " " + right.substring(1, res.length() - 1) + " )");
                        } else
                            res = new StringBuilder("( " + left + " " + op2 + " " + right + " )"); //其他情况需要括号

                    } else {
                        left = res.toString();
                        right = fraction(range);
//                        right = String.valueOf(getRandomNum(0,1));

                        //判断同级
                        if (("+-".contains(String.valueOf(op1)) && "+-".contains(String.valueOf(op2))) || //若新符号与原符号同级，去掉括号如 (1+2) - 3 应去掉括号
                                "×÷".contains(String.valueOf(op1))) { //若原符号是乘除，不需要括号，如 (2*2)+1 应去掉括号
                            res = new StringBuilder("( " + left.substring(1, res.length() - 1) + " " + op2 + " " + right + " )");
                        } else {
                            res = new StringBuilder("( " + left + " " + op2 + " " + right + " )");
                        }
                    }

                    valueNow = ReversePolishUtil.ReversePolishNotation(res.toString());
                    if (op2 == '-' && valueNow.contains("-")) { //交换左右
                        valueNow = valueNow.substring(1);
                        res = new StringBuilder("( " + right + " " + op2 + " " + left + " )");
                    } else if ("+×".contains(String.valueOf(op2)) && //去重
                            !ReversePolishUtil.ReversePolishNotation(
                                    "( " + left + " - " + right + " )").contains("-")) {
                        res = new StringBuilder("( " + right + " " + op2 + " " + left + " )");
                    }
                    op1 = op2;
                    op2 = operator.charAt(getRandomNum(0, 3));
                }

                valueNow = FormatUtil.finalResult(valueNow);
                String str = res.substring(1, res.toString().length()-1);

                if (!strHashSet.contains(str)) {
                    strHashSet.add(str);
                    result.add(valueNow);
                    expression.add(str + " = " + valueNow);

                } else i--;
            } catch (NumberFormatException e) {
                i--;
            }


        }

        return expression.toArray(new String[0]);
    }

    public static String fraction(int range) {
        //molecular是分子，denominator是分母
        int molecular, denominator;
        molecular = getRandomNum(0, range);//分子
        denominator = getRandomNum(1, range);//分母
        return FormatUtil.format(molecular, denominator);
    }

    public static String[] toAnswer(String[] expression) {
        String[] answer = new String[expression.length];

        for (int j = 0; j < expression.length; j++) {
            answer[j] = expression[j].split("=")[1].substring(1);
        }
        return answer;
    }

    public static String[] toExercise(String[] expression) {
        String[] Exercise = new String[expression.length];

        for (int j = 0; j < expression.length; j++) {
            Exercise[j] = expression[j].split("=")[0] +"=";

        }
        return Exercise;
    }

    public static int getRandomNum(int min, int max) {
        return min == 0 ? new Random().nextInt(max + 1) : new Random().nextInt(min + max - 1) + min;
    }

}
