package top.ncserver.update;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class progress_bar implements Runnable{
    public static final Logger logger = Logger.getLogger(progress_bar.class);
    private String filesize;
    private String filename;
    File directory ;
    public progress_bar(String printSize,String name) {
       filesize=printSize;
       //filename=name;
       directory = new File(name);
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        JFrame bar = new JFrame("我是一个无情的进度条");
        Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        bar.setSize((int) (point.x/3), (int) (point.x/3*0.618));
        bar.setLocationRelativeTo(null);
        bar.setResizable(false);
        bar.setVisible(true);

        JLabel bar_ = new JLabel(FTPClient.getPrintSize(directory.length())+"/"+filesize);
        Font font=new Font("宋体",Font.BOLD,20);
        bar_.setFont(font);
        bar.add(bar_);
        logger.info(filename);
    while (true)
    {
        try {

            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(FTPClient.getPrintSize(directory.length()));
        bar_.setText(FTPClient.getPrintSize(directory.length())+"/"+filesize);
        bar.add(bar_);
    }
    }
}
