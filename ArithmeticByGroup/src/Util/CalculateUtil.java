package Util;

import java.util.*;

/**
 *
 * @author : [86135]
 * @version : [v1.0]
 */

public class CalculateUtil {

    //设置运算符的优先级
    public static Map<String, Integer> map = new HashMap<String, Integer>() {
        //数字越大，优先级越高
        {
            put("(", 0);
            put("+", 1);
            put("-", 1);
            put("×", 2);
            put("÷", 2);
            put(")", 3);
            put("=", 4);
        }
    };


    /**
     * 将最终结果进行格式化
     * @param num 需要格式化的数据
     * @return 格式化后的结果
     * @throws Exception
     */
    public static String finalResult(String num) throws Exception {
        String[] nums = new String[2];
        int mole, deno;
        nums = FractionUtil.change(num);
        mole = Integer.parseInt(nums[0]);
        deno = Integer.parseInt(nums[1]);
        String finalResult = FormatUtil.Format(mole, deno);
        if(finalResult.contains("-")) {//运算结果为负数，不给过
            throw new Exception();
        }else {
            return finalResult;
        }

    }


    /**
     * 计算栈内结果，如果有(,需要将括号拿走
     * @param Stack1
     * @throws Exception
     */
    public static String doCalculation(Stack<String> Stack1) throws Exception {
        List<String> numList = new ArrayList<>();
        Stack<String> Stack2 = new Stack();
        String operator = "";
        String num1 = "";
        String num2 = "";
        String temp = "";
        //将stack1放入stack2
        for (int i = 0; i < Stack1.size();) {
            Stack2.push(Stack1.pop());
        }

        for (int i = 0; i < Stack2.size();) {
            temp = Stack2.pop();
            if(!Operator.isOperator(temp)){
                numList.add(temp);
            }else{
                operator = temp;
                num1 = numList.get(numList.size() - 2);
                num2 = numList.get(numList.size() - 1);
                numList.remove(numList.size() - 2);
                numList.remove(numList.size() - 1);
                String result = FractionUtil.result(operator,num1,num2);
                if(result.contains("-")){
                    throw new Exception();
                }else{
                    Stack2.push(result);
                }
            }
        }
        return temp;

//        //将两个数值及运算符出栈进行计算
//        String nowOpera = operaStack.pop();
//        String nowNum_2 = numStack.pop();
//        String nowNum_1 = numStack.pop();
//
//        String result = FractionUtil.result(nowOpera, nowNum_1, nowNum_2);
//        //根据题目要求，结果不可能为负
//        if(result.contains("-")) {	//结果为负
//            throw new Exception();
//        }else {
//            numStack.push(result);
//        }
//        if(flag) {
//            if("(".equals(operaStack.peek())) {//栈顶为（
//                //将‘（’取走
//                operaStack.pop();
//            }else {
//                doCalcul(operaStack, numStack, flag);//栈顶不为“（”
//            }
//        }else {
//            //如果还有运算符，就进行递归调用
//            if(!operaStack.empty()) {
//                doCalcul(operaStack, numStack, flag);
//            }
//        }

    }

    /**
     * 比较运算级，如果没有入栈，就直接计算栈内结果
     * @param operaStack 运算符栈
     * @param numStack 数据栈
     * @param operator 当前运算符
     * @throws Exception
     */
    public static void comparePriority(Stack<String> operaStack, Stack<String> numStack, String operator) throws Exception{
        String topOpera = operaStack.peek();
        int priority = map.get(operator) - map.get(topOpera);//优先级判断
        //如果当前运算符优先级比栈顶的优先级高，则加入栈
        if (priority > 0) {
            operaStack.push(operator);
        } else {//如果低，先进行运算
            String nowOpera = operaStack.pop();
            String nowNum_2 = numStack.pop();
            String nowNum_1 = numStack.pop();
            String result = FractionUtil.result(nowOpera, nowNum_1, nowNum_2);

            numStack.push(result);
            if (operaStack.empty()) {//运算符栈为空
                operaStack.push(operator);
            } else {
                comparePriority(operaStack, numStack, operator);
            }
        }

    }
}

