package top.ncserver.update;

import org.json.JSONException;
import org.json.JSONObject;
import top.ncserver.update.Mysetting.MyJButton;
import top.ncserver.update.Mysetting.MyJFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class configUI {
    public static  void configUI()
    {
        MyJFrame configFrame= null;
        try {
            configFrame = new MyJFrame("config",280,300,2);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        class MyActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(1);
                JFileChooser chooser = new JFileChooser("C:\\Program Files\\Java");
                chooser.setFileFilter(new FileFilter(){
                    @Override
                    public boolean accept(File file){
                        // always accept directorys
                        if(file.isDirectory()) {
                            return true;
                        }
                        // but only files with specific name _SomeFixedFormat.def
                        return "java.exe".equals(file.getName());
                    }
                    @Override
                    public String getDescription() {
                        return "java.exe";
                    }
                });
                chooser.setSelectedFile(new File("auto"));
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showDialog(new JLabel(), "选择");
                File file = chooser.getSelectedFile();
                JSONObject config;
                try {
                    String str = "";
                    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\config.json");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String tempString = null;
                    while((tempString = reader.readLine()) != null){
                        str += tempString;
                    }
                    //System.out.println(str);
                    config=new JSONObject(str);
                    config.remove("java");
                    config.put("java",file);
                    //System.out.println(file.toString());
                    FileOutputStream fos= new FileOutputStream(System.getProperty("user.dir")+"\\config.json");
                    OutputStreamWriter os= new OutputStreamWriter(fos);
                    BufferedWriter w= new BufferedWriter(os);
                    w.write(config.toString());
                    w.close();
                }catch (JSONException | IOException ae) { }

            }
        }
        MyJButton java=new MyJButton(configFrame,"配置java路径",0,50,new MyActionListener());

        class RAMconfig implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
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
                    String input = JOptionPane.showInputDialog( "当前内存：" +config.getInt("RAM")+"m\n只输入数字即可，无需带单位");
                    config.remove("RAM");
                    config.put("RAM",Integer.parseInt( input ));
                    //System.out.println(file.toString());
                    FileOutputStream fos= new FileOutputStream(System.getProperty("user.dir")+"\\config.json");
                    OutputStreamWriter os= new OutputStreamWriter(fos);
                    BufferedWriter w= new BufferedWriter(os);
                    w.write(config.toString());
                    w.close();
                }catch (IOException ae){}

            }
        }
        MyJButton RAMconfigButton=new MyJButton(configFrame,"配置内存",0,120,new RAMconfig());

        configFrame.setVisible(true);
    }

}
