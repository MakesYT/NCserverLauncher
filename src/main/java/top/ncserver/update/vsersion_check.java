package top.ncserver.update;

import org.apache.log4j.Logger;

import java.io.*;

public class vsersion_check {
    public static final Logger logger = Logger.getLogger(vsersion_check.class);
    public static String client_version;
    public static String server_version;
    //public static FTP test = new FTP();
    public static boolean check() throws IOException {

        org.apache.commons.net.ftp.FTPClient ftpClient = FTPClient.getFTPClient("nas.ncserver.top",21,"update","n~7z26");
        server_version= FTPClient.check_Weeks_orders(ftpClient);
        assert server_version != null;
        FTPClient.readFileByFolder(ftpClient, server_version.charAt(0)+"周目/");
        File client=new File("version.txt");
        if(!client.exists())
            {
            FileWriter writer =new FileWriter("version.txt");
            writer.write("V"+server_version.charAt(0)+".0.0");
            writer.close();
            }
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(client)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
         client_version = br.readLine();
        //FTP.closeFTP(ftp);
        //logger.info(client_version);
        //logger.info("V"+server_version);
        return client_version.equals("V" + server_version);
    }
}
