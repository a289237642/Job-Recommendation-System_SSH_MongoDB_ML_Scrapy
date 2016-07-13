package com.sizhou.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import randomforest.RandomForestPrediction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xinchen.biz.ShowdataBiz;

public class GetResumeDataAction extends ActionSupport implements ModelDriven<Resumer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ShowdataBiz showdatabiz;
	public void setShowdatabiz(ShowdataBiz showdatabiz) {
		this.showdatabiz = showdatabiz;
	}
	

	private Resumer resumer = new Resumer();
	
	Map job = new HashMap();
	
	@Override
	public Resumer getModel() {
		return resumer;
	}
	@Override
	public String execute() throws Exception {
	StringBuffer sb = new StringBuffer();
	
	if(resumer.getSex().equals("男"))
	{
		sb.append("1"+" ");
	}else{
		sb.append("0"+" ");
	}
	sb.append((Integer.valueOf(resumer.getPm())/10+1)+" ");
	sb.append((resumer.getLevel()[0])+" ");
	sb.append(resumer.getSkill()[0]+" "+
			  resumer.getSkill()[1]+" "+
			  resumer.getSkill()[2]+" "+
			  resumer.getSkill()[3]+" "+
			  resumer.getSkill()[4]+" "+
			  resumer.getSkill()[5]+" "+
			  resumer.getSkill()[6]+" "+
			  resumer.getSkill()[7]+" "+
			  resumer.getSkill()[8]+" "+
			  resumer.getSkill()[9]+" "+
			  resumer.getSkill()[10]+" "+
			  resumer.getSkill()[11]+" "
			);
	sb.append(((resumer.getSx()==null)?0:resumer.getSx())+" ");
	sb.append(((resumer.getXg()==null)?0:resumer.getXg())+" ");
	sb.append(((resumer.getSt()==null)?0:resumer.getSt())+" ");
	sb.append(resumer.getFlag());
	sb.append(" 0");
	//System.out.println(sb.toString());
	File f =new File("D:/Data_Classification/randomforest/input.txt");
	if(f.exists())
	{
		f.delete();
		f =new File("D:/Data_Classification/randomforest/input.txt");
	}
	OutputStream os = new FileOutputStream(f); 
	OutputStreamWriter osw = new OutputStreamWriter(os);
	osw.write(sb.toString());
	osw.close();
	os.close();
	RandomForestPrediction p = new RandomForestPrediction();
	int predict_res = p.predict();
	System.out.println(predict_res);
	job.put(1, "DEV");
	job.put(2, "TEST");
	job.put(3, "IT");
	job.put(4, "OTHER");
	Map<String,List<Map<String,String>>> res_tmp = new HashMap<String,List<Map<String,String>>>();
	res_tmp = showdatabiz.showdata(); 
	System.out.println(job.get(predict_res));
	//System.out.println(res_tmp.get(job.get(predict_res)).toString());
	Map<String,List<Map<String,String>>> res = new HashMap<String,List<Map<String,String>>>();
	res.put(job.get(predict_res).toString(),res_tmp.get(job.get(predict_res)));
	JSONObject json = new JSONObject();
	json.accumulate("ok", res_tmp.get(job.get(predict_res)));
	ServletActionContext.getRequest().setAttribute("data",json);
	ServletActionContext.getRequest().setAttribute("gangwei",job.get(predict_res));
	//System.out.println(json.toString());
	return SUCCESS;
	}
		
	
	
	
	
	
}
