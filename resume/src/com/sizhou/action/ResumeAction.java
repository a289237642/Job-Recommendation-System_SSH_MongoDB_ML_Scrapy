package com.sizhou.action;
import java.util.HashMap;
import java.util.Map;

import test.DifferentSchoolTestData;
import util.Test;
import analyzer.DifferentSchoolAnalyzer;

import com.opensymphony.xwork2.ActionSupport;
public class ResumeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private Map datamap;
    public Map getDatamap() {
		return datamap;
	}
	public void setDatamap(Map datamap) {
		this.datamap = datamap;
	}
	
	public void diaoyong(){	
		Test.start();
	}
	public void document_processor()
	{
		String[] args = {"2.0"};
		DifferentSchoolAnalyzer.run(args);
		String[] arg = {"10.128.2.168","2.0"};
		DifferentSchoolTestData.run(arg);
	}
	@Override
	public String execute() throws Exception {
		try {
			//diaoyong();
			document_processor();
			datamap = new HashMap<String, String>();  
			datamap.put("result", "ok");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return SUCCESS;
	}
}
