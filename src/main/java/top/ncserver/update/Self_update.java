package top.ncserver.update;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;

public class Self_update {
    static Logger logger = Logger.getLogger(Self_update.class);
    public static void checkSelf() throws IOException, InterruptedException {


    logger.info("开始检测更新...");
    if (!getLocalVersion().equals(getVersion()))
        {if (!download.downloadHttpUrl("http://download.ncserver.top:8000/update",
                System.getProperty("user.dir"),"/update.jar"))
        {
            logger.error("下载更新器失败");
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "下载更新器失败", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }else {
            logger.info("下载更新器成功");
        }
            logger.info("检测到更新开始更新...");
            Info msg=new Info("检测到更新,正在更新");
            new Thread(msg).start();
            Thread.sleep(2000);
            Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k java -jar "+System.getProperty("user.dir")+"\\update.jar");
            System.exit(1);
        } else {
        logger.info("无需更新");
    }
    }
    public static String getLocalVersion()  {
        try {
            JSONObject config;
            String str = "";
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\config.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                str += tempString;
            }
            //System.out.println(str);
            config=new JSONObject(str);
            return config.getString("version");
        }catch (Exception e)
        {
            logger.error(e);
            logger.error("读取本地版本失败");
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "读取本地版本失败", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }
    public static String getVersion() {
        if(!download.downloadHttpUrl("http://download.ncserver.top:8000/update/",
                System.getProperty("user.dir")+"\\temp\\","config.json"))
        {
            logger.error("获取远程配置文件失败");
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "获取远程配置文件失败", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            String str = "";
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\temp\\config.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                str += tempString;
            }
            //System.out.println(str);
            JSONObject config=new JSONObject(str);
            return config.getString("version");
        } catch (JSONException | IOException e) {
            logger.error(e);
            logger.error("读取最新版本失败");
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "读取最新版本失败", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }
}
