package top.ncserver.update;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;

/**
 * @author MakesYT
 */
public class INIT {
    public static final Logger logger = Logger.getLogger(INIT.class);
    public static JFrame alwaysOnTop =new JFrame("AlwaysOnTop");
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //json_init.init();
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

        //progress_bar bar = new progress_bar("http://download.ncserver.top:8000/update/C","6.1.0.zip");
       // new Thread(bar).start();
        //download.downloadHttpUrl("http://download.ncserver.top:8000/update/C",System.getProperty("user.dir"),"/"+"6.1.0.zip");
        File tempDir=new File(System.getProperty("user.dir")+"//temp");
        tempDir.mkdirs();
        json_init.init();
        Self_update.checkSelf();
        UI.loginui();

       // UI.UI();
        //Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start\u0020C:\\Windows\\Temp\\Ncharge_client\\start.bat");

       // MC_Login.MC_init();
    }
}
