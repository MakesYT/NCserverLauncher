package top.ncserver.update.Mysetting;

import top.ncserver.update.INIT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * @author MakesYT
 */
public class MyJButton extends JButton {
    static JButton startButton;
    public MyJButton(MyJFrame name, String content, int x, int y, ActionListener a)
    {
         startButton=new JButton(content);
        startButton.setForeground(Color.white);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        Font startButtonFont =new Font("楷体",Font.BOLD,32);
        startButton.setOpaque(false);
        startButton.setFont(startButtonFont);
        startButton.setBorderPainted(false);
        startButton.setMargin(new Insets(0,0,0,0));
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setBounds(x,y,280,79);
        startButton.setIcon(new ImageIcon(INIT.class.getResource("/startButton_N.png")));
        startButton.setRolloverIcon(new ImageIcon(INIT.class.getResource("/startButton_C.png")));
        startButton.setPressedIcon(new ImageIcon(INIT.class.getResource("/startButton_P.png")));
        startButton.addActionListener( a);
        name.addJButton(startButton);
    }

}
