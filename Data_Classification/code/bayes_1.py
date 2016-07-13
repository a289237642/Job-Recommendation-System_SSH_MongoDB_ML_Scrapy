#coding:utf-8
'''
Created on 9,Jan 2016

@author: xinchen

'''

from numpy import *
#from PyConnent_list import PyConnect
class MyBayes(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
    def loadDataSet(self,filename1,filename2):
        f1 = open(filename1,"r")
        f2 = open(filename2,"r")
        dataSet = f1.read().split('\t')
        datalist = []
        for i in dataSet:
            data = []
            data = i.split('#')
            if len(data)>0:
                datalist.append(data[:-1])
        datalist = datalist[:-1]
        labelSet = f2.read().split('\t')
        label = []
        for i in labelSet:
            if i:
                label.append(int(i[:-1]))
#        print datalist
#        print label             #1 is tec ,0 is no-tec
        return datalist,label
    
    def createVocabList(self,dataSet,vocabname):
        f3 = open(vocabname,"r")
        vocabulary = f3.read().split(",")
        return vocabulary
        '''
        vocabSet = set([])
        for document in dataSet:
            vocabSet = vocabSet | set(document)
        return list(vocabSet)
        '''
        
    def setOfWords2Vec(self,vocabList,inputSet):
        returnVec =[0]*len(vocabList)
        for word in inputSet:
            if word in vocabList:
                returnVec[vocabList.index(word)] = 1
            else:
                print 'the word %s is not in my Vocabulary!' % word
        return returnVec
    
    def trainNB0(self,trainMatrix,trainCategory):
        numTrainDocs = len(trainMatrix)
        numWords = len(trainMatrix[0])
        pAbusive = sum(trainCategory)/float(numTrainDocs)
        p0Num = ones(numWords)
        p1Num = ones(numWords)
        p0Denom = 2.0
        p1Denom = 2.0
        for i in range(numTrainDocs):
            if trainCategory[i] == 1:
                p1Num += trainMatrix[i]
                p1Denom += sum(trainMatrix[i])
            else:
                p0Num += trainMatrix[i]
                p0Denom += sum(trainMatrix[i])
        p0Vec = log(p0Num/p0Denom)
        p1Vec = log(p1Num/p1Denom)
        return p0Vec,p1Vec,pAbusive 
    
    def classifyNB(self,vec2Classify,p0Vec,p1Vec,pClass1):
        p1 = sum(vec2Classify*p1Vec) + log(pClass1)
        p0 = sum(vec2Classify*p0Vec) + log(1-pClass1)
        if p1 > p0:
            return 1
        else:
            return 0
    
    
if __name__ == '__main__':
    mybayes = MyBayes()
    filename1 = "D:\\Data_Classification\\bayes\\train\\split_source\\DataSet_1.txt"
    filename2 = "D:\\Data_Classification\\bayes\\train\\split_source\\LableSet_1.txt"
    listOPosts,listClasses = mybayes.loadDataSet(filename1,filename2)
    vocabname = "D:\\Data_Classification\\bayes\\train\\split_source\\Vocabulary_1.txt" 
    myVocabList = mybayes.createVocabList(listOPosts,vocabname)
    print listOPosts
    print listClasses
    print myVocabList
    trainMat = []
    for postinDoc in listOPosts:
        trainMat.append(mybayes.setOfWords2Vec(myVocabList, postinDoc))
    p0V,p1V,pAb = mybayes.trainNB0(trainMat, listClasses)
    
#    print 'p0V :',p0V
#    print 'p1V :',p1V
#    print 'pAbusive :',pAb
    

    '''these codes are using to test the error rate by choosing the data in totalDataSet randomly
    / and they dataSet and vocabulary all come from the same source. 
    average_error = 0.0
    iterator = 20
    for i in range(iterator):
        testSet = []
        trainingSet = range(450)
        for i in range(10):
            randIndex = int(random.uniform(0,len(trainingSet)))
            testSet.append(trainingSet[randIndex])
            del (trainingSet[randIndex])
        errorCount =0
        for docIndex in testSet:
            wordVector = mybayes.setOfWords2Vec(myVocabList, listOPosts[docIndex])
            if mybayes.classifyNB(array(wordVector), p0V, p1V, pAb) != listClasses[docIndex]:
                errorCount += 1
        average_error += float(errorCount)/len(testSet)
    print 'the average error rate is :',average_error/iterator
    '''
    
    '''now i^m using the testData to test model and i find the result is pretty good!
    Jan 30,2016
    '''
    '''
    add for iterator
    '''
    
    list_name = ['NUPT','NUAA','NUST','SEU']
    for j in list_name:      
        
        testfilename = "D:\\Data_Classification\\bayes\\test\\split_scrapy\\"+ 'split_'+ j +".txt"
        f = open(testfilename,"r")
        dataSet = f.read().split('\t')
        datalist = []
        for i in dataSet:
            data = []
            data = i.split('#')
            if len(data)>0:
                datalist.append(data[:-1])
        datalist = datalist[:-1]
        print 'datalist',datalist
        print len(datalist)
        predict_label = []
        for i in range(len(datalist)):
            wordVector = mybayes.setOfWords2Vec(myVocabList,datalist[i])
            label = mybayes.classifyNB(array(wordVector), p0V, p1V, pAb) 
            predict_label.append(label)
        print predict_label
        print len(predict_label)
        Test_Data_File = open("D:\\Data_Classification\\bayes\\test\\scrapy\\"+ j +".txt","r")
        Tec_File = open("D:\\Data_Classification\\bayes\\outcome\\" + 'Tec_'+ j +".txt","w")
        NO_Tec_File = open("D:\\Data_Classification\\bayes\\outcome\\" + 'Non_Tec_'+ j +".txt","w")
        index = 0
        for line in Test_Data_File.readlines():
            if cmp(predict_label[index],0)==0:
                NO_Tec_File.writelines(line)
            else:
                Tec_File.writelines(line)
            index += 1
        Test_Data_File.close()    
        Tec_File.close()
        NO_Tec_File.close()
        f.close()    
    
    '''20160222-remove
    f2 = open("C:\\Users\\xinchen\\Desktop\\train\\predictLabel.txt","w")
    for i in range(len(predict_label)):
#        print predict_label[i]
        f2.writelines(str(predict_label[i]))
        f2.writelines('\n')
    f2.close()
    '''