package ovy.framework.mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassInfo {
    public static boolean classHasAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        if (clazz.isAnnotationPresent(annotation)) {
            return true;
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) {
                return true;
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                return true;
            }
        }

        return false;
    }

    public static List<Class<?>> getClassesWithAnnotation(List<Class<?>> classes, Class<? extends Annotation> annotation) {
        List<Class<?>> result = new ArrayList<>();

        for (Class<?> clazz : classes) {
            if (classHasAnnotation(clazz, annotation)) {
                result.add(clazz);
            }
        }

        return result;
    }
}
