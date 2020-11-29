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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;
import top.ncserver.update.Login;
import top.ncserver.update.download;

public class MC_Login {
    public static final String USER_AGENT = "Ncharge client/3.0beta";
    public static String MC_accessToken;
    public static String uuid;//http://download.ncserver.top:8000/update/authlib-injector.jar
    public static void MC_init() throws Exception {
        authlib_injector();
        MC_Login();
        MC_start.MC_start();
    }
    public static void authlib_injector()
    {
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
        MC_accessToken=result.getString("accessToken");
        JSONObject result1=new JSONObject(result.getJSONArray("availableProfiles"));
        JSONArray uuidJSON=result.getJSONArray("availableProfiles");
        String uuidString=uuidJSON.toString();
        uuidString=uuidString.substring(1, uuidString.length()-1);
        JSONObject uuidJSON2=new JSONObject(uuidString);
        uuid=uuidJSON2.getString("id");
        System.out.println(result.getString("accessToken"));
        System.out.println(uuid);
        //System.out.println(rd.readLine());
        if (MC_accessToken.length()>=2) {
            return true;
        } else {
            return false;
        }

    }
}
