package top.ncserver.update;

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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;
public class MC_Login {
    public static final String USER_AGENT = "Ncharge client/3.0beta";
    public static String MC_accessToken;
    public static String uuid;
    public static void MC_init()
    {

    }
    public static boolean MC_Login() throws Exception {
        String url = "https://www.ncserver.top:666/api/yggdrasil/authserver/authenticate/";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/json");

        String jsonString="{\"username\":\"2696703792@qq.com\",\"password\":\"lyx20060306\",\"requestUser\":false,\"agent\":{\"name\":\"Minecraft\",\"version\":1}}";
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
        //StringBuffer result = new StringBuffer();
        //String line = "";
        //while ((line = rd.readLine()) != null) {
        //    result.append(line);
        //}
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
