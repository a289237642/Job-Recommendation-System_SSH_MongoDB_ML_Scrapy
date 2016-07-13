package randomforest;

import java.util.List;




public class RandomForestPrediction
{
	public static int predict()
	{
		String trainFile = "D:/Data_Classification/randomforest/sample_libsvm_data.txt";
		String testFile = "D:/Data_Classification/randomforest/input.txt";
		List<DataItem> dataList = Utility.loadData(trainFile);
		List<DataItem> testList = Utility.loadData(testFile);
		RandomForest rf = new RandomForest();
		int treeCount = 300;
		double sampleRate = 1.0d;
		rf.buildFullCartForest(dataList, treeCount, sampleRate);
		//double err = rf.evaluate(testList);
		//System.out.println(String.format("ErrorRate: %f", err));
		//System.out.println("label:"+testList.get(0).getLabel());
		//System.out.println("predict:"+rf.predict(testList.get(0)));
		return rf.predict(testList.get(0));
	}
	
}
