package top.ncserver.update;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class vsersion_check {
    public static String client_version;
    public static void check() throws IOException {
        FTP test = new FTP();
        FTPClient ftp =FTP.getFTPClient("192.168.2.28",21,"update","n~7z26");
        String Weeks_orders=FTP.check_Weeks_orders(ftp);
        FTP.readFileByFolder(ftp, Weeks_orders+"周目/");
        File client=new File("version.txt");
        if(!client.exists())
            {
            FileWriter writer =new FileWriter("version.txt");
            writer.write("V"+Weeks_orders+".0.0");
            writer.close();
            }
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(client)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
         client_version = br.readLine();
    }
}
