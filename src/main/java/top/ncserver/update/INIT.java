package top.ncserver.update;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;

/**
 * @author MakesYT
 */
public class INIT {
     static Logger logger = Logger.getLogger(INIT.class);
    public static JFrame alwaysOnTop =new JFrame("AlwaysOnTop");
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Info.init();
        Info.type=1;
        Info msg=new Info("正在启动...");
        new Thread(msg).start();
        alwaysOnTop.setAlwaysOnTop(true);
        //创建临时目录，失败则退出
        logger.info("开始初始化...");

        File tempDir=new File(System.getProperty("user.dir")+"//temp");
        if (tempDir.exists()){logger.info("临时目录已存在，跳过创建");}
        else if (tempDir.mkdirs()){logger.info("临时目录创建成功");}
        else if (!tempDir.mkdirs()&&!tempDir.exists())
        { JOptionPane.showMessageDialog(INIT.alwaysOnTop, "权限异常，无法正常创建文件！", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1); }

        //配置文件初始化
        json_init.init();


        //检查更新
        Self_update.checkSelf();
        //UI初始化
        autoLogin.check();
        if (autoLogin.autoLogin)
        {
            Login.Login();
        }else {
            UI.loginui();
        }
        Info.setVisible(false);
    }
}
