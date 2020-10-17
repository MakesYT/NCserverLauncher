package top.ncserver.update;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;

import static java.lang.Math.abs;

public class progress_bar implements Runnable {
    public static final Logger logger = Logger.getLogger(progress_bar.class);
    File directory;
    String percent;
    // 创建一个数值格式化对象
    NumberFormat numberFormat = NumberFormat.getInstance();
    private final String filesize;
    private final String filename;
    private final long size1;

    // 设置精确到小数点后2位
    public progress_bar(String printSize, String name, long size) {
        filesize = printSize;
        filename=name;
        // 创建一个数值格式化对象

        size1 = size;
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
        bar.setSize(point.x / 3, (int) (point.x / 3 * 0.618));
        bar.setLocationRelativeTo(null);
        bar.setResizable(false);
        bar.setVisible(true);
        bar.setAlwaysOnTop(true);
        JLabel bar_ = new JLabel("");
        Font font = new Font("宋体", Font.BOLD, 20);
        bar_.setFont(font);
        bar.add(bar_);
        logger.info("确认监听文件："+filename);
        while (abs(size1 - directory.length()) >= 1) {

            try {
                numberFormat.setMaximumFractionDigits(2);
                percent = numberFormat.format((double) directory.length() / (double) size1 * 100);
                logger.info(FTPClient.getPrintSize(directory.length()) + "  " + percent + "%");
                bar_.setText(FTPClient.getPrintSize(directory.length()) + "/" + filesize + "  " + percent + "%");
                bar.add(bar_);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        bar.setVisible(false);
    }
}
