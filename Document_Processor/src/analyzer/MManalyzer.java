package analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jeasy.analysis.MMAnalyzer;

public class MManalyzer {
	public List<List<String>> handleDataSet(List<List<String>> input)
	{
		Iterator<List<String>> it = input.iterator();
		List<List<String>> result = new ArrayList<List<String>>();
		try {
			while(it.hasNext())
			{
				List<String> title_detail_list = it.next();
				List<String> word = new ArrayList<String>();
				String data = title_detail_list.get(0).toString()+title_detail_list.get(1).toString();
				MMAnalyzer analyzer = new MMAnalyzer();
				String input1 = analyzer.segment(data, "|");
				String[] tmp = input1.split("\\|");
				for(String i : tmp)
				{
					word.add(i);
				}
				result.add(word);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
} 
