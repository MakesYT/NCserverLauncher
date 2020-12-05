package top.ncserver.update.MC_start;

import org.json.JSONException;
import org.json.JSONObject;
import top.ncserver.update.download;
import top.ncserver.update.progress_bar;

import java.io.*;

/**
 * @author MakesYT
 */
public class McClientDownload {
    public static void mcClientDownloadInit() throws IOException {
        File client = new File(System.getProperty("user.dir")+".minecraft");
        if (client.exists())
        {
            deleteFile(client);
        }
        progress_bar bar = new progress_bar("http://download.ncserver.top:8000/update/C",getClientVersion()+".zip");
        new Thread(bar).start();
        download.downloadHttpUrl("http://download.ncserver.top:8000/update/C",System.getProperty("user.dir"),"/"+getClientVersion()+".zip");

    }
    public static String getClientVersion() throws IOException {
        download.downloadHttpUrl("http://download.ncserver.top:8000/update/","C:\\Windows\\Temp\\Ncharge_client\\","config.json");
        try {
            String str = "";
            FileInputStream fileInputStream = new FileInputStream("C:\\Windows\\Temp\\Ncharge_client\\config.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                str += tempString;
            }
            //System.out.println(str);
            JSONObject config=new JSONObject(str);
            System.out.println(config.getString("C_version"));
            return config.getString("C_version");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean deleteFile(File dirFile) {
        // 如果dir对应的文件不存在，则退出

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }

        return dirFile.delete();
    }
}
