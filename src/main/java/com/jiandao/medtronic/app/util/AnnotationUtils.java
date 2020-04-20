package com.jiandao.medtronic.app.util;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationUtils {
  private static Reflections reflections = new Reflections(
          ClasspathHelper.forPackage("com.example.polls"),
          new MethodAnnotationsScanner(),
          new FieldAnnotationsScanner(),
          new TypeAnnotationsScanner(),
          new SubTypesScanner()
  );
  public static <T extends Annotation> List<T> findAllAnnotations(Class<T> annotation){
    List<T> types = reflections
            .getTypesAnnotatedWith(annotation)
            .stream()
            .map(x -> x.getAnnotation(annotation))
            .collect(Collectors.toList());

    List<T> methods = reflections
            .getMethodsAnnotatedWith(annotation)
            .stream()
            .map(x -> x.getAnnotation(annotation))
            .collect(Collectors.toList());

    List<T> fields = reflections
            .getFieldsAnnotatedWith(annotation)
            .stream()
            .map(x -> x.getAnnotation(annotation))
            .collect(Collectors.toList());

    return Stream.of(types, fields, methods).flatMap(Collection::stream).collect(Collectors.toList());
  }
}
