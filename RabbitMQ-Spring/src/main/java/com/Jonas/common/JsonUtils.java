package com.Jonas.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class JsonUtils {
	
	public static String toJSONString(Object object) throws Exception {
		try {
			return JSON.toJSONString(object,SerializerFeature.WriteMapNullValue);
		} catch (Throwable e) {
			throw new Exception("Mapping Object to json failed!", e);
		}
	}

	public static Object fromJSONString(String json, Class<?> clazz)
			throws Exception {
		try {
			return JSON.parseObject(json, clazz);
		} catch (Throwable e) {
			throw new Exception("Mapping from json failed!", e);
		}
	}
	
	
	public static JSONObject stringToJson(String content) throws Exception{
		try {
			return JSON.parseObject(content);
		} catch (Exception e) {
			throw new Exception("Mapping from json failed!", e);
		}
	}
	
	
	public static void main(String[] args){
		String content = "{ '_id' : '53795bb11a067220bd7d7bc1', 'userId' : '4e97fbeecce7b2ca3259658f', 'networkId' : '383cee68-cea3-4818-87ae-24fb46e081b1', 'userSeq' : '10003941', 'accountName' : 'yuanfei@kingdee.com', 'createTime' : ISODate('2014-05-19T01:17:37.433Z') }";
		
		content = "{'_id' : '53795bb11a067220bd7d7bc1',userId:'1111',accountName:'32222','createTime' : ISODate('2014-05-19T01:17:37.433Z')}";
		
		content= "{ '_id' : '53795bb11a067220bd7d7bc1', 'userId' : '4e97fbeecce7b2ca3259658f', 'networkId' : '383cee68-cea3-4818-87ae-24fb46e081b1', 'userSeq' : '10003941', 'accountName' : 'yuanfei@kingdee.com'}";
		
		
		try{
		    JSONObject json = JsonUtils.stringToJson(content);
		    System.out.println(json.getString("userId"));
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
