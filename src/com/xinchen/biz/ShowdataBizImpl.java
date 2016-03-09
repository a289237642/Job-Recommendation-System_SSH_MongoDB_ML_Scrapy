package com.xinchen.biz;

import java.util.List;
import java.util.Map;

import com.xinchen.dao.DataDao;

public class ShowdataBizImpl implements ShowdataBiz {

	DataDao datadao;
	public void setDatadao(DataDao datadao) {
		this.datadao = datadao;
	}

	@Override
	public Map<String,List<Map<String,String>>> showdata() {
		// TODO Auto-generated method stub
		Map<String,List<Map<String,String>>> result = datadao.showdata();
		return result;
	}

}
