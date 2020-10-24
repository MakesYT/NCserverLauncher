package top.ncserver.update;

import org.apache.log4j.Logger;

import java.io.*;

public class update_del {
    public static final Logger logger = Logger.getLogger(update_del.class);
    public static BufferedReader in;
    public static File File;

    public static void del() {
        File del=new File("del.txt");

        if(del.exists())
        try {
            in = new BufferedReader(new FileReader("del.txt"));
            StringBuffer l;
            while (in.ready()) {
                l = (new StringBuffer(in.readLine()));
                File = new File(".minecraft/" + l);
                if (File.delete())
                {
                    logger.info("删除旧文件：" + l + "成功");
                }

                else
                {
                    logger.error("删除旧文件：" + l + "失败");
                    System.exit(1);
                }

                //System.out.println(l);
            }
            in.close();
            del.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        else logger.info("无需删除文件，跳过旧文件删除");
    }
}


