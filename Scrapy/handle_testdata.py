'''
Created on Jan 30 ,2016

@author: xinchen
'''
import sys
class Handle_TestData(object):
    def __init__(self):
        '''
        Constructor
        '''  
    '''
    classdocs
    '''
    def handledata(self,filename,i):
        reload(sys)   
        sys.setdefaultencoding('utf8')
        f2 = open("D:\\Data_Classification\\bayes\\test\\processed_scrapy\\" + 'processed_'+ i +".txt",'w')
        f= open(filename,'r')
        testData = []
        for i in f.readlines():
            data = i.split('\t')
            testData.append(data[1].decode("unicode_escape"))
            f2.writelines(data[1])
            f2.writelines('\n')

if __name__=='__main__':
    
    list_name = ['NUPT','NUAA','NUST','SEU']
    for i in list_name:
        handle_testData = Handle_TestData()
        handle_testData.handledata("D:\\Data_Classification\\bayes\\test\\scrapy\\"+ i +".txt",i)
    #handle_testData.handledata('C:\\Users\\xinchen\\Desktop\\train\\source\\test_data.txt')