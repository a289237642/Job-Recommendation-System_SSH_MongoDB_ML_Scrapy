'''
Created on 20160226

@author: xinchen_tang
'''
import os
import shutil

def createBayesDir(src_dir,second_dir): 
    os.mkdir(src_dir + second_dir)
    
    os.mkdir(src_dir + second_dir + r'\\train')
    os.mkdir(src_dir + second_dir + r"\\train\\source")
    os.mkdir(src_dir + second_dir + r"\\train\\processed_source")
    os.mkdir(src_dir + second_dir + r"\\train\\split_source")
    
    os.mkdir(src_dir + second_dir + r"\\test")
    os.mkdir(src_dir + second_dir + r"\\test\\scrapy")
    os.mkdir(src_dir + second_dir + r"\\test\\processed_scrapy")
    os.mkdir(src_dir + second_dir + r"\\test\\split_scrapy")
    
    os.mkdir(src_dir+second_dir + r"\\outcome")
    
def createSVMDir(src_dir,second_dir):
    os.mkdir(src_dir + second_dir)
    os.mkdir(src_dir + second_dir + r'\\train')
    os.mkdir(src_dir + second_dir + r'\\util')

def initDirectory(src_dir):
    if not os.path.exists(src_dir):
        os.makedirs(src_dir)
    else:
        shutil.rmtree(src_dir)
        os.makedirs(src_dir)

if __name__ == '__main__':
    src_dir = r'D:\\Data_Classification'
    bayes_dir = r'\\bayes'
    svm_dir = r'\\svm'
    initDirectory(src_dir)
    createBayesDir(src_dir,bayes_dir)
    createSVMDir(src_dir,svm_dir)
    print r'Directories have all been created'