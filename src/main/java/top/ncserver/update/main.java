package top.ncserver.update;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class main {
    public static final Logger logger = Logger.getLogger(main.class);
    public static void main( String[] args ) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        if (vsersion_check.check())
        {
            logger.info("无需更新");
            JOptionPane.showMessageDialog(null,"无需更新");
        }
        //ui.Init_ui();
    }
}
