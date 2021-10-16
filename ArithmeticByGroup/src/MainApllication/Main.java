package MainApllication;

import Util.GenerateUtil;

/**
 * 主函数，程序的入口
 * @author : [86135]
 * @version : [v1.0]
 */

public class Main {
    public static void main(String[] args) {
        int i=args.length;
        int num,range;
        long startTime = 0,endTime = 0;

        //按照题目要求,输入四个数据-n 10000 -r 10,不满足要求退出程序
        if(i==4) {
            if(args[0].equals("-n")&&args[2].equals("-r")) {
                num=Integer.parseInt(args[1]);
                range=Integer.parseInt(args[3]);
                if(num>0&&range>0) {
                    startTime = System.currentTimeMillis();
                    String[] expression = new String[num];
                    expression = GenerateUtil.createExpression(range, num);
                    for (int j = 0; j < expression.length; j++) {
                        System.out.println(j+1+"、 "+expression[j]);
                    }
                    endTime = System.currentTimeMillis();
                }else {
                    System.out.println("您输入的参数不合法！！！");
                    System.exit(0);
                }
            }else {
                System.out.println("您输入的参数不合法！！！");
                System.exit(0);
            }
        }else{
            System.out.println("您输入的参数数目不符合要求");
            System.exit(0);
        }
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }



}
