package top.ncserver.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.SocketException;

import org.apache.commons.net.ftp.*;

import org.apache.log4j.Logger;

/**
 * 简单操作FTP工具类 ,此工具类支持中文文件名，不支持中文目录
 * 如果需要支持中文目录，需要 new String(path.getBytes("UTF-8"),"ISO-8859-1") 对目录进行转码
 */

public class FTPClient {

    public static org.apache.commons.net.ftp.FTPClient ftpClient = null;
    public static final Logger logger = Logger.getLogger(FTPClient.class);
    public static String[] update_backpack = new String[30];
    public static int update_backpack_size = 0;


    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }
    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     服务器IP
     * @param ftpPort     服务器端口号
     * @param ftpUserName 用户名
     * @param ftpPassword 密码
     * @return FTPClient
     */
    public static org.apache.commons.net.ftp.FTPClient getFTPClient(String ftpHost, int ftpPort,
                                                                    String ftpUserName, String ftpPassword) {


        try {
            ftpClient = new org.apache.commons.net.ftp.FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftpClient.connect(ftpHost, ftpPort);
            // 设置用户名和密码
            ftpClient.login(ftpUserName, ftpPassword);
            // 设置连接超时时间,5000毫秒
            ftpClient.setConnectTimeout(50000);
            // 设置中文编码集，防止中文乱码
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("GBK");
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置");
        }
        return ftpClient;
    }


    /**
     * 关闭FTP方法
     *
     * @param ftpClient
     * @return boolean
     */
    public static boolean closeFTP(org.apache.commons.net.ftp.FTPClient ftpClient) {

        try {
            ftpClient.logout();
        } catch (Exception e) {
            logger.error("FTP关闭失败");
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTP关闭失败");
                }
            }
        }

        return false;

    }


    /**
     * 从FTP服务器下载文件
     *
     * @param ftpClient
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */

    public static String downFile(org.apache.commons.net.ftp.FTPClient ftpClient,
                                  String remotePath, String fileName, String localPath) {

        String result = "下载失败 ！";
        try {
            ftpClient.changeWorkingDirectory(remotePath); // 转移到FTP服务器目录
            FTPFile[] fs = ftpClient.listFiles();
            boolean flag = false; // 下载文件不存在
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    logger.info("下载开始,文件："+fileName+" 大小："+getPrintSize(ff.getSize()));
                    progress_bar bar=new progress_bar(getPrintSize(ff.getSize()),fileName, ff.getSize());
                    new Thread(bar).start();
                    ftpClient.retrieveFile(ff.getName(), is);
                    logger.info("文件："+fileName+" 下载成功");
                    new Thread(bar).stop();
                    is.close();
                    flag = true;

                }
            }
            //ftp.logout();

            if (!flag) {
                result = "文件: " + fileName + "不存在 ！";
            } else {
                result = "下载成功 ！";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                //ftp.disconnect();
            }
        }
        return result;
    }


    /**
     * 遍历解析文件夹下所有文件
     *
     * @param ftpClient  FTPClient对象
     * @param folderPath 需要解析的的文件夹
     */
    public static void readFileByFolder(org.apache.commons.net.ftp.FTPClient ftpClient, String folderPath) {
        int i = 0;
        logger.info(folderPath);
        try {
            ftpClient.changeWorkingDirectory(new String(folderPath.getBytes("GBK"), "ISO-8859-1"));
            //设置FTP连接模式
            ftpClient.enterLocalPassiveMode();
            //获取指定目录下文件文件对象集合
            FTPFile[] files = ftpClient.listFiles();
            logger.info(files.length);

            for (FTPFile file : files) {

                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.matches("(.*)_to_(.*)")) {
                        update_backpack[i] = fileName;
                        i++;
                        logger.info(fileName);
                        update_backpack_size = i;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件解析失败");
        }

    }

    /**
     * 检测服务器当前周目
     *
     * @param ftpClient FTPClient对象
     */
    public static String check_Weeks_orders(org.apache.commons.net.ftp.FTPClient ftpClient) {
        String folderPath = "";

        try {
            ftpClient.changeWorkingDirectory(new String(folderPath.getBytes("GBK"), "iso-8859-1"));
            //设置FTP连接模式
            ftpClient.enterLocalPassiveMode();
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                //判断为txt文件则解析
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        return fileName.substring(0, fileName.length() - 4);
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("检测服务器当前周目失败");
        }
        return null;
    }

}
