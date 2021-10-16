package Util;

import java.io.File;
import java.io.FileWriter;

/**
 * 文件输出
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/14 21:22]
 */

public class FileIOUtil {
    public static void FileOutput(StringBuilder s, int type){

        String path="";
        //存储表达式文件
        if(type == 1)
            path="src\\Exercises.txt";
        if(type == 2)
            path="src\\Answers.txt";

        File file=new File(path);
        FileWriter fw=null;
        try{
            fw = new FileWriter(file);
            fw.write(String.valueOf(s));
            fw.close();
        }catch(Exception e){
            System.out.println("文件保存失败！");
        }
    }

}
