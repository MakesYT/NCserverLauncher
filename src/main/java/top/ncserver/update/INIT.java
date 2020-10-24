package top.ncserver.update;

import org.apache.log4j.Logger;
import org.to2mbn.jmccc.launch.LaunchException;

import javax.swing.*;
import java.io.IOException;

public class INIT {
    public static final Logger logger = Logger.getLogger(INIT.class);
    public static JFrame alwaysOnTop =new JFrame("AlwaysOnTop");
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, LaunchException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        alwaysOnTop.setAlwaysOnTop(true);

    }
}
