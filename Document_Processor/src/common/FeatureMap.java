package common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import train.FeatureTermVectorSelector;

public class FeatureMap {
	private Map<String,Integer> feature = new HashMap<String,Integer>();
	private Map<Integer,List<List<String>>> item = null;
	private Set<String> vocab = null;

	public Map<Integer, List<List<String>>> getItem() {
		return item;
	}

	public void setItem(Map<Integer, List<List<String>>> item) {
		this.item = item;
	}

	public Set<String> getVocab() {
		return vocab;
	}

	public void setVocab(Set<String> vocab) {
		this.vocab = vocab;
	}

	public Map<String, Integer> getFeature() {
		return feature;
	}

	public void setFeature(Map<String, Integer> feature) {
		this.feature = feature;
	}
	
	public void feature_vocab (Double n)
	{
		FeatureTermVectorSelector ftvs = new FeatureTermVectorSelector(item,vocab);
		ftvs.item_set();
		Map<String, Double> word_frequency_label1 = ftvs.processOneLabel(1);
		Map<String, Double> word_frequency_label2 = ftvs.processOneLabel(2);
		Map<String, Double> word_frequency_label3 = ftvs.processOneLabel(3);
		Map<String, Double> word_frequency_label4 = ftvs.processOneLabel(4);
		Map<String,Double> sortedMap1 = ftvs.sortmap(word_frequency_label1);
		Map<String,Double> sortedMap2 = ftvs.sortmap(word_frequency_label2);
		Map<String,Double> sortedMap3 = ftvs.sortmap(word_frequency_label3);
		Map<String,Double> sortedMap4 = ftvs.sortmap(word_frequency_label4);
		Map<String,Double> topNMap1 = ftvs.topN(sortedMap1,n);
		Map<String,Double> topNMap2 = ftvs.topN(sortedMap2,n);
		Map<String,Double> topNMap3 = ftvs.topN(sortedMap3,n);
		Map<String,Double> topNMap4 = ftvs.topN(sortedMap4,n);
		
		
		Set<String> feature1s = Map2List(topNMap1);
		Set<String> feature2s = Map2List(topNMap2);
		Set<String> feature3s = Map2List(topNMap3);
		Set<String> feature4s = Map2List(topNMap4);
		
		feature1s.addAll(feature2s);
		feature1s.addAll(feature3s);
		feature1s.addAll(feature4s);
		Set<String> features = feature1s; 
		
		int index = 0;
		Iterator it = features.iterator();
		while(it.hasNext())
		{
			String word = (String) it.next();
			feature.put(word, index);
			index += 1;
		}
	}
	public Set<String> Map2List(Map<String,Double> topNMap)
	{
		Map<String,Double> totalMap = topNMap;
		Set<String> features = new HashSet<String>();
		Iterator iter = totalMap.entrySet().iterator();
		while(iter.hasNext())
		{
			Map.Entry<String, Double> entry = (Map.Entry<String, Double>)iter.next();
			String key = entry.getKey();
			Double val = entry.getValue();
			System.out.println(key+val);
			features.add(key);
		}
		return features;
	}
	
	
}
