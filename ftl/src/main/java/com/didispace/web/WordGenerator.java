package com.didispace.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.util.DownImage;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

public class WordGenerator {  
    private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;  
      
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(WordGenerator.class, "/com/didispace/web");  
        allTemplates = new HashMap<>();   // Java 7 钻石语法  
        try {  
            allTemplates.put("resume", configuration.getTemplate("resume.ftl"));  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }  
    }  
  
    private WordGenerator() {  
        throw new AssertionError();  
    }  
  
    public static File createDoc(Map<?, ?> dataMap, String type) {  
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
        File f = new File(name);  
        Template t = allTemplates.get(type);  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }  
    
    
    public static String getImageString(String filename) throws IOException {  
        InputStream in = null;  
        byte[] data = null;  
        try {  
            in = new FileInputStream(filename);  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (IOException e) {  
            throw e;  
        } finally {  
            if(in != null) in.close();  
        }  
        BASE64Encoder encoder = new BASE64Encoder();  
        return data != null ? encoder.encode(data) : "";  
   }  
    
    
    public static String getImageStringUrl(String url) throws IOException, InterruptedException {
    	
        InputStream in = DownImage.getInputStreamByGet(url); 
        byte[] data = null;  
        try {  
            data = new byte[in.available()];  
            in.read(data);  
            in.close(); 
        } catch (IOException e) {  
            throw e;  
        } finally {  
            if(in != null) in.close();  
        }  
        BASE64Encoder encoder = new BASE64Encoder();  
        return data != null ? encoder.encode(data) : "";  
   }  
  
}  