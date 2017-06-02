package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;

public class DownImage {
		
	
        // 通过get请求得到读取器响应数据的数据流
    public static InputStream getInputStreamByGet(String url) throws InterruptedException {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        // 将服务器响应的数据流存到本地文件
    public static void saveData(InputStream is, File file) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(file));) {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static String saveData1(String url) throws IOException, InterruptedException {
    	//Integer num = is.available();
    	InputStream in = DownImage.getInputStreamByGet(url);
        try (BufferedInputStream bis = new BufferedInputStream(in);
                ) {
            byte[] buffer = new byte[1024];
            String res = "";
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
               res += buffer;
            }
            byte[] newRes = res.getBytes();
            BASE64Encoder encoder = new BASE64Encoder();  
            return res != null ? encoder.encode(newRes) : "";
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
}