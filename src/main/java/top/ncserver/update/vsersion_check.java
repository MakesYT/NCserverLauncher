package top.ncserver.update;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class vsersion_check {
    public static void check() throws FileNotFoundException {

        File f = new File("version.txt");
        FileOutputStream fop = new FileOutputStream(f);

        FTP test = new FTP();
        FTPClient ftp =FTP.getFTPClient("192.168.2.28",21,"update","n~7z26");
        FTP.readFileByFolder(ftp, "");

    }
}
