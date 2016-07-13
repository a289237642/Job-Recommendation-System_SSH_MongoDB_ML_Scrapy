package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jeasy.analysis.MMAnalyzer;


public class SplitWord {
	
	
	public List addVocab(String text)   
	{    
		MMAnalyzer analyzer = new MMAnalyzer();
		List vocabList = new ArrayList();
		try   
		{   
			String result = analyzer.segment(text, "|");
			String[] tmp = result.split("\\|");
			for(int i=0;i<tmp.length;i++)
			{
				vocabList.add(tmp[i]);
			}   
		}   
		catch (IOException e)   
		{   
			e.printStackTrace();   
		}   
		return vocabList;
	} 
	public List<List> loadData(String filepath)
	{
		List<List> ls = new ArrayList<List>(); 
		List<String> result = new ArrayList<String>();
		List<String> label = new ArrayList<String>(); 
		try {
			String encoding = "GBK";
			File file = new File(filepath);
			if(file.isFile() && file.exists())
			{
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader br = new BufferedReader(read);
				String lineTxt = null;
	//			int i=0;
				while((lineTxt = br.readLine()) != null)
				{
					
					String[] input1 = lineTxt.split("\t");
					result.add(new String(input1[2].getBytes("GB2312")));
					label.add(input1[0]);
	//				i=i+1;
				}
	//			System.out.println(i);
				read.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ls.add(result);
		ls.add(label);
		return ls;	
	}
	public void storeVocab(String storename,Set output_set)
	{
		try {
			File file = new File(storename);
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<String> it = output_set.iterator();
			while(it.hasNext())
			{
				String data = it.next();
		//		System.out.println(data);
				bw.write(data);
				bw.write(",");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void storeLabelData(String storename,List result)
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
					bw.write(i);
					bw.write(",");
				}
				bw.write("\t");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void start()
	{
		SplitWord sw = new SplitWord();
		//String filename1 = "D:\\Scrapy\\train\\source\\first_data.txt";
		String filename2 = "D:\\Data_Classification\\bayes\\train\\processed_source\\processed_data1.txt";
		String filepath = filename2;
		List<String> result_list = sw.loadData(filepath).get(0);
		List<String> label_list = sw.loadData(filepath).get(1);
		System.out.println(result_list.size());
		System.out.println(label_list.size());
		List<String> output = new ArrayList<String>();
		for(String text : result_list)
		{
			List<String> c = sw.addVocab(text);
			for (String tmp1 : c)
			{
				if (tmp1.length()>1 && tmp1.indexOf("\\/")==-1 )
				{
					output.add(tmp1);					
				}
			}
		}
		System.out.println("List num : "+output.size());
		Set<String> output_set = new HashSet<String>(output);
		System.out.println("Set num :"+output_set.size());
/*		for(String tmp : output_set)
		{
			System.out.println(tmp);
		}*/
		sw.storeDataSet("D:\\Data_Classification\\bayes\\train\\split_source\\DataSet_1.txt",result_list);
		sw.storeLabelData("D:\\Data_Classification\\bayes\\train\\split_source\\LableSet_1.txt", label_list);
		sw.storeVocab("D:\\Data_Classification\\bayes\\train\\split_source\\Vocabulary_1.txt", output_set);
		System.out.println("DataSet_1.txt ,LabelSet_1.txt,and Vocabulary_1.txt has already been handled");
	}

}
