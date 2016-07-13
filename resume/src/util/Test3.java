package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class Test3 {
	public static void start(){  
		try{   
            Process pr = Runtime.getRuntime().exec("python D:\\Scrapy\\bayes_1.py");  
              
            BufferedReader in = new BufferedReader(new  
                    InputStreamReader(pr.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                System.out.println(line);  
            }  
            in.close();  
            pr.waitFor();    
    } catch (Exception e){  
                e.printStackTrace();  
            }  
    }
}

