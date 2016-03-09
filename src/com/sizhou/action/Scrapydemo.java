package com.sizhou.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Scrapydemo extends Thread {static File f=null;
	public static void startCai() throws Exception {
		
		Thread t1 = new Scrapydemo();
			t1.start();
			Thread.sleep(5000);
			//File f=new File("D:\\scrapy.log");
			
			while(true){
				long t=	f.length();
				Thread.sleep(6000);
				if(t==f.length())
					break;
				System.out.println(f.length());
			
			}
			System.out.println("hello");
			//System.exit(0);
	}

	@Override
	public void run() {
		try { 
			f=new File("D:\\scrapy.log");
			Process proc = Runtime.getRuntime().exec("cmd /c start E:\\desktop\\start.bat");
			System.out.println("run");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
