/**
 * @author MakesYT
 */
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Config {
    static Logger logger=Logger.getLogger(Config.class);

    public static void loader()
    {

    }
    static void init() {
        Map<String, String> map=new LinkedHashMap<String, String>();
        map.put("firstJoin","true");
        map.put("update","false");
        map.put("LauncherVersion","4.0.0-Alpha");
        map.put("ClientVersion","null");
        map.put("java","auto");
        map.put("RAM","4096");
        map.put("autoLogin","false");
        map.put("account","null");
        map.put("password","null");
        try {
            FileWriter config=new FileWriter("config.json");
            config.write(JSON.toJSONString(map,true));
            config.flush();
            config.close();
        }catch (Exception ignored){

        }

    }
}
