package top.ncserver.update.Mysetting;

import org.json.JSONObject;
import top.ncserver.update.INIT;
import top.ncserver.update.json_init;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class MyJFrame {
    JLabel lblNewLabel ;
    JFrame main;
    public MyJFrame(String name,int width,int height){
        lblNewLabel = new JLabel("");
        main = new JFrame(name);
       // lblNewLabel.re
        main.setSize(width,height);
        main.setLocationRelativeTo(main.getOwner());
        main.setUndecorated(true);
        main.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        main.getContentPane().setBackground(Color.white);
        main.setShape(new RoundRectangle2D.Double(0, 0, main.getWidth(), main.getHeight(), 50, 50));
        main.setResizable(false);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, -5, width, height);
        // 创建图片对象
        ImageIcon img = new ImageIcon(INIT.class.getResource("/bg/bg.png"));
        //设置图片在窗体中显示的宽度、高度
        img.setImage(img.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
        JPanel panel = new JPanel();
        panel.setBounds(0, -5, width, height);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        panel.add(lblNewLabel);
        lblNewLabel.setIcon(img);
        main.getContentPane().add(layeredPane);
        JButton closeButton = new JButton("关闭");
        closeButton.setBorderPainted(false);
        closeButton.setMargin(new Insets(0,0,0,0));
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBounds(width-40,0,50,50);
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

        JButton minButton = new JButton();
        minButton.setBounds(width-90,0,50,50);
        minButton.setBorderPainted(false);
        minButton.setMargin(new Insets(0,0,0,0));
        minButton.setFocusPainted(false);
        minButton.setContentAreaFilled(false);
        minButton.setIcon(new ImageIcon(INIT.class.getResource("/minButton_N.png")));
        minButton.setRolloverIcon(new ImageIcon(INIT.class.getResource("/minButton_C.png")));
        minButton.setPressedIcon(new ImageIcon(INIT.class.getResource("/minButton_P.png")));
        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               main.setExtendedState(JFrame.ICONIFIED);
            }
        });
        lblNewLabel.add(minButton);
        //json_init.config
/*        JTextField versionLabel = new JTextField(json_init.config.getString("C_version"));
        Font font_ = new Font("宋体",Font.BOLD,20);
        versionLabel.setFont(font_);
        versionLabel.setForeground(Color.white);
        versionLabel.setBounds(10,height-25,130,20);
        versionLabel.setBorder(null);
        versionLabel.setOpaque(false);
        lblNewLabel.add(versionLabel);*/
    }
    public void addJButton(JButton name)
    {
        lblNewLabel.add(name);
    }
    public void addJTextField(JTextField name)
    {
        lblNewLabel.add(name);
    }
    public void addJpasswordfield(JPasswordField name)
    {
        lblNewLabel.add(name);
    }
    public void addJTextArea(JTextArea name) { lblNewLabel.add(name);}
    public void setVisible(Boolean visible) { main.setVisible(visible); }
    public void setResizable(Boolean Resizable)
    {
        main.setResizable(Resizable);
    }
}
