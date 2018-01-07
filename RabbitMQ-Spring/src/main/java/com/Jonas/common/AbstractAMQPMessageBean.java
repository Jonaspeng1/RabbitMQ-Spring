package com.Jonas.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;



public abstract class AbstractAMQPMessageBean {
    
    
    public static String toJSONString(Object object) throws Exception {
        try {
            return JSON.toJSONString(object,SerializerFeature.WriteMapNullValue);
        } catch (Throwable e) {
            throw new Exception("Mapping Object to json failed!", e);
        }
    }
    
    public static Object fromJSONString(String json, Class<?> clazz) throws Exception {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Throwable e) {
            throw new Exception("Mapping from json failed!", e);
        }
    }
}
