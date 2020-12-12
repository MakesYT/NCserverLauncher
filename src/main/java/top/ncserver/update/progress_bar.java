package top.ncserver.update;

import javafx.scene.control.ProgressBar;
import org.apache.log4j.Logger;
import top.ncserver.update.Mysetting.MyJFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;

import static java.lang.Math.abs;

public class progress_bar implements Runnable {
    public static final Logger logger = Logger.getLogger(progress_bar.class);
    File directory;
    String percent;
    // 创建一个数值格式化对象
    NumberFormat numberFormat = NumberFormat.getInstance();
long fileSize;

String fileName;
String flieSizeAfterTreatment;
static long downloaded;
static String downloadedA;
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }
    // 设置精确到小数点后2位
    MyJFrame bar;
    JTextField bar_;
    public progress_bar(String stringUrl, String name) throws IOException {
            fileSize=download.getFileSize(stringUrl+"/"+name);
            flieSizeAfterTreatment=getPrintSize(fileSize);
            fileName=name;
            directory=new File(System.getProperty("user.dir")+name);
             bar = new MyJFrame("无情的进度条",500,250,3);
         bar_ = new JTextField("");
        Font font = new Font("宋体", Font.BOLD, 35);
        bar_.setFont(font);
        bar_.setForeground(Color.white);
        bar_.setBounds(10,100,500,60);
        bar_.setBorder(null);
        bar_.setOpaque(false);
        //assert bar != null;
        bar.addJTextField(bar_);
        bar.setVisible(true);
    }

    @Override
    public void run() {

        logger.info("确认监听文件："+fileName);
        bar.setVisible(true);
        while (abs(fileSize - downloaded) >= 1000) {

            try {

                numberFormat.setMaximumFractionDigits(2);

                percent = numberFormat.format((double) downloaded / (double) fileSize * 100);
                //logger.info(getPrintSize(directory.length()) + "  " + percent + "%");
                bar_.setText(downloadedA + "/" + flieSizeAfterTreatment + "  "+ percent + "%" );

                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        bar.setVisible(false);
    }
}