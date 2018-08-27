package com.ccsu.client.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2018/8/14
 * *********************
 * function: 反射工具类
 */
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    public static Field[] getFields(Class clazz) {
        return clazz.getDeclaredFields();
    }

    public static Field[] getFields(Object obj) {
        Class<?> clazz = obj.getClass();
        return getFields(clazz);
    }

    public static List<Field> getFieldsWithAnnotation(Class clazz, Class annotation) {
        Field[] fields = getFields(clazz);
        List<Field> result = new ArrayList<>();
        if (fields == null || fields.length == 0) return null;
        for (Field field : fields) {
            Annotation anno = field.getAnnotation(annotation);
            if (anno != null) {
                result.add(field);
            }
        }
        return result;
    }

    public static List<Field> getFieldsWithAnnotation(Object obj, Class annotation) {
        Class<?> clazz = obj.getClass();
        return getFieldsWithAnnotation(clazz, annotation);
    }

    public static Object getFieldContent(Object obj, Field field) {
        Object result = null;
        try {
            field.setAccessible(true);
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public static void setFieldContent(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Method[] getMethods(Class clazz) {
        return clazz.getDeclaredMethods();
    }

    public static Method[] getMethods(Object obj) {
        return getMethods(obj.getClass());
    }

    public static List<Method> getMethodWithAnnotation(Class clazz, Class annotation) {
        List<Method> result = new ArrayList<>();
        Method[] methods = getMethods(clazz);
        for (Method method : methods) {
            Annotation anno = method.getAnnotation(annotation);
            if (anno != null) {
                result.add(method);
            }
        }
        return result;
    }

    public static Object invokeMethod(Method method, Object obj) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException("方法执行异常, message:" + e.getMessage());
        }
        return result;
    }

    public static List<Method> getMethodWithAnnotation(Object obj, Class annotaion) {
        return getMethodWithAnnotation(obj.getClass(), annotaion);
    }

}
