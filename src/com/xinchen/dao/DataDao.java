package com.xinchen.dao;

import java.util.List;
import java.util.Map;

public interface DataDao {
	public abstract Map<String,List<Map<String,String>>> showdata();
}
