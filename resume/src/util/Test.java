package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jnr.ffi.Struct.in_addr_t;
public class Test {
	public static void start(){  
        try{  
                System.out.println("start");
                
                //开始数据的准备期
                //先执行数据的预处理 python D:\\Scrapy\\handledata.py
                System.out.println("pre_data_handling");
                Process pr = Runtime.getRuntime().exec("python D:\\Scrapy\\handledata.py");    
                pr.waitFor();  
                System.out.println("Finish pre_data_handling");

                //执行SplitWord.java
                System.out.println("Starting pre_data_word_split");
                SplitWord.start();
                System.out.println("Finish pre_data_word_split");
                
                //开始正式使用朴素贝叶斯算法执行分类过程
                //执行Handle_testdata.py
                System.out.println("Starting Handle_testdata.py");  
                Runtime.getRuntime().exec("python  D:\\Scrapy\\handle_testdata.py");                   
                System.out.println("Finish Handle_testdata.py");
                //执行Bayes_SplitWord.java
                System.out.println("Starting Bayes_SplitWord.java");  
                Bayes_SplitWord.start();
                System.out.println("Finish Bayes_SplitWord.java");
                //执行Baye_1.py
                System.out.println("start Baye_1.py");  
                Test3.start();
                System.out.println("Finish Baye_1.py");  
        } catch (Exception e){  
                    e.printStackTrace();  
                } 
        }  
}

