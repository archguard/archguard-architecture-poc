package com.example.converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapConverter {
    public static <T> T mapToObj(Map source, Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            Object val;
            if ((val = source.get(field.getName())) != null) {
                field.setAccessible(true);
                field.set(o, val);
            }
        }
        return o;
    }

    public static Map<String, Object> ObjToMap(Object obj, Object nullValue) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = field.get(obj);
            if (value == null) {
                value = nullValue;
            }
            map.put(keyName, value);
        }
        return map;
    }

}
