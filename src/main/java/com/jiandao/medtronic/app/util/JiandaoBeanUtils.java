package com.jiandao.medtronic.app.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JiandaoBeanUtils {

  public static <T> void copyPropertiesIgnoreNull(
      T src, T dst, List<String> ignoreFields, Class<?> classType){
    if (src == null || dst == null) {
      return;
    }

    Field[] allFields = classType.getDeclaredFields();
    try {
      for (Field field : allFields) {
        if (ignoreFields.contains(field.getName())) {
          continue;
        }
        field.setAccessible(true);
        Object value = field.get(src);
        if (value == null) {
          continue;
        }
        field.set(dst, value);
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public static <T> void copyPropertiesIgnoreNull(T src, T dst, Class<?> classType) {

    copyPropertiesIgnoreNull(src, dst, new ArrayList<>(), classType);
  }

  public static <T> void copyPropertiesIgnoreNull(T src, T dst) {

    copyPropertiesIgnoreNull(src, dst, new ArrayList<>(), src.getClass());
  }
}
