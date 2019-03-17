package com.acui.merchant.common.utils;

import org.apache.commons.beanutils.BeanMap;

import java.lang.reflect.Field;
import java.util.Map;

public class BeanUtils {
    public static Object mapToObject(Map map, Class<?> beanClass) throws Exception {
        if (map == null) return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj,map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null)
            return null;
        return new BeanMap(obj);
    }

    public static void transformBeanToMap(Object object,Map<String, String>map)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            field.setAccessible(true);
            Object value = field.get(object);
            map.put(field.getName(), value+"");
        }
    }
}
