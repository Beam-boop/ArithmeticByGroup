package Util;

import java.util.*;

/**
 *
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/16 10:56]
 */

public class CalculateUtil {
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
        boolean flag = false;//是否重复
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
    }
}
