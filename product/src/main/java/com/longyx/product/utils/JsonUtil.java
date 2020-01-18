package com.longyx.product.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Mr.Longyx
 * @date 2020年01月14日 11:18
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转换为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     * @author Mr.Longyx
     * @date 2020/1/14 19:51
     */
    public static<T> T fromJson(String str, Class classType) {
        try {
            return (T) objectMapper.readValue(str, classType);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象(反序列化)
     * @author Mr.Longyx
     * @date 2020/1/15 16:43
     * @param str
     * @param typeReference
     * @return T
     */
    public static<T> T fromJson(String str, TypeReference typeReference) {
        try {
            return (T) objectMapper.readValue(str, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
