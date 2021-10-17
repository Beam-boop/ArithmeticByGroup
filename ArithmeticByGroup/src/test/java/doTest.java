/**
 *
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/17 11:15]
 */
import Util.ExamineUtil;
import Util.ReversePolishUtil;
import org.testng.annotations.Test;

import java.util.Arrays;

public class doTest{
    //
    @Test
    public void testGrade() throws Exception {
//        System.out.println(Arrays.toString(ExamineUtil.calculateAnswer("src\\test\\exercisefile.txt")));

        ExamineUtil.compareDifferent("src\\test\\exercisefile.txt","src\\test\\answerfile.txt","src\\Grade.txt");
    }

    //测试除数为zero
    @Test
    public  void testZero(){
        String str = "3 ÷ 0";
        try {
            ReversePolishUtil.ReversePolishNotation(str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("除数为0，不符合要求!");
        }
    }
    //测试结果为负
    @Test
    public void testAnsNeg(){
        String str = "3 - 4";
        try {
            ReversePolishUtil.ReversePolishNotation(str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("测试结果为负，不符合要求！");
        }
    }

    //表达式是重复的
    @Test
    public void testExpressionRepeat(){
        String str1 = "1 + 2 + 3";
        String str2 = "( 1 + 2 ) + 3";
    }
}
