package com.didispace.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.util.DownImage;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    
    
    @RequestMapping("/freeMarker")
    public void freeMarker(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    	req.setCharacterEncoding("utf-8");  
        Map<String, Object> map = new HashMap<String, Object>();  
        Enumeration<String> paramNames = req.getParameterNames();  
        // 通过循环将表单参数放入键值对映射中  
        while(paramNames.hasMoreElements()) {  
            String key = paramNames.nextElement();  
            String value = req.getParameter(key);  
            map.put(key, value);  
        }
        
        String url="http://oml9ntix5.qnssl.com/01492505994-750x1000.jpg";
        String[] split = url.split("\\/");
		String fileName = split[split.length - 1];
		File fileImg = new File("f:/", fileName);
		InputStream inputStream = DownImage.getInputStreamByGet(url);
        DownImage.saveData(inputStream, fileImg);
        String basePic =  WordGenerator.getImageString(fileImg.getAbsolutePath());
        map.put("content",basePic );
        // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = WordGenerator.createDoc(map, "resume");  
            fin = new FileInputStream(file);  
              
            resp.setCharacterEncoding("utf-8");  
            resp.setContentType("application/msword");  
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
            resp.addHeader("Content-Disposition", "attachment;filename=resume.doc");  
              
            out = resp.getOutputStream();  
            byte[] buffer = new byte[512];  // 缓冲区  
            int bytesToRead = -1;  
            // 通过循环将读入的Word文件的内容输出到浏览器中  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);  
            }  
        } finally {  
            if(fin != null) fin.close();  
            if(out != null) out.close();  
            if(file != null) file.delete(); // 删除临时文件  
        }  
    }  
    

}