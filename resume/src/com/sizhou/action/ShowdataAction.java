package com.sizhou.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.xinchen.biz.ShowdataBiz;
import com.xinchen.dao.DataDao;
public class ShowdataAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	ShowdataBiz showdatabiz;
	private Map<String,List<Map<String,String>>> dataMap3 = null;
	public Map<String,List<Map<String,String>>> getDataMap3() {
		return dataMap3;
	}
	public void setDataMap3(Map<String,List<Map<String,String>>> dataMap3) {
		this.dataMap3 = dataMap3;
	}
	public void setShowdatabiz(ShowdataBiz showdatabiz) {
		this.showdatabiz = showdatabiz;
	}
	@Override
	public String execute() 
	{ 
		dataMap3 = showdatabiz.showdata();  
		//System.out.println(dataMap3);
		return SUCCESS;
	}
}
