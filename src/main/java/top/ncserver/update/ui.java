package top.ncserver.update;

import javax.swing.*;
import java.awt.*;

public class ui {
    public static boolean Init_ui()
    {
        JFrame main = new JFrame("Ncharge更新器");
        Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        main.setSize((int) (point.x/1.5), (int) (point.x/1.5*0.618));
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return false;
    }
}
