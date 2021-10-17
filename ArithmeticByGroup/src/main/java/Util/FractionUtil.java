package Util;

/**
 * [describe the work of this class in one sentence]
 *
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/16 10:58]
 */

public class FractionUtil {
    /**
     * 计算结果
     * @param nowOpera 运算符
     * @param nowNum_1 第一个数
     * @param nowNum_2 第二个数
     * @return 运算结果
     */
    public static String result(String nowOpera, String nowNum_1, String nowNum_2)throws Exception{
        String result = null;
        //将分子和分母拆开
        int mole_1, deno_1, mole_2, deno_2;
        String[] value_1 = change(nowNum_1);
        mole_1 = Integer.parseInt(value_1[0]);
        deno_1 = Integer.parseInt(value_1[1]);
        String[] value_2 = change(nowNum_2);
        mole_2 = Integer.parseInt(value_2[0]);
        deno_2 = Integer.parseInt(value_2[1]);

        int flag = 0;

        switch (nowOpera) {
            case "+":
                result = add(mole_1, deno_1, mole_2, deno_2);
                break;
            case "-":
                result = subtraction(mole_1, deno_1, mole_2, deno_2);
                break;
            case "×":
                result = multiplication(mole_1, deno_1, mole_2, deno_2);
                break;
            case "÷":
                result = division(mole_1, deno_1, mole_2, deno_2);
                break;
            default:
                break;
        }
        return result;
    }


    public static String add(int a, int b, int c, int d) {
        int mole, deno;
        mole = a * d + c * b;//分子通分相加
        deno = b * d;//分母相乘
        return mole + "/" + deno;
    }

    public static String subtraction(int a, int b, int c, int d){
        int mole, deno;
        mole = a * d - c * b;//分子通分相减
        deno = b * d;//分母相乘
        return mole + "/" + deno;

    }

    public static String multiplication(int a, int b, int c, int d) {
        int mole, deno;
        mole = a * c;
        deno = b * d;
        return mole + "/" + deno;
    }

    public static String division(int a, int b, int c, int d) throws Exception{
        //含有/0，不符合要求
        if(a == 0 && b==0 && c==0&&d==0){
            throw new Exception();
        }else{
            int mole, deno;
            mole = a * d;
            deno = b * c;
            return mole + "/" + deno;
        }


    }

    /**
     * 将分数转换成a/b形式并将a、b添加进array数组中
     * @param num 分数
     * @return array 第一个array存储分子，第二个array存储分母
     */
    public static String[] change(String num){
        String [] array = new String [2];
        if(num.contains("/")) {//是分数
            if(num.contains("'")) {//带真分数
                String[] tarray = num.split("/|'");//根据’/‘或者’进行划分
                int[] sarray = new int[3];
                for(int i = 0;i<3;i++) {
                    sarray[i] = Integer.parseInt(tarray[i]);
                }
                array[0] = String.valueOf(sarray[0] * sarray[2] + sarray[1]);//a'b/c转化为整数
                array[1] = String.valueOf(sarray[2]);
            }else {
                array = num.split("/");
            }
        }else {
            array[0] = num;
            array[1] = "1";
        }
        return array;
    }
}
