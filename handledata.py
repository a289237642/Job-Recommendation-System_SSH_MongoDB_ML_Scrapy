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
        filename1 = "C://Users//ss//Desktop//train//source//data1.txt"
        #filename1 = "D://train//scrapy//NJUPT.txt"
        filename2 = "C://Users//ss//Desktop//train//source//data2.txt"
        f = open(filename1,"r")
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
        filename1 = "C://Users//ss//Desktop//train//source//total_data.txt"
        #filename1 = "D://train//scrapy//NJUPT_new.txt"
        filename2 = "C://Users//ss//Desktop//train//source//second_yyt.txt"
        f = open(filename1,"w")
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
