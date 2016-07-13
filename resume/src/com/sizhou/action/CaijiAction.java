package com.sizhou.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class CaijiAction extends ActionSupport {
	private Map datamap2;
	public Map getDatamap2() {
		return datamap2;
	}
	public void setDatamap2(Map datamap2) {
		this.datamap2 = datamap2;
	}
	@Override
	public String execute() throws Exception {
     Scrapydemo.startCai();
     datamap2 = new HashMap<String, String>();  
	 datamap2.put("result", "ok");
     return SUCCESS;
	}

}
