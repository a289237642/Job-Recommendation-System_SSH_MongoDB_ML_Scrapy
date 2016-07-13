//package Apriori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jeasy.analysis.MMAnalyzer;

public class Bayes_SplitWord {
	public List loadData(String filepath)
	{
		File file = new File(filepath);
		List result = new ArrayList();
		try {
			if(file.isFile() && file.exists())
			{
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
				BufferedReader br = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = br.readLine()) != null)
				{
					result.add(lineTxt.toString());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
	public void storeDataSet(String storename,List result)
	{
		try {
			File file = new File(storename);
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<String> it = result.iterator();
			while(it.hasNext())
			{
				String data = it.next();
				MMAnalyzer analyzer = new MMAnalyzer();
				String result1 = analyzer.segment(data, "|");
				String[] tmp = result1.split("\\|");
	//			System.out.println(tmp);
				for(String i : tmp)
				{
					if(i.length()>1)
					{
						bw.write(i);
						bw.write("#");						
					}
				}
				bw.write("\t");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
	    String[] schoolArray = {"NUPT","NUAA","NUST","SEU"};
	    for (int j = 0;j < schoolArray.length;j++)
	    {
	    
		String filepath = "D:\\Data_Classification\\bayes\\test\\processed_scrapy\\" + "processed_"+ schoolArray[j] +".txt";
		String storepath = "D:\\Data_Classification\\bayes\\test\\split_scrapy\\"+ "split_"+ schoolArray[j] +".txt";
		Bayes_SplitWord bayes = new Bayes_SplitWord();
		List result = bayes.loadData(filepath);
		bayes.storeDataSet(storepath, result);
	    }
	    }
	}
	
