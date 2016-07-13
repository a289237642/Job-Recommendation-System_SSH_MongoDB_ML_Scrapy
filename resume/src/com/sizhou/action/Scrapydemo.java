package com.sizhou.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Scrapydemo extends Thread {
	static File f=null;
	public static void startCai() throws Exception {
		
		Thread t1 = new Scrapydemo();
			t1.start();
			Thread.sleep(10000);
			while(true)
			{
				long t=	f.length();
				Thread.sleep(10000);
				System.out.println(f.length());
				if(t==f.length())					
					break;
			}
			System.out.println("finish info collection");
			//System.exit(0);
	}

	@Override
	public void run() {
		try { 
			System.out.println("Starting run start.bat");
			f=new File("D:\\Scrapy\\scrapy.log");
			Process proc = Runtime.getRuntime().exec("cmd /c start D:\\Scrapy\\start.bat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
