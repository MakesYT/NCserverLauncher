package top.ncserver.update;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
/**
 * @author MakesYT
 */
public class json_init {
    public static JSONObject config;
    private static String path=System.getProperty("user.dir")+"\\config.json";
    public static void init() throws IOException {
        File configFile =new File(path);
        if (!configFile.exists()) {
            create();
        }else
        {
            try {
                String str = "";
                FileInputStream fileInputStream = new FileInputStream(path);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String tempString = null;
                while((tempString = reader.readLine()) != null){
                    str += tempString;
                }
                //System.out.println(str);
                config=new JSONObject(str);
            } catch (JSONException e) {
                //create();
                //e.printStackTrace();
            }
        }
        {


        }
        //System.out.println(s);
    }
    public static void create()  {
        HashMap<String,Object> person=new HashMap<String,Object>();
        person.put("version","3.0.0-beta");
        person.put("C_version","6.1.0");
        person.put("java","auto");
        person.put("RAM",4096);
        person.put("desc","1.游戏启动支持。");
        String s=new JSONObject(person).toString();

        try {
            FileOutputStream fos= new FileOutputStream(path);
            OutputStreamWriter os= new OutputStreamWriter(fos);
            BufferedWriter w= new BufferedWriter(os);
            w.write(s);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
