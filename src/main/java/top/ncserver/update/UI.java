package top.ncserver.update;
import top.ncserver.update.MC_start.MC_Login;
import top.ncserver.update.Mysetting.JTextFieldHintListener;
import top.ncserver.update.Mysetting.MyJFrame;
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
    public static MyJFrame main;
    public static void loginUI()
    {
        int width =450;
        int height=300;
         main=new MyJFrame("Login",width,height);

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
        main.addJButton(registerButton);

        JTextArea title = new JTextArea("登录");
        title.setBounds(190,50,100,50);
        title.setBorder(null);
        title.setOpaque(false);
        Font font =new Font("黑体",Font.BOLD|Font.ITALIC,32);
        title.setFont(font);
        title.setForeground(Color.white);
        main.addJTextArea(title);

        user_jtextField = new MyJtextField();
        user_jtextField.setBounds(120, 110, 210, 44);
        user_jtextField.addFocusListener(new JTextFieldHintListener(user_jtextField, "邮箱"));
        main.addJTextField(user_jtextField);

        pass_jpassField = new MyJpassTextfield();
        pass_jpassField.setBounds(120, 160, 210, 44);
        pass_jpassField.addFocusListener(new JTextFieldHintListener(pass_jpassField, "密码"));
        pass_jpassField.setEchoChar((char)0);
        main.addJpasswordfield(pass_jpassField);
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
        main.addJButton(loginButton);

        main.setVisible(true);
    }
    public static void UI() throws Exception {
        int width =900;
        int height=500;
        MyJFrame mainFrame=new MyJFrame("Ncharge服务器",width,height);

        JButton startButton=new JButton("启动游戏");
        startButton.setForeground(Color.white);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        Font startButtonFont =new Font("楷体",Font.BOLD,32);
        startButton.setOpaque(false);
        startButton.setFont(startButtonFont);
        startButton.setBorderPainted(false);
        startButton.setMargin(new Insets(0,0,0,0));
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setBounds(20,200,280,79);
        startButton.setIcon(new ImageIcon(INIT.class.getResource("/startButton_N.png")));
        startButton.setRolloverIcon(new ImageIcon(INIT.class.getResource("/startButton_C.png")));
        startButton.setPressedIcon(new ImageIcon(INIT.class.getResource("/startButton_P.png")));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MC_Login.MC_init();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        mainFrame.addJButton(startButton);
        
        JButton via1 = new JButton();
        ImageIcon viaIcon=new ImageIcon("C:\\Windows\\Temp\\Ncharge_client\\via.png");
        via1.setIcon(viaIcon);
        via1.setBorderPainted(false);
        via1.setMargin(new Insets(0,0,0,0));
        via1.setFocusPainted(false);
        via1.setContentAreaFilled(false);
        via1.setBounds(20,20,128,128);
        mainFrame.addJButton(via1);
        JTextArea title = new JTextArea(Login.userName+
                 "\n欢迎您\n游玩本服务器");
        title.setBounds(150,30,220,150);
        title.setBorder(null);
        title.setOpaque(false);
        Font font =new Font("楷体",Font.BOLD,32);
        title.setFont(font);
        title.setForeground(Color.white);
        mainFrame.addJTextArea(title);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
       // System.out.println(Login.token);
        //MC_Login.MC_init();
    }
}
