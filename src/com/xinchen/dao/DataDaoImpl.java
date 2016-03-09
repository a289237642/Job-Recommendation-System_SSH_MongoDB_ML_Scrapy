package com.xinchen.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class DataDaoImpl implements DataDao {

	private static Mongo conn = null;
	private static DB db = null;
	private static DBCollection coll = null;
	private String[] schools = {"NUPT","NUAA","NUST","SEU"};
	private String[] tec_classfication = {"TEC","NO_TEC"};
	private String[] jobs_classfication = {"DEV","TEST","IT","OTHER"};
	
	public DBCollection getCollection(String host_port,String dbName,String collName) throws UnknownHostException, MongoException
	{
		this.conn = new Mongo(host_port);
		this.db = conn.getDB(dbName);
		this.coll = db.getCollection(collName);
		return coll;
	}
	public void insertOneDate(DBCollection coll,BasicDBObject document1)
	{
		BasicDBObject document = document1;
		coll.insert(document);
	}
	/* 执行插入单条语句
	 * 外部调用方法
	 * BasicDBObject document = new BasicDBObject();
	 * document.put("id",1);
	 * document.put("msg","helloworld");
	 * List<DBObject> documents = new ArrayList<DBObject>();
	 * documents.add(document);
	 * 将documents传入函数
	 * @param coll , documents
	 * @return cursor
	 */
	public void insertSeveralData(DBCollection coll , List<DBObject> documents1)
	{
		List<DBObject> documents = documents1;
		coll.insert(documents);
	}
	/* 执行查询语句
	 * 外部调用方法
	 * BasicDBObject searchQuery = new BasicDBObject();
	 * searchQuery.put("id",1)
	 * 将searchQuery传入函数
	 * @param coll serachQuery
	 * @return cursor
	 * 输出DBCursor的玩法：
	 * while(cursor.hasNext())
	 * {
	 * 	   System.out.println(cursor.next());
	 * }
	 */
	public DBCursor find(DBCollection coll,BasicDBObject searchQuery)
	{
		DBCursor cursor = coll.find(searchQuery);//还有一种findOne(DBObject o)  Returns a single object from this collection matching the query.
		//findOne(Object obj) Finds an object by its id.
		return cursor;
	}
	public DBCursor findAll(DBCollection coll)
	{
		DBCursor cursor = coll.find();//还有一种findOne(DBObject o)  Returns a single object from this collection matching the query.												 //findOne(Object obj) Finds an object by its id.
		return cursor;
	}
	
	public static long count(DBCollection coll,BasicDBObject searchQuery)
	{
		return coll.count(searchQuery);
	}
	
	public void delete(DBCollection coll,BasicDBObject document)
	{
		coll.remove(document);
	}
	
	public void update(DBCollection coll,BasicDBObject old_document,BasicDBObject new_document)
	{
		coll.update(old_document, new_document);
	}
	@Override
	public Map<String,List<Map<String,String>>> showdata() 
	{
		Map<String,List<Map<String,String>>> result = new HashMap<String,List<Map<String,String>>>();
		Calendar now = Calendar.getInstance();
		String dbName = now.get(Calendar.YEAR)+"_"+(now.get(Calendar.MONTH) + 1)+"_"+now.get(Calendar.DAY_OF_MONTH);
		for(int j=0;j<4;j++)
		{
			List<Map<String,String>> jobs = new ArrayList<Map<String,String>>();	
			for(int i=0;i<4;i++)
			{
				try {
					String schoolname = schools[i];
					DBCollection coll = this.getCollection("10.128.4.134",dbName,schoolname+"_"+this.tec_classfication[0]+"_"+this.jobs_classfication[j]);
					DBCursor cursor = this.findAll(coll);
					while(cursor.hasNext())
					{
						DBObject document =  cursor.next();
						jobs.add(document.toMap());
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			result.put(this.jobs_classfication[j],jobs);
		}
		return result;
	}

}
