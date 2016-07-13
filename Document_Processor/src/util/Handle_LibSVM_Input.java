package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Handle_LibSVM_Input {
	private Map<Integer,List<Map<Integer,Double>>> document_tfidf = null;
	private List<Map<Integer,Double>> input_label1 = null; 
	private List<Map<Integer,Double>> input_label2 = null;
	private List<Map<Integer,Double>> input_label3 = null;
	private List<Map<Integer,Double>> input_label4 = null;
	private String filepath = null;
	
	public Handle_LibSVM_Input(Map<Integer,List<Map<Integer,Double>>> document_tfidf,String filepath)
	{
		this.document_tfidf = document_tfidf;
		input_label1 = document_tfidf.get(1);
		input_label2 = document_tfidf.get(2);
		input_label3 = document_tfidf.get(3);
		input_label4 = document_tfidf.get(4);
		this.filepath = filepath;
	}
	
	public void loadData()
	{
		try {
			//创建文件
			File file = new File(filepath);
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			//写入开发部分__1
			List<Map<Integer,Double>> input_label_dev = sort_tfidf(input_label1);
			Iterator<Map<Integer,Double>> iter1 = input_label_dev.iterator();
			while(iter1.hasNext())
			{
				bw.write("1");
				Map<Integer,Double> sortedMap = new LinkedHashMap<Integer,Double>();
				sortedMap = iter1.next();
				for (Map.Entry<Integer, Double> entry : sortedMap.entrySet())
				{	bw.write(" ");
					bw.write(entry.getKey()+":"+entry.getValue());
				}
				bw.write("\r\n");
			}
			//写入测试部分__2
			List<Map<Integer,Double>> input_label_test = sort_tfidf(input_label2);
			Iterator<Map<Integer,Double>> iter2 = input_label_test.iterator();
			while(iter2.hasNext())
			{
				bw.write("2");
				Map<Integer,Double> sortedMap = new LinkedHashMap<Integer,Double>();
				sortedMap = iter2.next();
				for (Map.Entry<Integer, Double> entry : sortedMap.entrySet())
				{	bw.write(" ");
					bw.write(entry.getKey()+":"+entry.getValue());
				}
				bw.write("\r\n");
			}		
			//写入技术支持部分__3
			List<Map<Integer,Double>> input_label_support = sort_tfidf(input_label3);
			Iterator<Map<Integer,Double>> iter3 = input_label_support.iterator();
			while(iter3.hasNext())
			{
				bw.write("3");
				Map<Integer,Double> sortedMap = new LinkedHashMap<Integer,Double>();
				sortedMap = iter3.next();
				for (Map.Entry<Integer, Double> entry : sortedMap.entrySet())
				{	bw.write(" ");
					bw.write(entry.getKey()+":"+entry.getValue());
				}
				bw.write("\r\n");
			}
			//写入其他__4
			List<Map<Integer,Double>> input_label_other = sort_tfidf(input_label4);
			Iterator<Map<Integer,Double>> iter4 = input_label_other.iterator();
			while(iter4.hasNext())
			{
				bw.write("4");
				Map<Integer,Double> sortedMap = new LinkedHashMap<Integer,Double>();
				sortedMap = iter4.next();
				for (Map.Entry<Integer, Double> entry : sortedMap.entrySet())
				{	bw.write(" ");
					bw.write(entry.getKey()+":"+entry.getValue());
				}
				bw.write("\r\n");
			}
			//将文件关闭
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	//将待输入的svm训练数据按key值进行排序(升序)
	public List<Map<Integer,Double>> sort_tfidf(List<Map<Integer,Double>> input_label)
	{
		List<Map<Integer,Double>> sorted_input_label = new ArrayList<Map<Integer,Double>>();
		Iterator iter = input_label.iterator();
		while(iter.hasNext())
		{
			Map<Integer,Double> inputmap =  (Map<Integer, Double>) iter.next();
			Map<Integer,Double> sortedmap = new LinkedHashMap<Integer,Double>(); 
			List<Map.Entry<Integer, Double>> entryList = 
					new ArrayList<Map.Entry<Integer,Double>>(inputmap.entrySet());  
			Collections.sort(entryList, new Comparator<Map.Entry<Integer,Double>>(){
				public int compare(Entry<Integer, Double> lhs, Entry<Integer, Double> rhs) {		       
					return lhs.getKey().compareTo(rhs.getKey());
				}
			});
			Iterator<Map.Entry<Integer, Double>> it = entryList.iterator();
			Map.Entry<Integer, Double> tmpEntry = null;
			while(it.hasNext())
			{
				tmpEntry = it.next();
				//System.out.println(tmpEntry.getKey()+tmpEntry.getValue());
				//topN_word.add(tmpEntry.getKey());
				sortedmap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}
			sorted_input_label.add(sortedmap);
		}
		return sorted_input_label;
	}
}
