package MyApplication;

import form.*;
import javax.swing.*;
import java.awt.*;



public class MainForm {
    public static void main(String[] args) {
        Form form=new Form();
        form.setMinimumSize(new Dimension(600, 300));
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
