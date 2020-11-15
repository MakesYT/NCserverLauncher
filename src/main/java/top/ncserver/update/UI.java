package top.ncserver.update;
import top.ncserver.update.MC_start.MC_Login;
import top.ncserver.update.Mysetting.JTextFieldHintListener;
import top.ncserver.update.Mysetting.MyJpassTextfield;
import top.ncserver.update.Mysetting.MyJtextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class UI {


    public static JTextField user_jtextField;
    public static JPasswordField pass_jpassField;
    public static JFrame main;
    public static void loginUI()
    {
        int width =450;
        int height=300;
        main = new JFrame("Login");
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
        JLabel lblNewLabel = new JLabel("");
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

        JButton registerButton = new JButton("注册");
        registerButton.setBorderPainted(false);
        registerButton.setMargin(new Insets(0,0,0,0));
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setBounds(230,210,110,40);
        registerButton.setIcon(new ImageIcon(INIT.class.getResource("/registerButton_N.png")));
        registerButton.setRolloverIcon(new ImageIcon(INIT.class.getResource("/registerButton_C.png")));
        registerButton.setPressedIcon(new ImageIcon(INIT.class.getResource("/registerButton_P.png")));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = "cmd /c start https://www.ncserver.top:666/auth/register";
                try {
                    Runtime.getRuntime().exec(command);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        lblNewLabel.add(registerButton);

        JTextArea title = new JTextArea("登录");
        title.setBounds(190,50,100,50);
        title.setBorder(null);
        title.setOpaque(false);
        Font font =new Font("黑体",Font.BOLD|Font.ITALIC,32);
        title.setFont(font);
        title.setForeground(Color.white);
        lblNewLabel.add(title);

        user_jtextField = new MyJtextField();
        user_jtextField.setBounds(120, 110, 210, 44);
        user_jtextField.addFocusListener(new JTextFieldHintListener(user_jtextField, "邮箱"));
        lblNewLabel.add(user_jtextField);

        pass_jpassField = new MyJpassTextfield();
        pass_jpassField.setBounds(120, 160, 210, 44);
        pass_jpassField.addFocusListener(new JTextFieldHintListener(pass_jpassField, "密码"));
        pass_jpassField.setEchoChar((char)0);
        lblNewLabel.add(pass_jpassField);
        JButton loginButton = new JButton("登陆");
        loginButton.setBorderPainted(false);
        loginButton.setMargin(new Insets(0,0,0,0));
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBounds(110,210,110,40);
        loginButton.setIcon(new ImageIcon(INIT.class.getResource("/loginButton_N.png")));
        loginButton.setRolloverIcon(new ImageIcon(INIT.class.getResource("/loginButton_C.png")));
        loginButton.setPressedIcon(new ImageIcon(INIT.class.getResource("/loginButton_P.png")));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Login.Login();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        lblNewLabel.add(loginButton);

        main.setVisible(true);
    }
    public static void UI() throws Exception {
        int width =900;
        int height=500;
        JFrame main = new JFrame("Ncharge服务器");
        main.setSize(width,height);
        main.setLocationRelativeTo(main.getOwner());
        main.setUndecorated(true);
        main.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        main.getContentPane().setBackground(Color.white);
        main.setShape(new RoundRectangle2D.Double(0, 0, main.getWidth(), main.getHeight(), 50, 50));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, width, height);
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
        closeButton.setBounds(width-50,0,50,50);
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

        JButton via1 = new JButton();
        ImageIcon viaIcon=new ImageIcon("C:\\Windows\\Temp\\Ncharge_client\\via.png");
        via1.setIcon(viaIcon);
        via1.setBorderPainted(false);
        via1.setMargin(new Insets(0,0,0,0));
        via1.setFocusPainted(false);
        via1.setContentAreaFilled(false);
        via1.setBounds(20,20,128,128);
        lblNewLabel.add(via1);
        JTextArea title = new JTextArea(Login.userName+"\n欢迎您\n游玩本服务器");
        title.setBounds(150,30,220,150);
        title.setBorder(null);
        title.setOpaque(false);
        Font font =new Font("楷体",Font.BOLD,32);
        title.setFont(font);
        title.setForeground(Color.white);
        lblNewLabel.add(title);
        main.setResizable(false);
        main.setVisible(true);
        System.out.println(Login.token);
       // MC_Login.MC_init();
    }
}
