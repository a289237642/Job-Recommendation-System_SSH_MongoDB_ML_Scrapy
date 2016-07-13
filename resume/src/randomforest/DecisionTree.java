package randomforest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class DecisionTree
{
	protected static String trainData = "D:/Data_Classification/randomforest/sample_libsvm_data.txt";
	
	protected static String testData = "D:/Data_Classification/randomforest/input.txt";
	
	protected static List<DataItem> trainSet;
	
	protected static List<DataItem> testSet;
	
	protected int featureCount = 2;
	
	protected TreeNode tree;
	
	static
	{
		trainSet = Utility.loadData(trainData);
		testSet = Utility.loadData(testData);
	}
	
	public DecisionTree()
	{
		tree = new TreeNode();
	}
	
	
	public int getFeatureCount()
	{
	
		return featureCount;
	}

	public void setFeatureCount(int featureCount)
	{
	
		this.featureCount = featureCount;
	}


	/**
	 * ����ĳһ���ݵĻ���ָ��
	 * @param dataList
	 * @return
	 */
	public double computeGiniIndex(List<DataItem> dataList)
	{
		double giniIndex = 0d;
		Map<Integer, Integer> labelCount = new HashMap<Integer, Integer>();
		for(DataItem di : dataList)
		{
			int label = di.getLabel();
			if(labelCount.containsKey(label))
				labelCount.put(label, labelCount.get(label) + 1);
			else
				labelCount.put(label, 1);
		}
		double sum = 0d;
		Iterator<Entry<Integer, Integer>> iter = labelCount.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Integer, Integer> entry = iter.next();
			int count = entry.getValue();
			sum += Math.pow((double)count / (double)dataList.size(), 2d);
		}
		giniIndex = 1 - sum;
		return giniIndex;
	}
	
	
	/**
	 * ���ĳһ�е�����
	 * @param dataList
	 * @param index
	 * @return
	 */
	public double [] getFeatureArray(List<DataItem> dataList, int index)
	{
		double [] features = new double[dataList.size()];
		int i = 0;
		for(DataItem di : dataList)
		{
			features[i++] = di.getFeature(index);
		}
		
		return features;
	}
	
	/**
	 * ��ú�ѡ�ָ�ֵ
	 * @param values
	 * @return
	 */
	public List<Double> getCandidateSegment(double [] values)
	{
		//������������values
		List<Double> segments = new ArrayList<Double>();
		Arrays.sort(values);
		for(int i = 0; i < values.length - 1; i++)
		{
			if(Math.abs(values[i] - values[i + 1]) > 1E-5)
				segments.add((values[i] + values[i + 1]) / 2d);
		}
		
		
		return segments;
	}
	
	public List<Double> getCandidateSegment(List<DataItem> dataList, int index)
	{
		return getCandidateSegment(getFeatureArray(dataList, index));
	}
	
	/**
	 * ����ĳһ�����з�����
	 * @param condition ��������
	 * @param dataList
	 * @param leftData
	 * @param rightData
	 * @return ��Ȩ������
	 */
	public double splitData(TreeNode condition, List<DataItem> dataList, List<DataItem> leftData, List<DataItem> rightData)
	{
		if(leftData == null)
			leftData = new ArrayList<DataItem>();
		if(rightData == null)
			rightData = new ArrayList<DataItem>();
		
		for(DataItem di : dataList)
		{
			if(di.getFeature(condition.getFeatureIndex()) <= condition.getValue())
				leftData.add(di);
			else
				rightData.add(di);
		}
		double leftGini, rightGini;
		double impulity = 0d;
		leftGini = computeGiniIndex(leftData);
		rightGini = computeGiniIndex(rightData);
		impulity = (double)leftData.size() * leftGini + (double)rightData.size() * rightGini;
		impulity = impulity / (double)dataList.size();
		
		return impulity;
	}
	
	/**
	 * �Ƿ�ֻ��һ�����
	 * @param dataList
	 * @return
	 */
	public boolean isOneLabel(List<DataItem> dataList)
	{
		boolean flag = true;
		for(int i = 0; i < dataList.size() - 1; i++)
		{
			if(dataList.get(i).getLabel() != dataList.get(i + 1).getLabel())
				return false;
			
		}
		
		return flag;
		
	}
	
	/**
	 * Ѱ�ҳ�����������ǩ
	 * @param dataList
	 * @return
	 */
	public int findNearestLabel(List<DataItem> dataList)
	{
		Map<Integer, Integer> labelCount = new HashMap<Integer, Integer>();
		for(DataItem di : dataList)
		{
			int label = di.getLabel();
			if(labelCount.containsKey(label))
				labelCount.put(label, labelCount.get(label) + 1);
			else
				labelCount.put(label, 1);
		}
		int bestCount = Integer.MIN_VALUE;
		int bestLabel = 0;
		Iterator<Entry<Integer, Integer>> iter = labelCount.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Integer, Integer> entry = iter.next();
			int count = entry.getValue();
			if(count > bestCount)
			{
				bestLabel = entry.getKey();
				bestCount = count;
			}
		}
		return bestLabel;
	}
	
	
	
	public TreeNode findBestSplitFeature(List<DataItem> dataList, List<DataItem> leftData, List<DataItem> rightData)
	{
		TreeNode node = new TreeNode();
		//�Ƿ�ΪҶ�ӽ�㣬�������ñ�ǩ�з�֮
		if(isOneLabel(dataList))
		{
			node.setLabel(dataList.get(0).getLabel());
			return node;
		}
		
		featureCount = dataList.get(0).getFeatureCount();
		double bestImpulity = Double.MAX_VALUE;
		for(int i = 0; i < featureCount; i++)
		{
			List<Double> segments = getCandidateSegment(dataList, i);
			for(int j = 0; j < segments.size(); j++)
			{
				TreeNode curNode = new TreeNode();
				curNode.setFeatureIndex(i);
				curNode.setValue(segments.get(j));
				
				List<DataItem> curLeft = new ArrayList<DataItem>();
				List<DataItem> curRight = new ArrayList<DataItem>();
				double curImpulity = splitData(curNode, dataList, curLeft, curRight);
				//�����ǰ�зֵ��������ݸ�������ѡ��֮
				if(curImpulity < bestImpulity)
				{
					bestImpulity = curImpulity;
					node = curNode;
					leftData.clear();
					rightData.clear();
					Utility.copyList(curLeft, leftData);
					Utility.copyList(curRight, rightData);
					//Collections.copy(leftData, curLeft);
					//Collections.copy(rightData, curRight);
				}
			}
		}
		
		return node;
	}
	
	/**
	 * �ݹ�ͨ��CART�㷨����һ�������ľ�����
	 * @param node
	 * @param dataList
	 */
	public void buildFullCartTree(TreeNode node, List<DataItem> dataList)
	{
		List<DataItem> leftSubData = new ArrayList<DataItem>(10000);
		List<DataItem> rightSubData = new ArrayList<DataItem>(10000);
		TreeNode tempNode = findBestSplitFeature(dataList, leftSubData, rightSubData);
		node.copy(tempNode);
		if(node.getLabel() != 0)
			return;
		TreeNode leftChild = new TreeNode();
		node.setLeftChild(leftChild);
		buildFullCartTree(node.getLeftChild(), leftSubData);
		TreeNode rightChild = new TreeNode();
		node.setRightChild(rightChild);
		buildFullCartTree(node.getRightChild(), rightSubData);
		
	}
	
	public void buildFullCartTree()
	{
		buildFullCartTree(this.tree, trainSet);
	}
	
	public void buildFullCartTree(List<DataItem> dataList)
	{
		buildFullCartTree(this.tree, dataList);
	}
	
	/**
	 * ����һ����ȫ��֦������ֻ��һ���зֽ��
	 * @param node
	 * @param dataList
	 */
	public TreeNode buildCompletePrunedTree(List<DataItem> dataList)
	{
		TreeNode node = null;
		List<DataItem> leftData = new ArrayList<DataItem>();
		List<DataItem> rightData = new ArrayList<DataItem>();
		node = findBestSplitFeature(dataList, leftData, rightData);
		
		TreeNode leftNode = new TreeNode();
		int leftLabel = findNearestLabel(leftData);
		leftNode.setLabel(leftLabel);
		node.setLeftChild(leftNode);
		
		TreeNode rightNode = new TreeNode();
		int rightLabel = findNearestLabel(rightData);
		rightNode.setLabel(rightLabel);
		node.setRightChild(rightNode);
		
		this.tree = node;
		
		return node;
	}
	
	public TreeNode buildCompletePrunedTree()
	{
		return buildCompletePrunedTree(trainSet);
	}
	
	/**
	 * ���ݹ���ľ���������Ԥ��
	 * @param tree
	 * @param sample
	 * @return
	 */
	public int predict(TreeNode tree, DataItem sample)
	{
		
		if(tree.getLabel() != 0)
			return tree.getLabel();
		if(sample.getFeature(tree.getFeatureIndex()) <= tree.getValue())
			return predict(tree.getLeftChild(), sample);
		else
			return predict(tree.getRightChild(), sample);
	}
	
	public int predict(DataItem sample)
	{
		return predict(tree, sample);
	}
	
	public double evaluate(TreeNode tree, List<DataItem> dataList)
	{
		double error = 0d;
		int errorCount = 0;
		for(int i = 0; i < dataList.size(); i++)
		{
			DataItem di = dataList.get(i);
			int result = predict(tree, di);
			if(di.getLabel() != result)
				errorCount++;
			
			
		}
		error = (double)errorCount / (double)dataList.size();
		return error;
	}
	
	public double evaluate(List<DataItem> dataList)
	{
		return evaluate(tree, dataList);
	}
	
	
	
	
	public static int internalNodeCount = 0;
	
	/**
	 * ͳ���ڲ�������
	 * @param decisionTree
	 */
	public static void getInternalNodeCount(TreeNode decisionTree)
	{
		if(decisionTree == null)
			return;
		if(decisionTree.getLabel() == 0)
			internalNodeCount++;
		getInternalNodeCount(decisionTree.getLeftChild());
		getInternalNodeCount(decisionTree.getRightChild());
	}
	
	
	public static void main(String [] args)
	{
		DecisionTree dt = new DecisionTree();
		TreeNode tree = new TreeNode();
		dt.buildFullCartTree(tree, DecisionTree.trainSet);
		
		getInternalNodeCount(tree);
		
		double ein = dt.evaluate(tree, DecisionTree.trainSet);
		double eout = dt.evaluate(tree, DecisionTree.testSet);
		
		System.out.println(String.format("%d,%f,%f", internalNodeCount, ein, eout));
	}
	
	
	
	
	
}
