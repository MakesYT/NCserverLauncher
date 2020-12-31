package top.ncserver.update;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
/**
 * @author MakesYT
 */
public class json_init {
    static Logger logger = Logger.getLogger(json_init.class);
    public static JSONObject config;
    private static String path=System.getProperty("user.dir")+"\\config.json";
    public static void init() throws IOException {
        logger.info("初始化配置文件..");
        File configFile =new File(path);
        if (!configFile.exists()) {
            logger.info("配置文件不存在开始创建");
            create();
            logger.info("文件创建完成");
        }
        {
            logger.info("读取配置文件...");
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
                logger.error(e);
                logger.error("读取配置文件失败");
                JOptionPane.showMessageDialog(INIT.alwaysOnTop, "读取配置文件失败", "错误", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        {


        }
        logger.info("配置读取完成");
    }
    public static void create()  {
        HashMap<String,Object> person=new HashMap<String,Object>();
        person.put("version","3.0.7.5");
        person.put("C_version","null");
        person.put("autoLogin","false");
        person.put("account","null");
        person.put("password","null");
        person.put("java","auto");
        person.put("RAM",4096);
        String s=new JSONObject(person).toString();

        try {
            FileOutputStream fos= new FileOutputStream(path);
            OutputStreamWriter os= new OutputStreamWriter(fos);
            BufferedWriter w= new BufferedWriter(os);
            w.write(s);
            w.close();
        } catch (IOException e) {
            logger.error(e);
            logger.error("生成配置文件失败");
            System.exit(1);
        }

    }

}
