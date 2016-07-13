package component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.Term;

public class DocumentTFIDFComputation {
	private Map<Integer,List<List<String>>> item = null;
	private Set<String> vocab = null;
	List<List<String>> total_document = new ArrayList<List<String>>();
	Map<String,Integer> feature = null;
	
	public DocumentTFIDFComputation(Map<Integer,List<List<String>>> item,Set<String> vocab,Map<String,Integer> feature)
	{		
		this.item = item;
		this.vocab = vocab;
		this.feature = feature;
		Term term = new Term();
		total_document.addAll(item.get(1));
		total_document.addAll(item.get(2));
		total_document.addAll(item.get(3));
		total_document.addAll(item.get(4));
		term.setTotal_document_num(total_document.size());
		
	}
	public Map<Integer,List<Map<Integer,Double>>> compute()
	{
		Map<Integer,List<Map<Integer,Double>>> result = new HashMap<Integer,List<Map<Integer,Double>>>();
		result.put(1, computing_tfidf_in_label(1));
		result.put(2, computing_tfidf_in_label(2));
		result.put(3, computing_tfidf_in_label(3));
		result.put(4, computing_tfidf_in_label(4));
		return result;
	}
	
	public List<Map<Integer,Double>> computing_tfidf_in_label(int label)
	{
		List<List<String>> document_in_one_label = new ArrayList<List<String>>();
		document_in_one_label = item.get(label);
		Iterator iter = document_in_one_label.iterator();
		List<Map<Integer,Double>> document_tfidf = new ArrayList<Map<Integer,Double>>(); 
		while(iter.hasNext())
		{
			Map<Integer, Double> word_tfidf = new HashMap<Integer, Double>();
			List<String> document = (List<String>) iter.next();
			Iterator<String> it = document.iterator();
			int word_in_one_document = document.size();
			int word_showtimes_in_one_document = 0;
			int word_showtimes_in_alldocuments = 0;
			int all_documents_num = 0;
			double tf_idf =0;
			while(it.hasNext())
			{
				String word = it.next();
				if(feature.containsKey(word))
				{
					word_showtimes_in_one_document = getShowtimesInOneDocument(word,document);
					word_showtimes_in_alldocuments = getShowtimesInAllDocuments(word);
					all_documents_num = total_document.size();
					tf_idf = multiple(word_in_one_document,word_showtimes_in_one_document,word_showtimes_in_alldocuments,all_documents_num);
					word_tfidf.put(feature.get(word), tf_idf);
				}/*else
				{
					System.out.println(word+"is not a FeatureTermVector");
				}	*/
			}
			document_tfidf.add(word_tfidf);
		}
		return document_tfidf;
		
	}
	private int getShowtimesInOneDocument(String word,List<String> document) {
		int index = 0;
		Iterator iter = document.iterator();
		while(iter.hasNext())
		{
			String temp = (String) iter.next();
			if(temp.equals(word))
			{
				index += 1;
			}
		}
		return index;
	}
	
	private int getShowtimesInAllDocuments(String word) {
		int index = 0;
		Iterator iter = total_document.iterator();
		while(iter.hasNext())
		{
			List<String> document = (List<String>) iter.next();
			if(document.contains(word))
			{
				index += 1;
			}
		}
		return index;
	}
	private double multiple(int word_in_one_document,
			int word_showtimes_in_one_document,
			int word_showtimes_in_alldocuments,
			int all_documents_num) {
		double tf = (double)word_showtimes_in_one_document/(double)word_in_one_document;
		double idf = Math.log10((double)all_documents_num/word_showtimes_in_alldocuments); 
		return tf*idf;
	}
	
	
	
	
	
}
