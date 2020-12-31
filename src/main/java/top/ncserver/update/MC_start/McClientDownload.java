package top.ncserver.update.MC_start;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import top.ncserver.update.Info;
import top.ncserver.update.download;
import top.ncserver.update.progress_bar;
import top.ncserver.update.zip;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

/**
 * @author MakesYT
 */
public class McClientDownload implements Runnable{
    static final Logger logger = Logger.getLogger(McClientDownload.class);
    public  McClientDownload()  {

         }
    @Override
    public void run() {
        File client = new File(System.getProperty("user.dir")+"//.minecraft");
        if (client.exists())
        {
            //System.out.println(client);
            logger.info("正在删除旧客户端");
            Info.type=1;
            Info msg=new Info("正在删除旧客户端");
            new Thread(msg).start();
            deleteAll(client);
            Info.setVisible(false);
            //deleteFile(client);
        }
        progress_bar bar = null;
        try {
            bar = new progress_bar("http://download.ncserver.top:8000/update/C",getClientVersion()+".zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(bar).start();

        try {
            Info msg=new Info("正在下载客户端");
            new Thread(msg).start();
            download.downloadHttpUrl("http://download.ncserver.top:8000/update/C",System.getProperty("user.dir"),"/"+getClientVersion()+".zip");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            zip.decompressZip(System.getProperty("user.dir") + "/" + getClientVersion() + ".zip",System.getProperty("user.dir")+"/");

        } catch (IOException | ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        JSONObject config;
        try {
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
            config.remove("C_version");
            config.put("C_version",getClientVersion());
            //System.out.println(file.toString());
            FileOutputStream fos= new FileOutputStream(System.getProperty("user.dir")+"\\config.json");
            OutputStreamWriter os= new OutputStreamWriter(fos);
            BufferedWriter w= new BufferedWriter(os);
            w.write(config.toString());
            w.close();
        }catch (JSONException | IOException ae) { }

    }
    public static String getClientVersion() throws IOException {
        download.downloadHttpUrl("http://download.ncserver.top:8000/update/",System.getProperty("user.dir")+"\\temp\\","config.json");
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
            //System.out.println(config.getString("C_version"));
            return config.getString("C_version");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void deleteAll(File file) {

        if (file.isFile() || Objects.requireNonNull(file.list()).length == 0) {
            file.delete();
        } else {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                deleteAll(f); // 递归删除每一个文件

            }
            file.delete(); // 删除文件夹
        }
    }
    public static boolean deleteFile(File dirFile) {
        // 如果dir对应的文件不存在，则退出

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : Objects.requireNonNull(dirFile.listFiles())) {
                deleteFile(file);
            }
        }

        return dirFile.delete();
    }
}
