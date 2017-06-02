package com.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Test {
	    public static void main(String[] args) throws IOException, InterruptedException {
	    	
			String url = "http://oml9ntix5.qnssl.com/01492505994-750x1000.jpg";
			String[] split = url.split("\\/");
			String fileName = split[split.length - 1];
			File file = new File("f:/", fileName);
	        InputStream inputStream = DownImage.getInputStreamByGet(url);
	        DownImage.saveData(inputStream, file);
	    }
	}


