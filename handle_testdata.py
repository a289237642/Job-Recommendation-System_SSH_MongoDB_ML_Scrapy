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
    def handledata(self,filename):
        reload(sys)   
        sys.setdefaultencoding('utf8')
        f2 = open('C:\\Users\\ss\\Desktop\\train\\test_data.txt','w')
        f= open(filename,'r')
        testData = []
        for i in f.readlines():
            data = i.split('\t')
            testData.append(data[1].decode("unicode_escape"))
            f2.writelines(data[1])
            f2.writelines('\n')

if __name__=='__main__':
    handle_testData = Handle_TestData()
    handle_testData.handledata('C:\\Users\\ss\\Desktop\\train\\source\\NUST.txt')
    #handle_testData.handledata('C:\\Users\\ss\\Desktop\\train\\source\\test_data.txt')