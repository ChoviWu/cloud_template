package org.github..common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2018/6/20
 * Description :
 */
public class JsonUtils extends ObjectMapper{

    public static String toJson(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //将json 对象转为Map
    public static Map json2Map(String jsondata){

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = new HashMap<>();

        try {
            map=mapper.readValue(jsondata, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    //将json对象转为指定对象类型
    public static <T> T json2Object(String jsondata,Class<T> tClass){

        ObjectMapper mapper = new ObjectMapper();
        //Map<String,Object> map = new HashMap<>();
        T o=null;
        try {
            o = mapper.readValue(jsondata, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return o;
    }
    /**
     * Json  获取List泛型数据
     * @param clazz
     * @param json
     * @return
     */
    public static List jsoncastListType(Class clazz, String json){
        ObjectMapper mapper = new ObjectMapper();
        List<Object> list = new ArrayList<>();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            list =  mapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//    /**
//     * RabbitMQ unSerializable
//     * @param message   MQ
//     * @param <T>       T
//     * @return
//     */
//    public static <T> T jackon2ToObject(Message message){
//        return (T)new Jackson2JsonMessageConverter().fromMessage(message);
//    }

}
