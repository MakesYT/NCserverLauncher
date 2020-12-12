package top.ncserver.update;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class Self_update {
    public static void checkSelf() throws IOException {
        File selfUpdate=new File(System.getProperty("user.dir")+"\\update.jar");

            download.downloadHttpUrl("http://download.ncserver.top:8000/update",System.getProperty("user.dir"),"/update.jar");

        if (!getLocalVersion().equals(getVersion()))
        {
            Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k java -jar "+System.getProperty("user.dir")+"\\update.jar");
            System.exit(1);
        }
    }
    public static String getLocalVersion() throws IOException {
        JSONObject config;
        String str = "";
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\config.json");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String tempString = null;
        while((tempString = reader.readLine()) != null){
            str += tempString;
        }
        System.out.println(str);
        config=new JSONObject(str);
        return config.getString("version");
    }
    public static String getVersion() throws IOException {
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
            return config.getString("version");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
