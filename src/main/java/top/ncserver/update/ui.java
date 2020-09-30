package top.ncserver.update;

import javax.swing.*;
import java.awt.*;

public class ui {
    public static JFrame main = new JFrame("Ncharge更新器");
    public static void Init_ui()
    {

        Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        main.setSize((int) (point.x/1.75), (int) (point.x/1.75*0.618));
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setVisible(true);
        ImageIcon icon = new ImageIcon("src/main/resources/server-icon.png");
        JLabel icon1 = new JLabel();
        JLabel title = new JLabel("Ncharge\n客户端更新程序");
        title.setForeground(Color.black);
        title.setBounds(150, 35,(int) (point.x/1.75) , 50);
        Font font=new Font("宋体",Font.BOLD,30);
        title.setFont(font);
        icon1.setIcon(icon);
        icon1.setBounds(1, 1, icon.getIconWidth(), icon.getIconHeight());
        main.add(icon1);
        main.add(title);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}