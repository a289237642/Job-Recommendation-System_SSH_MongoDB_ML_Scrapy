package train;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.python.antlr.op.In;

public class FeatureTermVectorSelector {
	
	private Map<Integer,List<List<String>>> item = null;
	private Set<String> vocab = null;
	Map<Integer,Set<String>> all_info_in_diff_items = null;
	
	public FeatureTermVectorSelector(Map<Integer,List<List<String>>> item,Set<String> vocab)
	{
		this.item = item;
		this.vocab = vocab;
	}
	
	public Set<String> list2string (List<List<String>> data)
	{
		Set<String> all_info_in_intems = new HashSet<String>();
		Iterator<List<String>> it = data.iterator();
		while(it.hasNext())
		{
			List<String> info = (List<String>) it.next();
			all_info_in_intems.addAll(info);
		}
		return all_info_in_intems;
	}
	
	
	public void item_set ()
	{
		Map<Integer,Set<String>> all_info_in_diff_items = new HashMap<Integer,Set<String>>();
		Set<String> dev = this.list2string(item.get(1));
		Set<String> test = this.list2string(item.get(2));
		Set<String> support = this.list2string(item.get(3));
		Set<String> other = this.list2string(item.get(4));
		all_info_in_diff_items.put(1, dev);
		all_info_in_diff_items.put(2, test);
		all_info_in_diff_items.put(3, support);
		all_info_in_diff_items.put(4, other);
		this.all_info_in_diff_items = all_info_in_diff_items;
	}
	
	public Map<String, Double> processOneLabel(int label)
	{
		Iterator iter = all_info_in_diff_items.get(label).iterator();
		Map<String, Double> word_frequency = new HashMap<String,Double>();
		while(iter.hasNext())
		{
			String word = (String) iter.next();
			//A
			int docCountContainingWordInLabel = 0;
			Iterator ita = item.get(label).iterator();
			while(ita.hasNext())
			{
				List<String> document = (List<String>) ita.next();
				if(document.contains(word)) 
				{
					docCountContainingWordInLabel +=1;
				}
			}
			//B
			//word_frequency.put(word, docCountContainingWordInLabel);
			int docCountContainingWordNotInLabel = 0;
			Iterator itb = Left_Label(label).iterator();
			while(itb.hasNext())
			{
				List<String> document = (List<String>) itb.next();
				if(document.contains(word)) 
				{
					docCountContainingWordNotInLabel +=1;
				}
			}
			//c
			int docCountNotContainingWordInLabel = 
					item.get(label).size() - docCountContainingWordInLabel;  
			
			//d
			int docCountNotContainingWordNotInLabel = 
					Left_Label(label).size() - docCountContainingWordNotInLabel;
		
			//compute CHI value
			int N = item.get(label).size() + Left_Label(label).size();
			int A = docCountContainingWordInLabel;
			int B = docCountContainingWordNotInLabel;
			int C = docCountNotContainingWordInLabel;
			int D = docCountNotContainingWordNotInLabel;
			int temp = (A*D-B*C);
			double chi = (double) N*temp*temp / ((A+C)*(A+B)*(B+D)*(C+D));
			word_frequency.put(word, chi);
		}
		return word_frequency;
	}
	//将Map的值按照chi进行排序
	public Map<String,Double> sortmap(Map<String, Double> word_frequency)
	{
		//List<String> topN_word = new ArrayList<String>();
		Map<String,Double> sortedMap = new LinkedHashMap<String,Double>();
		List<Map.Entry<String, Double>> entryList = 
				new ArrayList<Map.Entry<String,Double>>(word_frequency.entrySet());  
		Collections.sort(entryList, new Comparator<Map.Entry<String,Double>>(){
			public int compare(Entry<String, Double> lhs, Entry<String, Double> rhs) {
				Double d1 = lhs.getValue();		
				Double d2 = rhs.getValue();	       
				return d2.compareTo(d1);
			}
		});
		Iterator<Map.Entry<String, Double>> iter = entryList.iterator();
		Map.Entry<String, Double> tmpEntry = null;
		while(iter.hasNext())
		{
			tmpEntry = iter.next();
			//System.out.println(tmpEntry.getKey()+tmpEntry.getValue());
			//topN_word.add(tmpEntry.getKey());
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		//return sortedMap;
		return sortedMap;
	}
	//依据chi从排序好的map中选取N个word作为该label的特征值
	public Map<String,Double> topN(Map<String,Double> sortedMap,Double n)
	{
		Iterator iter = sortedMap.entrySet().iterator();
		Map<String,Double> topNMap = new LinkedHashMap<String,Double>();
		while(iter.hasNext())
		{
			Map.Entry<String, Double> entry = (Map.Entry<String, Double>)iter.next();
			String key = entry.getKey();
			Double val = entry.getValue();
			if(val>=n)
			{
				topNMap.put(key, val);
			}
		}
		return topNMap;
	}
	
	
	
	
	
	//除了当前label以外的其余的label所组成的document的集合
	public List<List<String>> Left_Label(int label)
	{
		List<Integer> Label = new ArrayList<Integer>();
		Label.add(1);
		Label.add(2);
		Label.add(3);
		Label.add(4);
		Label.remove(label-1);
		List<List<String>> left_document = new ArrayList<List<String>>();
		Iterator it = Label.iterator();
		while(it.hasNext())
		{
			List<List<String>> document = item.get(it.next());
			left_document.addAll(document);
		}
		return left_document;
	}
}



