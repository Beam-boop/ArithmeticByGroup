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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        NeedNum = new JLabel();
        Num = new JTextField();
        scrollPane1 = new JScrollPane();
        showExercise = new JTextArea();
        NeedR = new JLabel();
        R = new JTextField();
        Creat = new JButton();
        Cheak = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "fill,hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));

        //---- NeedNum ----
        NeedNum.setText("\u8bf7\u8f93\u5165\u751f\u6210\u9898\u76ee\u4e2a\u6570");
        NeedNum.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        contentPane.add(NeedNum, "cell 0 0 2 1,grow");
        contentPane.add(Num, "cell 2 0 2 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(showExercise);
        }
        contentPane.add(scrollPane1, "cell 4 0 7 3,grow");

        //---- NeedR ----
        NeedR.setText("\u8bf7\u8f93\u5165\u53c2\u6570\u63a7\u5236\u9898\u76ee\u6570\u503c");
        NeedR.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        contentPane.add(NeedR, "cell 0 1 2 1");
        contentPane.add(R, "cell 2 1 2 1");

        //---- Creat ----
        Creat.setText("\u751f\u6210");
        Creat.addActionListener(e -> CreatActionPerformed(e));
        contentPane.add(Creat, "cell 0 2 2 1");

        //---- Cheak ----
        Cheak.setText("\u68c0\u67e5\u7b54\u6848");
        Cheak.addActionListener(e -> CheakActionPerformed(e));
        contentPane.add(Cheak, "cell 2 2 2 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
