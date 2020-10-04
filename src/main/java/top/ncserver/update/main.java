package top.ncserver.update;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class main {
    public static final Logger logger = Logger.getLogger(main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //while (!vsersion_check.check())
        if (ex_client.ex_check()) {
            if (vsersion_check.check()) {
                logger.info("无需更新");
                JOptionPane.showMessageDialog(null, "无需更新");
                System.exit(0);

            } else {
                update.start();
                if (vsersion_check.check())
                    logger.info("更新完成");
                JOptionPane.showMessageDialog(null, "更新完成!当前版本：" + vsersion_check.client_version);

                System.exit(0);
            }
        }
        //ui.Init_ui();
    }
}
