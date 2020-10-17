package top.ncserver.update;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class vsersion_check {
    public static final Logger logger = Logger.getLogger(vsersion_check.class);
    public static String client_version;
    public static String server_version;
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i< Objects.requireNonNull(children).length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    //public static FTP test = new FTP();
    public static boolean check() throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        org.apache.commons.net.ftp.FTPClient ftpClient = FTPClient.getFTPClient("nas.ncserver.top", 21, "update", "n~7z26");

        server_version = FTPClient.check_Weeks_orders(ftpClient);
        logger.info("客户端最新版本V"+server_version);
        assert server_version != null;
        FTPClient.readFileByFolder(ftpClient, server_version.charAt(0) + "周目/");
        File client = new File("version.txt");
        if (!client.exists()) {
            FileWriter writer = new FileWriter("version.txt");
            writer.write("V" + server_version.charAt(0) + ".0.0");
            writer.close();
        }
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(client)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        client_version = br.readLine();



        return client_version.equals("V" + server_version);
    }
}
