package top.ncserver.update.MC_start;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.apache.commons.net.imap.IMAPClient;
import org.to2mbn.jmccc.launch.LaunchException;
import top.ncserver.update.Login;
import top.ncserver.update.MC_start.MC_Login;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MC_start {
    public static StringBuilder lib=new StringBuilder();
    public static void MC_start() {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //alwaysOnTop.setAlwaysOnTop(true);
        lib.append(" -cp ");
        String l=System.getProperty("user.dir");
        System.out.println(l);
        File file = new File(l+"\\.minecraft\\libraries");
        func(file);
        lib.append(l+"\\.minecraft\\versions\\PO3-3.3.59\\PO3-3.3.59.jar");
        String start="";
        start="\"" + System.getProperty("java.home") + "\\bin\\java.exe" + "\""+
                " -Dminecraft.client.jar="+l+"\\.minecraft\\versions\\PO3-3.3.59\\PO3-3.3.59.jar"+
        " -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=16M" +
                " -XX:-UseAdaptiveSizePolicy -XX:-OmitStackTraceInFastThrow -Xmn128m -Xmx6122m" +
                " -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true" +
                " -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump"+
                " -Djava.library.path="+l+"\\.minecraft\\versions\\PO3-3.3.59\\natives"+
                " -Dminecraft.launcher.brand=Ncharge_launcher -Dminecraft.launcher.version=3.0-beta"+lib.toString()+
                " -javaagent:C:\\Users\\cc\\AppData\\Roaming\\.hmcl\\authlib-injector.jar=https://www.ncserver.top:666/api/yggdrasil/" +
                " -Dauthlibinjector.side=client -Dauthlibinjector.yggdrasil.prefetched=" +
                "eyJtZXRhIjp7InNlcnZlck5hbWUiOiJOX2NoYXJnZSBNQ1x1NTkxNlx1N2Y2ZVx1NzY3Ylx1NWY1NSIsImltcGxlbWVudGF0aW9uTmFtZSI6IllnZ2RyYXNpbCBBUEkgZm9yIEJsZXNzaWnIFNraW4iLCJpbXBsZW1lbnRhdGlvblZlcnNpb24iOiI0LjExLjAiLCJsaW5rcyI6eyJob21lcGFnZSI6Imh0dHBzOlwvXC93d3cubmNzZXJ2ZXIudG9wOjY2NiIsInJlZ2lzdGVyIjoiaHR0cHM6XC9cL3d3dyuY3NlcnZlci50b3A6NjY2XC9hdXRoXC9yZWdpc3RlciJ9LCJmZWF0dXJlLm5vbl9lbWFpbF9sb2dpbiI6dHJ1ZX0sInNraW5Eb21haW5zIjpbInd3dy5uY3NlcnZlci50b3AiXSwic2lnbmF0dXJlUHVibGlja2V5IjoiLS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS1cbk1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0BZzhBTUlJQ0NnS0NBZ0VBeWZyNjRHT2JxdmREQ0VyOEVPUkFcblBpODlWTGJQNU5XclJob0FsNzJnakttNG9SamluWnZYUytHNmdEK1pMYm92eUxWaDdKS0pJRzVCUjlIclZMY0tcbklvcCswU243SVBRSjhcL1yS3VSSGpic2JUQVNES0tXVGRMc0JwMzNxNHlIQjAwUmk3MVNuTGEra1B0VnhQTmRwXG56UTROeTRzNTRzYkJ6N05aYzQ4cld1aHhEbWtlOHlqcjJxbFdDQXBLVkdWeDFhQms1VjFveWh4XC94UWdVS1pSZFxud1h4NWFWa2RjY0N3Tjl5ZzZJOUxjSE9rZ3VjU0JQdjg1NlJTZlNOdkdsdVhXU3RWUVdNWEs1VVwvblhTalRN0dcbjlsV2I1QnhPUmpjeHRMMUhZcGdib1FqdVU1b2hNZSt2ZExHK2ZSanhMTWZUN0tSWDNPNlF6WTI4Z2VPT3p3WXpcbkxIbDJXTEhISGVNd2JEMm1ieDkyVkJjS2xmTDBQNHV4ZXF4b2ZhamU5RHJZVUhjVW83ZkZtQXZUc3RRTTRUM0hcbkZcLzlhNnV6bEdEdXUwenhGOWNaQnpncnJcL3YwNFE4ZnhOSHROOVlGOXwZlJrOTRvRzhBVytJTkJCYWdNYVNuQ1xuVDhcL1dhS1o5S1JFK0EyTlhYWG9nUTU1amk5TXZ0UHo2Uk1qUFA1a2VHSE1lbzFtc1Y3ZU9MTFdkZFphK3FYTk5cblpnRlRxeWlzelhGeFFNZlFVNENERzJkRWx1Rnyd1N6azFjTFU3elh6ZTBWT2V1WitCcm9WbmlaZnV6elJMUFNcbk9sQ1NJQlhDKzV0ZWd3eVdZNUJpTXNKV2FaZ295SGlWamlYekVpUnhpb3F6UkZuSitzUUlIWlhacjZRWnJVcGpcbkxqUG9BS0E5azlCRnd3QWFuSXlqMXprQ0F3RUFBUT09XG4tLS0tLUVORCBQVUJMSUMgS0VZLS0tLS1cbiJ9"+
                " net.minecraft.launchwrapper.Launch"+
            " --username "+ Login.userName+
            " --version \"Ncharge client/3.0beta\""+
            " --gameDir "+l+"\\.minecraft\\versions\\PO3-3.3.59\\PO3-3.3.59.jar"+
            " --assetsDir "+l+"\\.minecraft\\assets"+" --assetIndex 1.12"+
            "--uuid "+ MC_Login.uuid+" --accessToken "+MC_Login.MC_accessToken+
            " --userType mojang " +
                "--tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker " +
                "--versionType Forge --width 854 --height 480";
        System.out.println(start);
        try {
            Runtime.getRuntime().exec(start);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void func(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
            {
                func(f);
            }
            if(f.isFile())		//若是文件，直接打印
            {
                lib.append(f+";");
            }
        }
    }

}
/*
 --userType mojang --tweakClass net.minecraftforge.fml.common.
launcher.FMLTweaker --versionType Forge --width 854 --height 480
*/