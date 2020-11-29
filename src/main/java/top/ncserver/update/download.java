package top.ncserver.update;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class download {
    public static void downloadHttpUrl(String url, String dir, String fileName) {
        try {
            URL httpurl = new URL(url+fileName);
            FileUtils.copyURLToFile(httpurl, new File(dir + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
