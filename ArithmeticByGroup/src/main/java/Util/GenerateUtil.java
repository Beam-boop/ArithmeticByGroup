package Util;

/**
 * 生成表达式，并进行计算
 * @author : [86135]
 * @version : [v1.0]
 */

public class GenerateUtil {
    public static String[] createExpression(int range, int num) {
        String[] operator = {"+", "-", "×", "÷"};//定义四则运算符号，+-*/
        String[] expression = new String[num];//表达式
        String[] result = new String[num];
        //生成num个表达式，num是用户传入数据
        for (int i = 0; i < num; i++) {
            int operatornum = (int) (Math.random() * 3 + 1);//运算符号的个数
            String figure = null, operate = null;//figure是数字，operate是运算符号
            //添加右括号数
            int undo = 0;
            //左括号的个数
            int bracketnum = 0;
            //flag表示加括号后，括号中是否有数，防止直接添加运算符和数字
            boolean flag = true;
            try {
                while (operatornum > 0) {
                    //tag=0,表明生成左括号，tag=1，表示生成右括号
                    int tag = (int) (Math.random() * 2);
                    figure = fraction(range);//生成真分数
                    operate = operator[(int) (Math.random() * 4)];//随机选取一个运算符号
                    if (tag == 0 && bracketnum < operatornum - 1) {//加括号，括号个数必须比运算个数少一
                        if(expression[i] == null){
                            expression[i] = "( ";
                        }else{
                            expression[i] += "( ";
                        }

                        bracketnum++;
                        undo++;
                        flag = false;
                    } else if (tag == 1 && flag && undo > 0) {//1.随机生成右括号 2.前面后左括号，所以需要右括号 排除没有左括号，随机生成右括号，影响结果，所以undo必须为0
                        expression[i] += figure + " )" + " " + operate + " ";
                        operatornum--;
                        undo--;
                        flag = false;
                    } else {//如果不用加括号，就直接+数字和操作符
                        if(expression[i] == null){
                            expression[i] = figure +" "+ operate+" ";
                        }else{
                            expression[i] += figure +" "+ operate+" ";
                        }
                        operatornum--;//运算符号减一
                        flag = true;//我们已经把左括号内的数据加进去了
                    }
                }


                // 添加最后一个数
                if (undo == 0) {
                    figure = fraction(range);
                    expression[i] +=figure;
                } else if (undo == 1) {
                    //一开始有((或者(,到最后一个数字，减去一个(
                    if (expression[i].toString().startsWith("( ( ") || expression[i].toString().startsWith("( ") && expression[i].toString().contains(" )") == false) {
                        figure = fraction(range);
                        expression[i] = expression[i].substring(2) + figure;
                    } else {
                        figure = fraction(range);
                        expression[i] += figure + " )";
                    }
                } else if (undo == 2) {
                    //如果需要添加两个右括号，如果开始是((,可以将((去除
                    if (expression[i].startsWith("( ( ")) {
                        figure = fraction(range);
                        expression[i] = expression[i].substring(4) + figure;
                    }
                }
                //除数不能为0//还需要加一个去重复的步骤
                if (expression[i].contains("÷ 0")) {
                    expression[i] = "";
                    i--;
                    continue;
                }

                  result[i] = ReversePolishUtil.ReversePolishNotation(expression[i]);
                expression[i] += " = " + result[i];                              //expression+answer
            } catch (Exception e) {
                i--;
            }
        }
        return expression;
    }

    public static String fraction(int range) {
        //molecular是分子，denominator是分母
        int molecular, denominator;
        molecular = (int) (Math.random() * range);
        denominator = (int) (Math.random() * range + 1);
        return FormatUtil.Format(molecular, denominator);
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


}
