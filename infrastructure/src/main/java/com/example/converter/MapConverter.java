package com.example.converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapConverter {
    public static <T> T mapToObj(Map<String, String> source, Class<T> target) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> ObjToMap(Object obj) {
        try {
            Map<String, String> map = new HashMap<>();
            Class<?> cla = obj.getClass();
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String keyName = field.getName();
                Object value = field.get(obj);
                if (value == null) {
                    value = "";
                }
                map.put(keyName, value.toString());
            }
            return map;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public static void putField(Object obj, String fieldName, Object fieldObj) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldObj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
