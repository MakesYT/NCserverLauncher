package top.ncserver.update;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;


public class main {
    public static final Logger logger = Logger.getLogger(main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //ui.Init_ui();
        if (network_check()){
            if (ex_client.ex_check()) {
                if (vsersion_check.check()) {
                    logger.info("无需更新");
                    JOptionPane.showMessageDialog(null, "无需更新");
                } else {
                    update.start();
                    if (vsersion_check.check())
                        logger.info("更新完成");
                    JOptionPane.showMessageDialog(null, "更新完成!当前版本：" + vsersion_check.client_version);
                }
            }
    }
        else JOptionPane.showMessageDialog(null, "网络异常(甚至连接不上百度)", "错误", JOptionPane.ERROR_MESSAGE);
        System.exit(0);


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
