/*
 * Created by JFormDesigner on Fri Oct 15 21:50:44 CST 2021
 */

package form;

import net.miginfocom.swing.MigLayout;
import Util.ExamineUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Check extends JFrame {
    public Check() {
        initComponents();
    }
    String file1="";
    String file2="";

    private void selectFileActionPerformed(ActionEvent e) {
        // TODO add your code here
        JFileChooser chooser = new JFileChooser(); // 设置选择器
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false); // 设为单选
        int returnVal = chooser.showOpenDialog(selectFile); // 是否打开文件选择框

        if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型

            file1 = chooser.getSelectedFile().getAbsolutePath(); // 获取绝对路径
            showFile.setText(file1);
        }
    }



    private void selectFile1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        JFileChooser chooser = new JFileChooser(); // 设置选择器
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false); // 设为单选
        int returnVal = chooser.showOpenDialog(selectFile1); // 是否打开文件选择框

        if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型

            file2 = chooser.getSelectedFile().getAbsolutePath(); // 获取绝对路径
            showFile1.setText(file2);
        }



    }

    private void checkActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (showFile.getText() == null) {
            JOptionPane.showMessageDialog(null, "请输入题目路径");
        } else if (showFile1.getText() == null) {
            JOptionPane.showMessageDialog(null, "请输入答案路径");
        } else {
            new Examine().start();
            JOptionPane.showMessageDialog(null, "生成完毕");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label = new JLabel();
        showFile = new JTextField();
        selectFile = new JButton();
        label1 = new JLabel();
        showFile1 = new JTextField();
        selectFile1 = new JButton();
        check = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "fill,hidemode 3",
            // columns
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label ----
        label.setText("\u8bf7\u9009\u62e9\u9898\u76ee\u8def\u5f84");
        label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label, "cell 0 0,grow");
        contentPane.add(showFile, "cell 0 1");

        //---- selectFile ----
        selectFile.setText("...");
        selectFile.addActionListener(e -> selectFileActionPerformed(e));
        contentPane.add(selectFile, "cell 0 1,alignx right,growx 0");

        //---- label1 ----
        label1.setText("\u8bf7\u9009\u62e9\u7b54\u6848\u8def\u5f84");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        contentPane.add(label1, "cell 0 2,grow");
        contentPane.add(showFile1, "cell 0 3");

        //---- selectFile1 ----
        selectFile1.setText("...");
        selectFile1.addActionListener(e -> selectFile1ActionPerformed(e));
        contentPane.add(selectFile1, "cell 0 3,alignx trailing,growx 0");

        //---- check ----
        check.setText("\u68c0\u67e5");
        check.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        check.addActionListener(e -> checkActionPerformed(e));
        contentPane.add(check, "cell 0 4");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label;
    private JTextField showFile;
    private JButton selectFile;
    private JLabel label1;
    private JTextField showFile1;
    private JButton selectFile1;
    private JButton check;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    class Examine extends Thread{

        @Override
        public void run() {

            ExamineUtil.compareDifferent(showFile.getText(),showFile1.getText(),"Grade.txt");
        }
    }
}
