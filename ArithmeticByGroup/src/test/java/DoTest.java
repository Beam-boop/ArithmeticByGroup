/**
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/17 11:15]
 */

import Util.ExamineUtil;
import Util.FormatUtil;
import Util.ReversePolishUtil;
import org.testng.annotations.Test;


public class DoTest {



    //测试除数为zero
    @Test
    public void testZero() {
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
    public void testAnsNeg() {
        String str = "3 - 4";
        try {
            ReversePolishUtil.ReversePolishNotation(str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("测试结果为负，不符合要求！");
        }
    }

    //计算部分测试
    //测试带括号加法
    @Test
    public void testAdd() {
        String str = "( 3 + 2 ) + 1";
        System.out.println(ReversePolishUtil.ReversePolishNotation(str));

    }

    //测试带括号减法
    @Test
    public void testMinus() {
        String str = "( 3 - 2 ) - 1";
        System.out.println(ReversePolishUtil.ReversePolishNotation(str));


    }

    //测试带括号乘法
    @Test
    public void testMultiply() {
        String str = "( 3 × 2 ) × 1";
        System.out.println(ReversePolishUtil.ReversePolishNotation(str));

    }

    //测试带括号除法
    @Test
    public void testInto() {
        String str = "( 3 ÷ 2 ) ÷ 1";
        System.out.println(ReversePolishUtil.ReversePolishNotation(str));

    }

    //测试复杂的四则运算
    @Test
    public void testFour(){
        String str="5 × ( 5 + 10 )";
        System.out.println(ReversePolishUtil.ReversePolishNotation(str));

    }

    //测试带分数的运算
    @Test
    public void testF(){
        String str="1'1/4 × ( 4 ÷ ( 1/9 ÷ 5/9 ) )";
        System.out.println(ReversePolishUtil.ReversePolishNotation(str));
    }

    //测试分数的约分
    @Test
    public void testReduce() {
        System.out.println(FormatUtil.format(-27, 243));
    }


    //测试判断答案对错
    @Test
    public void testExamine() {
        ExamineUtil.compareDifferent("src\\test\\exercises.txt", "src\\test\\answers.txt", "src\\test\\Grade.txt");

    }


   //测试用户输入错误的n与r参数



}


