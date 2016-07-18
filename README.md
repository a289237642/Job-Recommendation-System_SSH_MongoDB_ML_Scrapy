# 《基于认知计算的就业咨询智慧服务系统》软件使用说明书

------
[（一）该系统具体业务流程介绍](http://##)

![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E6%9E%B6%E6%9E%84.png)
>（1）、Scrapy从各大招生信息网，获取就业实习信息，并进行数据预处理；
>（2）、存储处理后的数据至数据存储单元；
>（3）、通过走访、问卷调查、联合社团以及院校合作的方式获取前几届毕业生简历信息（包含掌握技能、社团经历等）和就职岗位信息，经过数据预处理后，存进数据存储单元；
>（4）、数据计算平台取出数据，利用认知计算的相关算法，使用就业实习岗位需求信息生成“就业岗位智慧分类模型”，使用简历与就职岗位数据生成“就业智慧决策树模型”；
>（5）、数据计算平台运用Model生成结果：使用“就业岗位智慧分类模型”，对岗位信息分类：技术类和非技术类，其中技术类分为：开发、测试和技术支持，并通过无监督式学习发现就业方向变化趋势；使用“就业智慧决策树模型”量化简历信息与岗位信息的内在联系，并能够通过建立信息，决策最终就职的岗位。所分岗位为：技术类和非技术类，其中技术类分为：开发、测试和技术支持。
（6）、数据计算平台存储结果数据
（7）、用户在用户服务平台输入简历信息
（8）、用户服务平台前端提取简历信息给平台后端
（9）、用户服务平台运用数据计算平台的Model得出适合岗位分类：用户服务平台使用“就业智慧决策树模型”为用户就业岗位提出意见
（10）、数据计算平台返回用户服务平台相关岗位信息
（11）、用户服务平台查询数据：用户服务平台依据得到的是技术类还是非技术类的相关岗位推荐，从数据存储单元查询相对应分类的实习和就业信息
（12）、用户服务平台取出数据：将对应分类的实习和就业信息返回至用户服务平台
（13）、短信推送给用户相关数据
（14）、Web显示相关数据

[（二）方案设计](http://##)
>本项目的技术方案设计包括五部分：
（1）设计并搭建数据采集单元：通过走访、问卷调查、联合社团与院校合作等方式选择获取近几年来高质量南京邮电大学在内的研究生简历以及最终就业单位、岗位信息。通过Scrapy爬虫框架，爬取各大就业信息网（南京邮电大学招生就业创业网、南大小百合BBS等）的就业信息，并对进行数据预处理。
（2）设计并搭建数据计算平台：使用认知计算中机器学习监督式学习多类别支持向量机、朴素贝叶斯算法，构造“就业岗位智慧分类模型”，对所提取的就业信息进行数据分类；采用随机森林算法，智能对用户简历信息进行数据分析，构造“就业智慧决策树模型”，洞察简历信息与就业岗位的内在联系，完成用户岗位信息的预测判决。搭建Hadoop+Spark分布式计算平台，提升模型分析处理能力。
（3）设计并搭建用户服务平台，使用SSH框架完成人机交互服务与业务逻辑设计，数据展示等。
（4）搭建数据存储单元，采用MongoDB数据库，完成数据存储，并配置用户登陆、副本集等功能，保障数据安全和冗余备份。
（5）迭代与验证模型环节：选择2016级应届生春招就业数据作为测试和再学习数据，验证就业咨询智慧服务系统：计算就业推荐岗位预测错误率和岗位分类错误率，并不断学习数据修正“就业岗位智慧分类模型”和“就业智慧决策树模型”。

[（三）软件使用说明书](http://##)
一、软件概述及功能
    本款软件主要服务对象是想求职，应聘IT行业的大学生，研究生或者社会上的求职者，主要提供的服务是为各类求职者提供他们所需要的求职信息，包括研发（开发）类，测试类，技术支持类以及其他岗位的招聘岗位信息，并且可以根据您填写的简历为您匹配合适的岗位，并提供实时的招聘信息展示。
二、软件使用环境
开发环境在Eclipse环境下开发，JAVA部分代码量8000行，Python部分代码量250行。
运行环境：JRE/JDK、python2.7.3、Scrapy(http://scrapy.org/)、MongoDB数据库(https://www.mongodb.org/)
运行前需要将软件包中的Scrapy文件夹以及Data_Classification文件夹拷贝至D盘。并启动软件包中的Tomcat。
三、软件使用说明
    本款软件是基于浏览器/服务器的软件，所以使用工具只要一个IE/Chrome的浏览器。
	首先，在浏览器里输入http://服务器的IP地址://rusume/index1.html
，然后回车（Enter），出来Web软件的主页面，如图1。
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%871.png)
2.1 数据的采集过程
	在Web软件的主页面上，点击左侧的导航栏（Backstage Show），您会看到如下页面:(如图2)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%872.png)

此时，您应该先点击Data Collection，这将调用服务器端的Start.bat批处理文件，在客户端会调用CMD窗口，而服务器端会调用Scrapy框架进行各大门户网站招聘信息数据的采集。在采集完成即将时，Web页面上会出现“OK”，并停留3秒后，消失。如图3,4。
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%873.png)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%874.png)
2.2 数据的分类与处理过程
之后，我们应该点击Data Presentation来完成数据的分类与处理。同样，在数据完成时，Web页面上将会出现“OK”，并停留3秒后，消失。如图5,6。
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%875.png)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%876.png)
2.3 数据的展示
在经过以上两个步骤后，其实服务端已经完成数据的采集，处理以及存储了！之后，我们就可以点击下面DEV，TEST，IT，OTHER来完成研发（开发），测试，技术支持以及其他招聘信息岗位的展示。如图7，8，9，10。
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%877.png)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%878.png)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%879.png)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%810.png)
三、针对个人简历来提供匹配合适的招聘信息
	首先，我们可以在浏览器上输入http://服务器的IP地址:8080/resume/，这时会出现个人简历页面（如图11），求职者应该在上面完成个人简历的填写，其中，您必须选择好论文发表等级。然后，您可以提交简历，交给服务器端进行岗位分类的计算以及招聘信息的展示。在网页的标题上会出现您所适合的岗位，然后在页面上出现符合您岗位的招聘信息，如图 12。
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%8711.png)
![](https://github.com/sainty7/Job-Recommendation-System_SSH_MongoDB_ML_Scrapy/blob/master/photo/%E5%9B%BE%E7%89%8712.png)
四、结束语
	本软件可以很好的为各位IT行业的求职者提供实时的招聘信息展示，并且可以根据您填写的简历匹配合适的岗位信息，并提供精准的招聘信息推送。如您在操作使用的过程中，遇到另外的问题，欢迎大家反映，我们将会做到更好！谢谢！