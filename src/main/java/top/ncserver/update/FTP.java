package top.ncserver.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 简单操作FTP工具类 ,此工具类支持中文文件名，不支持中文目录
 * 如果需要支持中文目录，需要 new String(path.getBytes("UTF-8"),"ISO-8859-1") 对目录进行转码
 * @author WZH
 *
 */

public class FTP {
    public static final Logger logger = Logger.getLogger(FTP.class);
    public static String[] update_backpack=new String[30];
    /**
     * 获取FTPClient对象
     * @param ftpHost 服务器IP
     * @param ftpPort 服务器端口号
     * @param ftpUserName 用户名
     * @param ftpPassword 密码
     * @return FTPClient
     */
    public static FTPClient getFTPClient(String ftpHost, int ftpPort,
                                         String ftpUserName, String ftpPassword) {

        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(ftpHost, ftpPort);
            // 设置用户名和密码
            ftp.login(ftpUserName, ftpPassword);
            // 设置连接超时时间,5000毫秒
            ftp.setConnectTimeout(50000);
            // 设置中文编码集，防止中文乱码
            ftp.setControlEncoding("GBK");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftp.disconnect();
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
        return ftp;
    }


    /**
     * 关闭FTP方法
     * @param ftp
     * @return
     */
    public boolean closeFTP(FTPClient ftp){

        try {
            ftp.logout();
        } catch (Exception e) {
            logger.error("FTP关闭失败");
        }finally{
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTP关闭失败");
                }
            }
        }

        return false;

    }


    /**
     * 下载FTP下指定文件
     * @param ftp FTPClient对象
     * @param filePath FTP文件路径
     * @param fileName 文件名
     * @param downPath 下载保存的目录
     * @return
     */
    public boolean downLoadFTP(FTPClient ftp, String filePath, String fileName,
                               String downPath) {
        // 默认失败
        boolean flag = false;

        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(filePath);
            // 获取目录下文件集合
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {
                    File downFile = new File(downPath + File.separator
                            + file.getName());
                    OutputStream out = new FileOutputStream(downFile);
                    // 绑定输出流下载文件,需要设置编码集，不然可能出现文件为空的情况
                    flag = ftp.retrieveFile(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"), out);
                    // 下载成功删除文件,看项目需求
                    // ftp.deleteFile(new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
                    out.flush();
                    out.close();
                    if(flag){
                        logger.info("下载成功");
                    }else{
                        logger.error("下载失败");
                    }
                }
            }

        } catch (Exception e) {
            logger.error("下载失败");
        }

        return flag;
    }


    /**
     * 遍历解析文件夹下所有文件
     * @param folderPath 需要解析的的文件夹
     * @param ftp FTPClient对象
     * @return
     */
    public static int readFileByFolder(FTPClient ftp, String folderPath){
        int i=0;
        logger.info(folderPath);
        try {

            ftp.changeWorkingDirectory(new String(folderPath.getBytes("GBK"),"ISO-8859-1"));
            //设置FTP连接模式
            ftp.enterLocalPassiveMode();
            //获取指定目录下文件文件对象集合
            FTPFile files[] = ftp.listFiles();
            logger.info(files.length);

            for (FTPFile file : files) {

                if(file.isFile()){
                    String fileName = file.getName();

                    if (fileName.indexOf("_to_") == -1){}
                    else
                    {update_backpack[i]=fileName;i++;}

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件解析失败");
        }

        return i;

    }
    /**
     * 检测服务器当前周目
     * @param ftp FTPClient对象
     */
    public static String check_Weeks_orders(FTPClient ftp) {
        String folderPath ="";

       try {
           ftp.changeWorkingDirectory(new String(folderPath.getBytes("UTF-8"),"iso-8859-1"));
           //设置FTP连接模式
           ftp.enterLocalPassiveMode();
           FTPFile files[] = ftp.listFiles();
           for (FTPFile file : files) {
               //判断为txt文件则解析
               if(file.isFile()){
                   String fileName = file.getName();
                   if(fileName.endsWith(".txt")){
                    return fileName.substring(0,fileName.length()-4);
                   }
               }

           }


       }catch (Exception e)
       {
           e.printStackTrace();
           logger.error("检测服务器当前周目失败");
       }
        return null;
    }

}
