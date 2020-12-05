package top.ncserver.update;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author MakesYT
 */
public class download {

    public static void downloadHttpUrl(String surl, String dir, String fileName)  {
        HttpURLConnection conn = null;
        OutputStream oputstream = null;
        InputStream iputstream = null;
        try {
            //FileUtils.copyURLToFile(httpurl, new File(dir + fileName));
        URL url = new URL(surl+fileName);
        // 将url以open方法返回的urlConnection连接强转为HttpURLConnection连接(标识一个url所引用的远程对象连接)
        // 此时cnnection只是为一个连接对象,待连接中
        conn = (HttpURLConnection) url.openConnection();
        // 设置是否要从 URL连接读取数据,默认为true
        conn.setDoInput(true);
        // 建立连接
        // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
        conn.connect();
        // 连接发起请求,处理服务器响应 (从连接获取到输入流)
        iputstream = conn.getInputStream();
        // 创建文件输出流，用于保存下载的远程文件
        oputstream = new FileOutputStream(dir+fileName);
        //  用来存储响应数据
        byte[] buffer = new byte[1024];
        int byteRead = -1;
        //  循环读取流
            int c = 0;
        while ((byteRead = (iputstream.read(buffer))) != -1) {
            oputstream.write(buffer, 0, byteRead);
           c=c+byteRead;
           oputstream.flush();
           progress_bar.downloaded=c;
            progress_bar.downloadedA=progress_bar.getPrintSize(c);
        }
        System.out.println(c);
        //  输出完成后刷新并关闭流


    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            //  重要且易忽略步骤 (关闭流,切记!)
            if (iputstream != null) {
                iputstream.close();
            }
            if (oputstream != null) {
                oputstream.close();
            }
            // 销毁连接
            if (conn != null) {
                conn.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        //  输出完成后刷新并关闭流

    }

    public static long getFileSize(String stringUrl) throws IOException {

        URL url = new URL(stringUrl);
        URLConnection conn = url.openConnection();
        long size = conn.getContentLength();
        if (size < 0) {
            System.out.println("无法获取文件大小。");
        } else {
            System.out.println("文件大小为：" + size + " bytes");
            return size;
        }
        conn.getInputStream().close();
        return 1;
    }

}

