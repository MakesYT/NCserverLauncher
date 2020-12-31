package top.ncserver.update.MC_start;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;
import top.ncserver.update.INIT;
import top.ncserver.update.Info;
import top.ncserver.update.Login;
import top.ncserver.update.download;

import javax.swing.*;

/**
 * @author MakesYT
 */
public class MC_Login {
    public static final String USER_AGENT = "Ncharge client/3.0.6";
    static Logger logger = Logger.getLogger(MC_Login.class);
    public static String MC_accessToken;
    public static String uuid;
    public static void MC_init() throws Exception {
        if (DetectClient()) {
            authlib_injector();
            MC_Login();
            MC_start.MC_start();
        }else
        {
            Info msg=new Info("尚未安装客户端,请先安装客户端");
            new Thread(msg).start();
        }

    }
    public static boolean DetectClient()
    {
        JSONObject config = null;
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
        } catch (JSONException | FileNotFoundException | UnsupportedEncodingException e) {
            logger.error(e);
            logger.error("检查客户端失败");
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "检查客户端失败", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!config.getString("C_version").equals("null")) {
            return true;
        }
        return false;
    }
    public static void authlib_injector() throws IOException {
        File authlib_file=new File(System.getProperty("user.dir")+"\\.minecraft\\authlib-injector.jar");
        if (!authlib_file.exists())
        {
            download.downloadHttpUrl("http://download.ncserver.top:8000/update/",System.getProperty("user.dir")+"\\.minecraft\\","authlib-injector.jar");
        }
    }

    public static boolean MC_Login() throws Exception {
        String url = "https://www.ncserver.top:666/api/yggdrasil/authserver/authenticate/";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/json");

        String jsonString="{\"username\":\""+ Login.user_email+"\",\"password\":\""+Login.password+"\",\"requestUser\":false,\"agent\":{\"name\":\"Minecraft\",\"version\":1}}";
        JSONObject json = new JSONObject(jsonString);
        StringEntity s = new StringEntity(json.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        post.setEntity(s);
        //post.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        JSONObject result=new JSONObject(rd.readLine());
        try{
            MC_accessToken=result.getString("accessToken");
        }catch (JSONException e)
        {
            logger.error(e);
            logger.error(result.getString("errorMessage"));
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, result.getString("errorMessage"), "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }


        JSONObject result1=new JSONObject(result.getJSONArray("availableProfiles"));
        JSONArray uuidJSON=result.getJSONArray("availableProfiles");
        String uuidString=uuidJSON.toString();
        uuidString=uuidString.substring(1, uuidString.length()-1);
        JSONObject uuidJSON2=new JSONObject(uuidString);
        uuid=uuidJSON2.getString("id");
        //System.out.println(rd.readLine());
        if (MC_accessToken.length()>=2) {
            return true;
        } else {
            return false;
        }

    }
}
