package top.ncserver.update;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class autoLogin {
    public static boolean autoLogin=false;
    public static String account;
    public static String password;
    public static void check() throws IOException {
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
        autoLogin=config.getBoolean("autoLogin");
    }
    public static void save(String account,String password)
    {
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
            config.remove("autoLogin");
            config.put("autoLogin","true");
            config.remove("account");
            config.put("account",account);
            config.remove("password");
            config.put("password",password);
            //System.out.println(file.toString());
            FileOutputStream fos= new FileOutputStream(System.getProperty("user.dir")+"\\config.json");
            OutputStreamWriter os= new OutputStreamWriter(fos);
            BufferedWriter w= new BufferedWriter(os);
            w.write(config.toString());
            w.close();
        }catch (JSONException | IOException ae) { }

    }
    public static void getUser() throws IOException {
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
        account=config.getString("account");
        password=config.getString("password");
    }
}
