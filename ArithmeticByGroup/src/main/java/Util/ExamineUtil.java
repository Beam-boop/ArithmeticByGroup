package Util;




import java.io.*;
import java.util.*;

public class ExamineUtil {


    public static void compareDifferent(String exercises, String answer, String Grade) {
        BufferedReader bfans = null;
        BufferedWriter bw = null;
        String[] rightAnswer ;

        ArrayList rightNumA = new ArrayList();
        ArrayList wrongNumA = new ArrayList();
        int A = 0;

        try {
            bfans = new BufferedReader(new InputStreamReader(new FileInputStream(new File(answer))));
            bw = new BufferedWriter(new FileWriter(Grade));

            //get the positive answers
            rightAnswer = ExamineUtil.calculateAnswer(exercises);
            String str = "";


            while ((str = bfans.readLine()) != null) {

                if (rightAnswer[A].substring(ExamineUtil.reDun(str)).equals(str.substring(ExamineUtil.reDun(str)))) {
                    A++;
                    rightNumA.add(A);
                } else {
                    A++;
                    wrongNumA.add(A);
                }
            }

            String correctNum = "";
            String wrongNum="";

            Iterator it1 = rightNumA.iterator();
            while(it1.hasNext()){
                correctNum+=it1.next()+"、";
            }

            Iterator it2 = wrongNumA.iterator();
            while(it2.hasNext()){
                wrongNum+=it2.next()+"、";
            }


            bw.write("Correct: "+rightNumA.size()+"("+correctNum+")"+"\n"+
                         "Wrong "+wrongNumA.size()+"("+wrongNum+")"  );


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public static String[] calculateAnswer(String exercisePath) {
        BufferedReader bfexp = null;
//        BufferedWriter bw=null;
        String[] answer = new String[10000];
        try {
            bfexp = new BufferedReader(new InputStreamReader(new FileInputStream(new File(exercisePath))));
//            bw = new BufferedWriter(new FileWriter(answerPath));
            int i = 1;
            String str = "";

            while ((str = bfexp.readLine()) != null) {
//
//                  bw.write( i+"、"+ ReversePolishUtil.ReversePolishNotation(str.substring(ExamineUtil.reDun(str)))+"\n");
                answer[i - 1] = i + "、" + ReversePolishUtil.ReversePolishNotation(str.substring(ExamineUtil.reDun(str),ExamineUtil.reEqual(str)));
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bfexp.close();
//                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return answer;
    }




    public static int reDun(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '、') break;
            num++;
        }
        return num + 1;
    }

    public static int reEqual(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '=') break;
            num++;
        }
        return num-1 ;
    }



} 
