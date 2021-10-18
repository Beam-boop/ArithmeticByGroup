/*
 * Created by JFormDesigner on Fri Oct 15 21:49:37 CST 2021
 */

package form;

import net.miginfocom.swing.MigLayout;
import Util.FileIOUtil;
import Util.GenerateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Form extends JFrame {
    public Form() {
        initComponents();
    }

    String[] expression;
    String[] exercise;
    private void CreatActionPerformed(ActionEvent e) {
        // TODO add your code here

        if (Num.getText() == null) {
            JOptionPane.showMessageDialog(null, "请输入生成题目个数");
        } else if (R.getText() == null) {
            JOptionPane.showMessageDialog(null, "请输入参数r以控制题目数值");
        } else {
            new ExpressionCreatThread().start();
            JOptionPane.showMessageDialog(null, "生成完毕");
            for (int i=0;i<Integer.parseInt(Num.getText());i++){
                showExercise.append(i+1+"、"+" "+exercise[i]+"\n");


            }

        }


    }


    private void CheakActionPerformed(ActionEvent e) {
        // TODO add your code here
        Check check = new Check();
        check.setVisible(true);
        check.setMinimumSize(new Dimension(400, 300));
        check.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    private void initComponents() {
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel NeedNum;
    private JTextField Num;
    private JScrollPane scrollPane1;
    private JTextArea showExercise;
    private JLabel NeedR;
    private JTextField R;
    private JButton Creat;
    private JButton Cheak;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    class ExpressionCreatThread extends Thread {
        @Override
        public void run() {
            String[] expression  = GenerateUtil.createExpression(Integer.parseInt(R.getText()), Integer.parseInt(Num.getText()));
            exercise=GenerateUtil.toExercise(expression);
            FileIOUtil.expressionOutput(exercise);

            String[] answer = GenerateUtil.toAnswer(expression);
            FileIOUtil.answerOutput(answer);


        }
    }
}
