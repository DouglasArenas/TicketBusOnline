package com.um.main.services;

import java.lang.reflect.Field;

public class IsNotEmpty {
    
    public static boolean isNotEmpty(Object object) {
        if (object == null) {
            return false;
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals("id")) {
                continue;
            }
            field.setAccessible(true);
            try {
                Object fieldObject = field.get(object);
                if (fieldObject == null || "".equals(fieldObject) || (fieldObject instanceof Integer && (Integer) fieldObject == 0)) {
                    System.out.println("Field " + field.getName() + " is empty");
                    return false;
                }
            } catch (IllegalAccessException e) {
                System.out.println("Field " + field.getName() + " is empty");
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean updateObject(Object object, Object newObject) {
        if (object == null || newObject == null) {
            return false;
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(newObject) != null && field.get(newObject) != "") {
                    field.set(object, field.get(newObject));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
