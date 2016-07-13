'''
Created on Jan 20 ,2016

@author: xinchen
'''
import sys
class MyClass(object):
    def __init__(self):
        '''
        Constructor
        '''
    '''
    classdocs
    '''
    def HandleData(self):
        filename = "D://Data_Classification//bayes//train//source//data1.txt"
        f = open(filename,"r")
        ArrMatrix = []
        for line in f.readlines():
            lineArr = line.strip().split('\t')
#            print lineArr[5]
#            print lineArr[4]
            if cmp(lineArr[5],"0") == 0:
                del lineArr[5]           
                ArrMatrix.append(lineArr)
        f.close()
        return ArrMatrix
        
    def StoredData(self,ArrMatrix):
#        filename = "C://Users//xinchen//Desktop//train//source//total_data.txt"
        filename = "D://Data_Classification//bayes//train//processed_source//processed_data1.txt"
        f = open(filename,"w")
        print len(ArrMatrix)
        index = 0
        for linedata in ArrMatrix:
            if cmp(linedata[0],"0") ==0:
                index +=1
            data=[]
            data.append(linedata[0])
            data.append('\t')
            data.append(linedata[1])
            data.append('\t')
            data.append(linedata[3])
            data.append('\n')
            f.writelines(data)
        print index
        
if __name__ == "__main__": 
    reload(sys)   
    sys.setdefaultencoding('utf8')
    cls = MyClass()
    ArrMatrix = cls.HandleData()
    cls.StoredData(ArrMatrix)