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

import javax.swing.*;

/**
 * 简单操作FTP工具类 ,此工具类支持中文文件名，不支持中文目录
 * 如果需要支持中文目录，需要 new String(path.getBytes("UTF-8"),"ISO-8859-1") 对目录进行转码
 *
 *
 */

public class FTP {
    public static FTPClient ftp = null;
    public static final Logger logger = Logger.getLogger(FTP.class);
    public static String[] update_backpack=new String[30];
    public static int update_backpack_size=0;
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
     * @return boolean
     */
    public static boolean closeFTP(FTPClient ftp){

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
     * 从FTP服务器下载文件
     * @param ftp
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static String downFile(FTPClient ftp,
                                  String remotePath,String fileName,String localPath) {

        String result = "下载失败 ！";
        try {
            ftp.changeWorkingDirectory(remotePath); // 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            boolean flag = false; // 下载文件不存在
            for(FTPFile ff:fs){
                if(ff.getName().equals(fileName)){
                    File localFile = new File(localPath+"/"+ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                    flag = true;
                }
            }
            //ftp.logout();

            if(!flag){
                result = "文件: "+fileName+"不存在 ！";
            }else{
                result = "下载成功 ！";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                //ftp.disconnect();
            }
        }
        return result;
    }


    /**
     * 遍历解析文件夹下所有文件
     * @param ftp FTPClient对象
     * @param folderPath 需要解析的的文件夹
     */
    public static void readFileByFolder(FTPClient ftp, String folderPath){
        int i=0;
        logger.info(folderPath);
        try {
            ftp.changeWorkingDirectory(new String(folderPath.getBytes("GBK"),"ISO-8859-1"));
            //设置FTP连接模式
            ftp.enterLocalPassiveMode();
            //获取指定目录下文件文件对象集合
            FTPFile[] files = ftp.listFiles();
            logger.info(files.length);

            for (FTPFile file : files) {

                if(file.isFile()){
                    String fileName = file.getName();
                    if (fileName.matches("(.*)_to_(.*)"))
                    {update_backpack[i]=fileName;i++;logger.info(fileName);update_backpack_size=i;}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件解析失败");
        }

    }
    /**
     * 检测服务器当前周目
     * @param ftp FTPClient对象
     */
    public static String check_Weeks_orders(FTPClient ftp) {
        String folderPath ="";

       try {
           ftp.changeWorkingDirectory(new String(folderPath.getBytes("GBK"),"iso-8859-1"));
           //设置FTP连接模式
           ftp.enterLocalPassiveMode();
           FTPFile[] files = ftp.listFiles();
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
