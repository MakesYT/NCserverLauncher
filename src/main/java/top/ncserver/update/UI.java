package top.ncserver.update;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

    public static void UI(){
        int width =800;
        int height=500;
        JFrame main = new JFrame("Ncharge服务器");
        main.setSize(width,height);
        main.setLocationRelativeTo(main.getOwner());
        main.setUndecorated(true);
        main.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        main.getContentPane().setBackground(Color.white);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, -5, width, height);
        // 创建图片对象
        ImageIcon img = new ImageIcon(INIT.class.getResource("/bg/bg.png"));
        //设置图片在窗体中显示的宽度、高度
        img.setImage(img.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
        JPanel panel = new JPanel();
        panel.setBounds(0, -5, width, height);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        JLabel lblNewLabel = new JLabel("");
        panel.add(lblNewLabel);
        lblNewLabel.setIcon(img);
        main.getContentPane().add(layeredPane);
        JButton closeButton = new JButton("关闭");
        closeButton.setBorderPainted(false);
        closeButton.setMargin(new Insets(0,0,0,0));
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBounds(width-30,-10,50,50);
        closeButton.setIcon(new ImageIcon(INIT.class.getResource("/closeButton_N.png")));
        closeButton.setRolloverIcon(new ImageIcon(INIT.class.getResource("/closeButton_C.png")));
        closeButton.setPressedIcon(new ImageIcon(INIT.class.getResource("/closeButton_P.png")));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        lblNewLabel.add(closeButton);
        main.setResizable(false);
        main.setVisible(true);
    }
}
