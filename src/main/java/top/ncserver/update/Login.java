package top.ncserver.update;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author MakesYT
 */
public class Login {
    public static final Logger logger = Logger.getLogger(Login.class);
    public static final String USER_AGENT = "Ncharge client/3.0beta";
    public static String token;
    public static String user_email;
    public static String password;
    public static String userName;
    public static InputStream uservia;
    public static void Login() throws Exception {
        if (firstLogin()) {
            UI.main.setVisible(false);
            userGet();
            viaGet();
            UI.UI();
        }else {
            JOptionPane.showMessageDialog(INIT.alwaysOnTop, "登陆失败", "错误", JOptionPane.ERROR_MESSAGE);
        }

    }
    public static boolean firstLogin() throws Exception {
        String url = "https://www.ncserver.top:666/api/auth/login";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Accept", "application/json");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        user_email=UI.user_jtextField.getText();
        password=String.valueOf(UI.pass_jpassField.getPassword());
        urlParameters.add(new BasicNameValuePair("email", user_email));
        urlParameters.add(new BasicNameValuePair("password",password ));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        JSONObject result=new JSONObject(rd.readLine());
        token=result.getString("token");
        if (token.length()>=2) {
            return true;
        } else {
            return false;
        }
    }
    public static void userGet() throws Exception {
        String url = "https://www.ncserver.top:666/api/players";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("User-Agent", USER_AGENT);
        get.setHeader("Accept", "application/json");
        get.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        HttpResponse response = client.execute(get);
        BufferedReader rd2 = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        String temp = rd2.readLine();
        //System.out.println(temp);
        String result =temp.substring(1,temp.length()-1);
        //System.out.println(result);
        JSONObject result2=new JSONObject(result);
        userName=result2.getString("name");
    }
    public static void viaGet() throws Exception {
        String url = "https://www.ncserver.top:666/avatar/player/"+userName+"?png";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("User-Agent", USER_AGENT);
        get.setHeader("Accept", "application/json");
        get.setHeader("png", "");
        get.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        //get.add(new BasicNameValuePair("email", user_email));
        HttpResponse response = client.execute(get);
        uservia= response.getEntity().getContent();
        int index;
        byte[] bytes = new byte[1024];
        File file = null;
        try {
            file = new File("C:\\Windows\\Temp\\Ncharge_client");
            if (!file.exists()) {
                file.mkdirs();
            }
        }catch (Exception ignored)
        {

        }

            FileOutputStream downloadFile = new FileOutputStream("C:\\Windows\\Temp\\Ncharge_client\\via.png");
        while ((index = uservia.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        uservia.close();
    }

}
