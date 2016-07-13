package filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Stop_Words {
	public Set<String> loadData(String filepath)
	{
		Set<String> stopword = new HashSet<String>();
		try {
			File file = new File(filepath);
			if(file.isFile() && file.exists())
			{
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
				BufferedReader br = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = br.readLine()) != null)
				{
					stopword.add(lineTxt);
				}
				read.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stopword;	
	}
	
	public List<String> del_stopword(Set<String> stopword,List<String> data)
	{
		List<String> result = new ArrayList<String>();
		Iterator<String> it = data.iterator();
		while(it.hasNext())
		{
			String word = it.next();
			if(stopword.contains(word))
			{
				continue;
			}
			result.add(word);
		}
		return result;
	}
}
