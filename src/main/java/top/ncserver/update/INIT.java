package top.ncserver.update;
import org.apache.log4j.Logger;
import javax.swing.*;
public class INIT {
    public static final Logger logger = Logger.getLogger(INIT.class);
    public static JFrame alwaysOnTop =new JFrame("AlwaysOnTop");
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        alwaysOnTop.setAlwaysOnTop(true);
       //
       // UI.UI();

        //UI.loginUI();
MC_Login.MC_Login();
    }
}
