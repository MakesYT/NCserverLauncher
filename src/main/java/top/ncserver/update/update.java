package top.ncserver.update;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.*;

public class update {
    public static final Logger logger = Logger.getLogger(update.class);

    public static void reload() throws IOException {
        logger.info("重载版本号");
        File client = new File("version.txt");
        if (!client.exists()) {
            FileWriter writer = new FileWriter("version.txt");
            writer.write("V" + vsersion_check.server_version.charAt(0) + ".0.0");
            writer.close();
        }
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(client)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        vsersion_check.client_version = br.readLine();
        logger.info("重载完成");
    }

    public static void start() throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // logger.info(FTP.update_backpack[0]);
        File directory = new File("");
        logger.info("开始更新");
        while (!vsersion_check.client_version.equals("V" + vsersion_check.server_version))

            for (int i = 0; i <= FTPClient.update_backpack_size - 1; i++) {
                //logger.info(FTP.update_backpack[i].indexOf(vsersion_check.client_version));
                if (FTPClient.update_backpack[i].indexOf(vsersion_check.client_version) == 0) {
                    //logger.info(directory.getAbsolutePath());
                    String flag = FTPClient.downFile(FTPClient.ftpClient, vsersion_check.server_version.charAt(0) + "周目/",
                            FTPClient.update_backpack[i], directory.getAbsolutePath());
                    if (flag.equals("下载成功 ！")) {
                        if (zip.decompressZip(directory.getAbsolutePath() + "/" + FTPClient.update_backpack[i], directory.getAbsolutePath() + "/")) {
                            logger.info(FTPClient.update_backpack[i].substring(FTPClient.update_backpack[i].indexOf("_to_") + 4,
                                    FTPClient.update_backpack[i].indexOf(".zip")));
                            logger.info("开始改写版本号");
                            FileWriter writer = new FileWriter("version.txt");
                            writer.write(FTPClient.update_backpack[i].substring(FTPClient.update_backpack[i].indexOf("_to_") + 4,
                                    FTPClient.update_backpack[i].indexOf(".zip")));
                            writer.close();
                            vsersion_check.client_version =
                                    FTPClient.update_backpack[i].substring(FTPClient.update_backpack[i].indexOf("_to_") + 5,
                                            FTPClient.update_backpack[i].indexOf(".zip"));
                            logger.info("改写版本号完成");
                            reload();
                        }
                    }
                }
            }

    }
}
