package com.didispace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMakerTest {

	public static void main(String[] args) throws Exception {  
		  
        //test001("first.html");  
        //test002("002.html");  
        //test002("include.html");  
        test002("内建函数.html");  
        //test001("test_userdefdir.html");  
          
  
    }  
  
    private static void test002(String templateName) throws Exception {  
        test001(templateName);  
          
    }  
  
    private static void test001(String templateName) throws Exception {  
        URL classpath = Thread.currentThread().getContextClassLoader()  
                .getResource("");  
  
        String path = classpath.getPath();  
        // System.out.println(path);  
  
        String templatePath = path + "template" + File.separator;  
        String outpath = new File("").getAbsolutePath() + File.separator  
                + "output" + File.separator;  
        // System.out.println(templatePath);  
        // System.out.println(outpath);  
        // System.out.println(new File(templatePath).exists());  
        // System.out.println(new File(outpath).exists());  
  
        Configuration configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
  
        // configuration.setClassForTemplateLoading(TestFreemarker.class,templatePath);  
        TemplateLoader templateLoader = new FileTemplateLoader(new File(  
                templatePath));  
        configuration.setTemplateLoader(templateLoader);  
        Template template = configuration.getTemplate(templateName);  
  
        Map map = new HashMap<String, Object>();  
          
        Set<String> data=new HashSet<String>();  
        data.add("foo");  
        data.add("bar");  
        data.add("baz");  
          
        List<String> list=new ArrayList<String>();  
        list.add("foo");  
        list.add("bar");  
        list.add("baz");  
          
          
        map.put("testSequence", list);  
          
        map.put("testString", "Tom & Jerry 云守护");  
        map.put("user", "蒙奇·D·路飞");  
        map.put("name", "蒙奇·D·路飞");  
        map.put("country", "日本");  
        map.put("city", "东京");  
        map.put("time",  
                new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()));  
        String url = "http://blog.csdn.net/earbao/";  
        map.put("url", url);  
  
        Student student = new Student(110, "终结者", "北京", 22, "man", url);  
        map.put("student", student);  
          
        List<Student> students=new ArrayList<>();  
        for(int i=0;i<5;i++)  
        {  
            Student student2 = new Student(110+i, "终结者"+i, "北京", 22, "man", url);  
            students.add(student2);  
        }  
        map.put("students", students);  
  
        try {  
            File dir = new File(outpath);  
            if (!dir.exists()) {  
                dir.mkdirs();  
            }  
            String outfile = outpath + template.getName();  
  
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(outfile), "UTF-8"));  
  
            template.process(map, out);  
            out.close();  
            System.out.println("output " + outfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
	
}


