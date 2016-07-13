# This package will contain the spiders of your Scrapy project
#
# Please refer to the documentation for information on how to create and manage
# your spiders.
import scrapy
from SEU_Intern.items import DmozItem
import sys  
import codecs
import string
from lxml import etree
from selenium import webdriver
from scrapy.selector import Selector

'''class DmozSpider_(scrapy.Spider):
	name = "dmoz_"
	allowed_domains = ["njupt.91job.gov.cn"]
	start_urls = ["http://njupt.91job.gov.cn/job/search/d_category%5B0%5D/0/d_category%5B1%5D/101/d_category%5B2%5D/102"]
	def parse(self, response):
		uls = response.xpath("//ul[@class='infoList']")
		for ul in uls:
			url_detail = 'http://njupt.91job.gov.cn'+''.join(ul.xpath(".//li[@class='span1']/a/@href").extract())
			print url_detail
			yield scrapy.Request(url_detail,callback = self.parse_detail)
			
	def parse_detail(self,response):
		data = response.xpath("//div[@class='vContent cl']")
		info = ''.join(data.xpath('string(.)').extract())
		print info


class NUPTSpider(scrapy.Spider):
	reload(sys)  
	sys.setdefaultencoding('utf8')
	global file
	file = open('D:\\NUPT.txt','w')
	name = "intern_nupt"
	allowed_domains = ["njupt.91job.gov.cn"]
	start_urls = ["http://njupt.91job.gov.cn/job/search/d_category%5B0%5D/0/d_category%5B1%5D/101/d_category%5B2%5D/102"]
	def parse(self,response):
		page_num = 34
		uls = response.xpath("//ul[@class='infoList']")
		for ul in uls:
			item= DmozItem()
			item['intern_info'] = ''.join(ul.xpath(".//li[@class='span1']/a[@href]/text()").extract())
			item['intern_url'] = 'njupt.91job.gov.cn'+''.join(ul.xpath(".//li[@class='span1']/a/@href").extract())
			item['intern_company'] = ''.join(ul.xpath(".//li[@class='span2']/a/text()").extract())
			item['intern_location']= ''.join(ul.xpath(".//li[@class='span3']/text()").extract())
			item['intern_date'] = ''.join(ul.xpath(".//li[@class='span4']/text()").extract())
			url_detail = 'http://njupt.91job.gov.cn'+''.join(ul.xpath(".//li[@class='span1']/a/@href").extract())
			yield scrapy.Request(url_detail,callback = self.parse_detail,meta={'item': item})
		for i in range(2,page_num):
			next_page_url = "http://njupt.91job.gov.cn/job/search?d_category[0]=0&d_category[1]=101&d_category[2]=102&page="+str(i)
			yield scrapy.Request(next_page_url,callback = self.parse)

	def parse_detail(self,response):
		item = response.meta['item']
		data = response.xpath("//div[@class='vContent cl']")
		info = str(''.join(data.xpath('string(.)').extract())).replace('\n','').replace('\r','').replace(' ','')
		item['intern_detail'] = info
		if item['intern_detail'] == '':
			item['intern_detail'] = 'null'
		line = item['intern_date']+'\t'+item['intern_info']+'\t'+item['intern_url']+'\t'+item['intern_detail']+'\n'
		file.writelines(line)
		yield item'''


class DmozSpider(scrapy.Spider):
	reload(sys)
	sys.setdefaultencoding('utf-8')
	name = "dmoz"
	#allowed_domains = ["bbs.seu.edu.cn"]
	start_urls = ["http://bbs.seu.edu.cn/bbsdoc.php?board=JobExpress&page=171"]
	global browser,month_dict,file
	file = open('D:\\Data_Classification\\bayes\\test\\scrapy\\SEU.txt','w')
	month_dict = {'Jan':'01','Feb':'02','Mar':'03','Apr':'04','May':'05','Jun':'06','Jul':'07','Aug':'08','Sep':'09','Oct':'10','Nov':'11','Dec':'12'}
	browser = webdriver.Chrome()
	def parse(self,response):
		browser.get(response.url)
		html = browser.page_source
		selector = etree.HTML(html)
		uls = selector.xpath("//tbody/tr")
		for ul in uls:
			if(not ul.xpath(".//td")):
				continue
			item= DmozItem()
			item['intern_info'] = ''.join(ul.xpath(".//td[6]/a/text()"))
			item['intern_url'] = 'http://bbs.seu.edu.cn/'+''.join(ul.xpath(".//td[6]/a/@href"))
			date = ''.join(ul.xpath(".//td[5]/nobr/text()")).split()
			Month = month_dict[date[0]]
			if int(date[1]) < 10:
				item['intern_date'] = '2015-'+Month+'-0'+date[1]
			else:
				item['intern_date'] = '2015-'+Month+'-'+date[1]
			#print item['intern_date']
			yield scrapy.Request(item['intern_url'],callback = self.parse_detail,meta={'item': item})
		for i in range(172,282):
			next_page = "http://bbs.seu.edu.cn/bbsdoc.php?board=JobExpress&page="+str(i)
			yield scrapy.Request(next_page,callback = self.parse)

	def parse_detail(self,response):
		browser.get(response.url)
		detail_source = browser.page_source
		selector_detail = etree.HTML(detail_source)
		info = selector_detail.xpath("//div[@class='article']")[0]
		item = response.meta['item']
		item['intern_detail'] = info.xpath("string(.)").replace('\n','').replace('\r','').replace('\t','')
		if item['intern_detail'] == '':
			item['intern_detail'] = 'null'
		line = item['intern_date']+'\t'+item['intern_info']+'\t'+item['intern_url']+'\t'+item['intern_detail']+'\n'
		file.writelines(line)
		yield item