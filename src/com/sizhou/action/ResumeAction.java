package com.sizhou.action;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import test.DifferentSchoolTestData;
import analyzer.DifferentSchoolAnalyzer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sizhou.Test;


public class ResumeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Map datamap;
	
	/*public void diaoyong(){
    
    		 Test.start();
    		// ActionContext.getContext().getSession().put("result","ok");
       }
	 */
	public Map getDatamap() {
		return datamap;
	}
	public void setDatamap(Map datamap) {
		this.datamap = datamap;
	}
	public void document_processor()
	{
		String[] args = {"2.0"};
		DifferentSchoolAnalyzer.run(args);
		String[] arg = {"10.128.4.134","2.0"};
		DifferentSchoolTestData.run(arg);
	}
	@Override
	public String execute() throws Exception {
		try {
			document_processor();
			datamap = new HashMap<String, String>();  
			datamap.put("result", "ok");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return SUCCESS;
	}
}
