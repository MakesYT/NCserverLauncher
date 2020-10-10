package top.ncserver.update;

import org.apache.log4j.Logger;
import org.to2mbn.jmccc.launch.LaunchException;
import top.ncserver.update.runclient.start;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;


public class main {
    public static final Logger logger = Logger.getLogger(main.class);
    public static JFrame alwaysOnTop =new JFrame("AlwaysOnTop");
    /*public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, LaunchException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        alwaysOnTop.setAlwaysOnTop(true);
        if (network_check()){
            if (ex_client.ex_check()) {
                if (vsersion_check.check()) {
                    logger.info("无需更新");
                    JOptionPane.showMessageDialog(alwaysOnTop, "无需更新");
                } else {
                    if (!vsersion_check.client_version.substring(1, 2).equals(vsersion_check.server_version.substring(0, 1)))
                    {
                        JOptionPane.showMessageDialog(alwaysOnTop,"检测到当前客户端非最新周目，将进行周目更新");
                        File folder = new File(".minecraft");

                        if (vsersion_check.deleteDir(folder))
                        {
                            JOptionPane.showMessageDialog(alwaysOnTop,"删除旧客户端完成");
                            ex_client.ex_check();
                        }

                        else {
                            JOptionPane.showMessageDialog(alwaysOnTop, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
                            System.exit(1);
                        }

                    }else
                    update.start();
                    if (vsersion_check.check())
                        logger.info("更新完成");
                    JOptionPane.showMessageDialog(alwaysOnTop, "更新完成!当前版本：" + vsersion_check.client_version);
                    start.start();
                }
            }
    }
        else JOptionPane.showMessageDialog(alwaysOnTop, "网络异常(甚至连接不上百度)", "错误", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }*/
    public static void main(String[] args) throws IOException, LaunchException {
        start.start();
    }

    /**
     */
    static boolean network_check(){
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            return address.isReachable(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
