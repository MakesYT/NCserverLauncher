package top.ncserver.update.MC_start;

import org.json.JSONObject;
import top.ncserver.update.Login;
import top.ncserver.update.MC_start.MC_Login;
import top.ncserver.update.Mysetting.MyJFrame;
import top.ncserver.update.json_init;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
/**
 * @author MakesYT
 */
public class MC_start {
    public static StringBuilder lib=new StringBuilder();
    public static void MC_start() throws IOException {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //alwaysOnTop.setAlwaysOnTop(true);
        String path=System.getProperty("user.dir")+"\\config.json";
        String str = "";
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\config.json");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String tempString = null;
        while((tempString = reader.readLine()) != null){
            str += tempString;
        }
        //System.out.println(str);
        JSONObject config=new JSONObject(str);
        String java= config.getString("java");
        int RAM=config.getInt("RAM");
        if ("auto".equals(java)) {
            java=System.getProperty("java.home") + "\\bin\\java.exe";
        }
        lib.append(" -cp ");
        String l=System.getProperty("user.dir");
        System.out.println(l);
        func(l);
        String start="";
        start="set APPDATA="+l+"\r\n"+"cd /D "+l+"\\.minecraft\\versions\\PO3-3.3.59\\\r\n"+"\"" + java + "\""+
                " -Dminecraft.client.jar="+l+"\\.minecraft\\versions\\PO3-3.3.59\\PO3-3.3.59.jar"+
        " -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=16M" +
                " -XX:-UseAdaptiveSizePolicy -XX:-OmitStackTraceInFastThrow -Xmn128m -Xmx"+RAM+"m" +
                " -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true" +
                " -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump"+
                " -Djava.library.path="+l+"\\.minecraft\\versions\\PO3-3.3.59\\natives"+
                " -Dminecraft.launcher.brand=Ncharge_launcher -Dminecraft.launcher.version=3.0-beta"+lib.toString()+
                " -javaagent:"+l+"\\.minecraft\\authlib-injector.jar=https://www.ncserver.top:666/api/yggdrasil/" +
                " -Dauthlibinjector.side=client -Dauthlibinjector.yggdrasil.prefetched=" +
                "eyJtZXRhIjp7InNlcnZlck5hbWUiOiJOX2NoYXJnZSBNQ1x1NTkxNlx1N2Y2ZVx1NzY3Ylx1NWY1NSIsImltcGxlbWVudGF0aW9uTmFtZSI6IllnZ2RyYXNpbCBBUEkgZm9yIEJsZXNzaW5nIFNraW4iLCJpbXBsZW1lbnRhdGlvblZlcnNpb24iOiI0LjExLjAiLCJsaW5rcyI6eyJob21lcGFnZSI6Imh0dHBzOlwvXC93d3cubmNzZXJ2ZXIudG9wOjY2NiIsInJlZ2lzdGVyIjoiaHR0cHM6XC9cL3d3dy5uY3NlcnZlci50b3A6NjY2XC9hdXRoXC9yZWdpc3RlciJ9LCJmZWF0dXJlLm5vbl9lbWFpbF9sb2dpbiI6dHJ1ZX0sInNraW5Eb21haW5zIjpbInd3dy5uY3NlcnZlci50b3AiXSwic2lnbmF0dXJlUHVibGlja2V5IjoiLS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS1cbk1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBeWZyNjRHT2JxdmREQ0VyOEVPUkFcblBpODlWTGJQNU5XclJob0FsNzJnakttNG9SamluWnZYUytHNmdEK1pMYm92eUxWaDdKS0pJRzVCUjlIclZMY0tcbklvcCswU243SVBRSjhcL1gyS3VSSGpic2JUQVNES0tXVGRMc0JwMzNxNHlIQjAwUmk3MVNuTGEra1B0VnhQTmRwXG56UTROeTRzNTRzYkJ6N05aYzQ4cld1aHhEbWtlOHlqcjJxbFdDQXBLVkdWeDFhQms1VjFveWh4XC94UWdVS1pSZFxud1h4NWFWa2RjY0N3Tjl5ZzZJOUxjSE9rZ3VjU0JQdjg1NlJTZlNOdkdsdVhXU3RWUVdNWEs1VVwvblhTalhRN0dcbjlsV2I1QnhPUmpjeHRMMUhZcGdib1FqdVU1b2hNZSt2ZExHK2ZSanhMTWZUN0tSWDNPNlF6WTI4Z2VPT3p3WXpcbkxIbDJXTEhISGVNd2JEMm1ieDkyVkJjS2xmTDBQNHV4ZXF4b2ZhamU5RHJZVUhjVW83ZkZtQXZUc3RRTTRUM0hcbkZcLzlhNnV6bEdEdXUwenhGOWNaQnpncnJcL3YwNFE4ZnhOSHROOVlGOXYwZlJrOTRvRzhBVytJTkJCYWdNYVNuQ1xuVDhcL1dhS1o5S1JFK0EyTlhYWG9nUTU1amk5TXZ0UHo2Uk1qUFA1a2VHSE1lbzFtc1Y3ZU9MTFdkZFphK3FYTk5cblpnRlRxeWlzelhGeFFNZlFVNENERzJkRWx1Rnoyd1N6azFjTFU3elh6ZTBWT2V1WitCcm9WbmlaZnV6elJMUFNcbk9sQ1NJQlhDKzV0ZWd3eVdZNUJpTXNKV2FaZ295SGlWamlYekVpUnhpb3F6UkZuSitzUUlIWlhacjZRWnJVcGpcbkxqUG9BS0E5azlCRnd3QWFuSXlqMXprQ0F3RUFBUT09XG4tLS0tLUVORCBQVUJMSUMgS0VZLS0tLS1cbiJ9"+
                " net.minecraft.launchwrapper.Launch"+
            " --username "+ Login.userName+
            " --version \"Ncharge client/3.0beta\""+
            " --gameDir "+l+"\\.minecraft\\versions\\PO3-3.3.59"+
            " --assetsDir "+l+"\\.minecraft\\assets"+" --assetIndex 1.12"+
            " --uuid "+ MC_Login.uuid+" --accessToken "+MC_Login.MC_accessToken+
            " --userType mojang " +
                "--tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker " +
                "--versionType Forge --width 854 --height 480";
        System.out.println(start);
        //start=start.replace(" ","\u0009");
        FileOutputStream fos= new FileOutputStream("C:\\Windows\\Temp\\Ncharge_client\\start.bat");
        OutputStreamWriter os= new OutputStreamWriter(fos);
        BufferedWriter w= new BufferedWriter(os);
        w.write(start);
        w.close();
        //Writer downloadFile = new OutputStreamWriter(new FileOutputStream("C:\\Windows\\Temp\\Ncharge_client\\start.bat"), StandardCharsets.US_ASCII);
        //downloadFile.write(start);
        //downloadFile.flush();
        //downloadFile.close();

        try {
            Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start C:\\Windows\\Temp\\Ncharge_client\\start.bat");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void func(String l){
       String lib_init="%lib%\\.minecraft\\libraries\\net\\minecraftforge\\forge\\1.12.2-14.23.5.2847\\forge-1.12.2-14.23.5.2847.jar;%lib%\\.minecraft\\libraries\\net\\minecraft\\launchwrapper\\1.12\\launchwrapper-1.12.jar;%lib%\\.minecraft\\libraries\\org\\ow2\\asm\\asm-all\\5.2\\asm-all-5.2.jar;%lib%\\.minecraft\\libraries\\org\\jline\\jline\\3.5.1\\jline-3.5.1.jar;%lib%\\.minecraft\\libraries\\net\\java\\dev\\jna\\jna\\4.4.0\\jna-4.4.0.jar;%lib%\\.minecraft\\libraries\\com\\typesafe\\akka\\akka-actor_2.11\\2.3.3\\akka-actor_2.11-2.3.3.jar;%lib%\\.minecraft\\libraries\\com\\typesafe\\config\\1.2.1\\config-1.2.1.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-actors-migration_2.11\\1.1.0\\scala-actors-migration_2.11-1.1.0.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-compiler\\2.11.1\\scala-compiler-2.11.1.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\plugins\\scala-continuations-library_2.11\\1.0.2\\scala-continuations-library_2.11-1.0.2.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\plugins\\scala-continuations-plugin_2.11.1\\1.0.2\\scala-continuations-plugin_2.11.1-1.0.2.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-library\\2.11.1\\scala-library-2.11.1.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-parser-combinators_2.11\\1.0.1\\scala-parser-combinators_2.11-1.0.1.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-reflect\\2.11.1\\scala-reflect-2.11.1.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-swing_2.11\\1.0.1\\scala-swing_2.11-1.0.1.jar;%lib%\\.minecraft\\libraries\\org\\scala-lang\\scala-xml_2.11\\1.0.2\\scala-xml_2.11-1.0.2.jar;%lib%\\.minecraft\\libraries\\lzma\\lzma\\0.0.1\\lzma-0.0.1.jar;%lib%\\.minecraft\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar;%lib%\\.minecraft\\libraries\\java3d\\vecmath\\1.5.2\\vecmath-1.5.2.jar;%lib%\\.minecraft\\libraries\\net\\sf\\trove4j\\trove4j\\3.0.3\\trove4j-3.0.3.jar;%lib%\\.minecraft\\libraries\\org\\apache\\maven\\maven-artifact\\3.5.3\\maven-artifact-3.5.3.jar;%lib%\\.minecraft\\libraries\\com\\mojang\\patchy\\1.1\\patchy-1.1.jar;%lib%\\.minecraft\\libraries\\oshi-project\\oshi-core\\1.1\\oshi-core-1.1.jar;%lib%\\.minecraft\\libraries\\net\\java\\dev\\jna\\platform\\3.4.0\\platform-3.4.0.jar;%lib%\\.minecraft\\libraries\\com\\ibm\\icu\\icu4j-core-mojang\\51.2\\icu4j-core-mojang-51.2.jar;%lib%\\.minecraft\\libraries\\com\\paulscode\\codecjorbis\\20101023\\codecjorbis-20101023.jar;%lib%\\.minecraft\\libraries\\com\\paulscode\\codecwav\\20101023\\codecwav-20101023.jar;%lib%\\.minecraft\\libraries\\com\\paulscode\\libraryjavasound\\20101123\\libraryjavasound-20101123.jar;%lib%\\.minecraft\\libraries\\com\\paulscode\\librarylwjglopenal\\20100824\\librarylwjglopenal-20100824.jar;%lib%\\.minecraft\\libraries\\com\\paulscode\\soundsystem\\20120107\\soundsystem-20120107.jar;%lib%\\.minecraft\\libraries\\io\\netty\\netty-all\\4.1.9.Final\\netty-all-4.1.9.Final.jar;%lib%\\.minecraft\\libraries\\com\\google\\guava\\guava\\21.0\\guava-21.0.jar;%lib%\\.minecraft\\libraries\\org\\apache\\commons\\commons-lang3\\3.5\\commons-lang3-3.5.jar;%lib%\\.minecraft\\libraries\\commons-io\\commons-io\\2.5\\commons-io-2.5.jar;%lib%\\.minecraft\\libraries\\commons-codec\\commons-codec\\1.10\\commons-codec-1.10.jar;%lib%\\.minecraft\\libraries\\net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar;%lib%\\.minecraft\\libraries\\net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar;%lib%\\.minecraft\\libraries\\com\\google\\code\\gson\\gson\\2.8.0\\gson-2.8.0.jar;%lib%\\.minecraft\\libraries\\com\\mojang\\authlib\\1.5.25\\authlib-1.5.25.jar;%lib%\\.minecraft\\libraries\\com\\mojang\\realms\\1.10.22\\realms-1.10.22.jar;%lib%\\.minecraft\\libraries\\org\\apache\\commons\\commons-compress\\1.8.1\\commons-compress-1.8.1.jar;%lib%\\.minecraft\\libraries\\org\\apache\\httpcomponents\\httpclient\\4.3.3\\httpclient-4.3.3.jar;%lib%\\.minecraft\\libraries\\commons-logging\\commons-logging\\1.1.3\\commons-logging-1.1.3.jar;%lib%\\.minecraft\\libraries\\org\\apache\\httpcomponents\\httpcore\\4.3.2\\httpcore-4.3.2.jar;%lib%\\.minecraft\\libraries\\it\\unimi\\dsi\\fastutil\\7.1.0\\fastutil-7.1.0.jar;%lib%\\.minecraft\\libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.8.1\\log4j-api-2.8.1.jar;%lib%\\.minecraft\\libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.8.1\\log4j-core-2.8.1.jar;%lib%\\.minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl\\2.9.4-nightly-20150209\\lwjgl-2.9.4-nightly-20150209.jar;%lib%\\.minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl_util\\2.9.4-nightly-20150209\\lwjgl_util-2.9.4-nightly-20150209.jar;%lib%\\.minecraft\\libraries\\com\\mojang\\text2speech\\1.10.3\\text2speech-1.10.3.jar;%lib%\\.minecraft\\versions\\PO3-3.3.59\\PO3-3.3.59.jar";
        lib.append(lib_init.replace("%lib%",l));
    }

}
/*
 --userType mojang --tweakClass net.minecraftforge.fml.common.
launcher.FMLTweaker --versionType Forge --width 854 --height 480
*/