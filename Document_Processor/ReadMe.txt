API

（1）需要将执行的程序命名为D:\train\bayes\Tec_NUPT.txt,D:\train\bayes\No_Tec_NUPT.txt

（2）先执行DifferentSchoolAnalyzer的main方法----训练集  chi的值由第一个参数args[0]提供，用于提取特征向量
         再执行DifferentSchoolTestData的main方法----测试集  数据库的IP地址 第一个参数agrs[0]提供
         结果存在数据库中
    /*
    * 调用数据库将数据存进数据库中
        Mongo默认端口号可以不赋值
              数据库命名规范:	
       数据库名称：当前日期YY-MM-DD
       集合名称：学校+技术+分类
    */

（3）训练集存在D:\Data_Classification\svm\train下，
	 测试集存在D:\Data_Classification\bayes\outcome下
	 命名规范为:NUPT_DataSet.txt