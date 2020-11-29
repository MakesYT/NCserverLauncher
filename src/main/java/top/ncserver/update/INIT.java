package top.ncserver.update;
import org.apache.log4j.Logger;
import top.ncserver.update.MC_start.MC_Login;
import top.ncserver.update.Mysetting.JTextFieldHintListener;
import top.ncserver.update.Mysetting.MyJFrame;
import top.ncserver.update.Mysetting.MyJtextField;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class INIT {
    public static final Logger logger = Logger.getLogger(INIT.class);
    public static JFrame alwaysOnTop =new JFrame("AlwaysOnTop");
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        alwaysOnTop.setAlwaysOnTop(true);
        //MyJFrame main=new MyJFrame("1",200,300);
        //JTextField a=new JTextField();


       // JFileChooser chooser = new JFileChooser("C:\\Program Files\\Java");
        //chooser.setFileFilter(new FileNameExtensionFilter("exe(*.exe)", "exe"));
        //chooser.setSelectedFile(new File("java.exe"));
        //chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //chooser.showDialog(new JLabel(), "选择");
        //File file = chooser.getSelectedFile();
        //System.out.println(file);
        //a.setText(file.getAbsoluteFile().toString());
        //main.addJTextField(a);
        //main.setVisible(true);
        json_init.init();
         UI.loginUI();//
       // UI.UI();
        //Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start\u0020C:\\Windows\\Temp\\Ncharge_client\\start.bat");

       // MC_Login.MC_init();
    }
}
