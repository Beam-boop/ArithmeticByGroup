package Util;

/**
 *
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/16 10:57]
 */

public class FormatUtil {
    /**
     * 格式化分数
     * 1.如果分子是分母的倍数，返回正整数 2.分子大于分母，但不是整数，则返回真分数 3.分子小于分母，返回分数
     *
     * @param molecular   分子
     * @param denominator 分母
     * @return String 格式化后的分数
     */
    public static String Format(int molecular, int denominator) {
        String fraction = null;
        int min = 0;
        //分子分母最小值
        min = Math.min(molecular, denominator);
        //如果分子等于0，那么返回分数为0
        if (molecular == 0) {
            fraction = "0";
        } else if (molecular % denominator == 0) {//如果可以被整除，直接返回整数
            fraction = String.valueOf(molecular / denominator);
        } else {
            //从最小的数min开始往下找，找公因子
            for (int i = min; i >= 2; i--) {
                if (molecular % i == 0 && denominator % i == 0) {
                    molecular = molecular / i;
                    denominator = denominator / i;
                }
            }
            //真分数，如果分子大于分母，可以化简为真分数
            if (molecular > denominator) {
                //真分数：整数‘分子模分母作为分子，分母不变
                fraction = String.valueOf(molecular / denominator) + "'" + String.valueOf(molecular % denominator) + "/" + String.valueOf(denominator);
            } else {
                fraction = molecular + "/" + denominator;
            }
        }
        return fraction;
    }

    /**
     * 将最终结果进行格式化
     * @param num 需要格式化的数据
     * @return 格式化后的结果
     * @throws Exception
     **/
    public static String finalResult(String num) throws Exception {
        String[] nums = new String[2];
        int mole, deno;
        nums = FractionUtil.change(num);
        mole = Integer.parseInt(nums[0]);
        deno = Integer.parseInt(nums[1]);
        String finalResult = Format(mole, deno);
        if(finalResult.contains("-")) {//部分运算结果为负数
            throw new Exception();
        }else {
            return finalResult;
        }
    }
}
