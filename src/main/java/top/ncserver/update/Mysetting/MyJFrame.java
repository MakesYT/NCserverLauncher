package top.ncserver.update.Mysetting;

import org.json.JSONObject;
import top.ncserver.update.INIT;
import top.ncserver.update.json_init;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import org.apache.log4j.Logger;

/**
 * @author MakesYT
 */
public class MyJFrame {
    JLabel lblNewLabel ;
    JFrame main;
    static Logger logger = Logger.getLogger(MyJFrame.class);
    /**
     * @param name 标题名字
     * @param type 1=关闭退出进程 2=关闭仅关闭当前窗口 3=无关闭最小化按钮
     * */
    public MyJFrame(String name, int width, int height, final int type)  {
        try {
            lblNewLabel = new JLabel("");
            main = new JFrame(name);
            // lblNewLabel.re
            main.setSize(width,height);
            main.setLocationRelativeTo(main.getOwner());
            main.setUndecorated(true);
            main.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            main.getContentPane().setBackground(Color.white);
            //main.setShape(new RoundRectangle2D.Double(0, 0, main.getWidth(), main.getHeight(), 50, 50));
            main.setResizable(false);
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, -10, width+20, height+20);
            // 创建图片对象
            ImageIcon img = new ImageIcon(INIT.class.getResource("/bg/bg.png"));
            img.setImage(img.getImage().getScaledInstance(width+20, height+20,Image.SCALE_DEFAULT));
            JPanel panel = new JPanel();
            panel.setBounds(0, -10, width+20, height+20);
            layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
            panel.add(lblNewLabel);
            lblNewLabel.setIcon(img);
            main.getContentPane().add(layeredPane);
            if (type!=3)
            {
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
                        if (type==1) {
                            System.exit(0);
                        }
                        if (type==2){
                            main.setVisible(false);
                        }
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
            }

            //json_init.config
            JSONObject config;
            String str = "";
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\config.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                str += tempString;
            }
            //System.out.println(str);
            config=new JSONObject(str);
            JTextField versionLabel = new JTextField(config.getString("version"));
            Font font_ = new Font("宋体",Font.BOLD,20);
            versionLabel.setFont(font_);
            versionLabel.setForeground(Color.white);
            versionLabel.setBounds(10,height-25,130,20);
            versionLabel.setBorder(null);
            versionLabel.setOpaque(false);
            lblNewLabel.add(versionLabel);
        }catch (IOException e)
        {
            logger.error(e);
            logger.error("UI加载失败");
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "UI加载失败", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

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
    public void addJCheckBox(JCheckBox name) { lblNewLabel.add(name); }
}
