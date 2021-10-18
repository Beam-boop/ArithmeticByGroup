package Util;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;

/**
 * 文件的输入和输出
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/16 10:57]
 */

public class FileIOUtil {
    public static void answerOutput(String[] s){

        String path="answers.txt";
        //存储表达式文件

        File file=new File(path);
        FileWriter fw;
        try{
            fw = new FileWriter(file);
            for(int i=1;i<s.length+1;i++){
                fw.write(i+"、"+s[i-1]+"\n");
//                fw.write(s[i-1]+"\n");
            }

            fw.close();
        }catch(Exception e){
            System.out.println("文件保存失败！");
            e.printStackTrace();
        }
    }


    public static void expressionOutput(String[] s){

        String path="exercises.txt";
        //存储表达式文件

        File file=new File(path);
        FileWriter fw;
        try{
            fw = new FileWriter(file);
            for(int i=1;i<s.length+1;i++){
                fw.write(i+"、"+s[i-1]+"\n");
//                fw.write(s[i-1]+"\n");
            }

            fw.close();
        }catch(Exception e){
            System.out.println("文件保存失败！");

        }
    }

}
