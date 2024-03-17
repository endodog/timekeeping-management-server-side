package com.endodog.timekeeping_management.utils;

import com.endodog.timekeeping_management.annotations.Equal;
import com.endodog.timekeeping_management.annotations.Like;
import com.endodog.timekeeping_management.constant.Sort;
import com.endodog.timekeeping_management.model.base.BaseDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class QueryHelper {

  private static final String TWO_DOTS = ":";
  private static final String COMMA_SPACE = ", ";
  private static final String SPACE = " ";
  private static final String ALIAS_ENTITY = "e.";
  private static final String ENTITY = "e";
  private static final String NEW = "new ";
  private static final String LEFT_PARENTHESIS = "(";
  private static final String RIGHT_PARENTHESIS = ")";

  private static final String SELECT = "SELECT ";
  private static final String FROM = " FROM ";
  private static final String WHERE = " WHERE ";
  private static final String AND = " AND ";
  private static final String COUNT = "COUNT";
  private static final String LIKE = " LIKE CONCAT";
  private static final String LEFT_PERCENT = "'%',";
  private static final String RIGHT_PERCENT = ",'%'";
  private static final String EQUAL = " = ";
  private static final String ID = "id";
  private static final String IN = " IN ";
  private static final String DELETE = "DELETE ";

  private static String getParametersByContructorClazz(Class<? extends BaseDTO> dto) {
    Constructor<?>[] constructors = dto.getConstructors();
    StringBuilder sb = new StringBuilder();

    for (Constructor<?> constructor : constructors) {
      if (constructor.getParameterCount() > 0) {
        Parameter[] parameters = constructor.getParameters();

        for (Parameter parameter : parameters) {
          sb.append(ALIAS_ENTITY).append(parameter.getName()).append(COMMA_SPACE);
        }

      }
    }

    if (sb.length() > 0) {
      sb.delete(sb.length() - 2, sb.length());
    }
    return sb.toString();
  }

  private static String buildQueryConditonByExample(Object example) {
    Field[] fields = example.getClass().getDeclaredFields();
    StringBuilder sb = new StringBuilder();
    int count = 0;

    for (Field field : fields) {
      field.setAccessible(true);
      try {
        if (field.getAnnotations().length > 0 && field.get(example) != null) {
          if (count > 0) {
            sb.append(AND);
          }
          sb.append(count == 0 ? WHERE : "");
          sb.append(buildQueryByFieldAnnotation(field));
          count++;
        }
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    return sb.toString();
  }

  private static String buildQueryByFieldAnnotation(Field field) {
    String fieldName = field.getName();
    StringBuilder sb = new StringBuilder(LEFT_PARENTHESIS + ALIAS_ENTITY + fieldName);

    if (field.isAnnotationPresent(Like.class)) {
      sb.append(LIKE + LEFT_PARENTHESIS)
              .append(LEFT_PERCENT + TWO_DOTS + fieldName + RIGHT_PERCENT)
              .append(RIGHT_PARENTHESIS + RIGHT_PARENTHESIS);
    }

    if (field.isAnnotationPresent(Equal.class)) {
      sb.append(EQUAL + TWO_DOTS + fieldName + RIGHT_PARENTHESIS);
    }

    return sb.toString();
  }

  private static String getPackagePathByClazz(Class<? extends BaseDTO> dto) {
    return dto.getName();
  }

  private static String buildQueryByDto(Class<? extends BaseDTO> dto, String entityName) {
    String getPackage = getPackagePathByClazz(dto);
    String getParameters = getParametersByContructorClazz(dto);

    StringBuilder sb = new StringBuilder(SELECT + NEW + getPackage)
            .append(LEFT_PARENTHESIS + getParameters + RIGHT_PARENTHESIS)
            .append(FROM + entityName + SPACE + ENTITY);

    return sb.toString();
  }

  public static class QueryFactory {

    public static String findAll(Class<? extends BaseDTO> dto, String entityName, Sort sort) {
      StringBuilder sb = new StringBuilder(buildQueryByDto(dto, entityName))
              .append(sort.build());
      return sb.toString();
    }

    public static String findAll(Class<? extends BaseDTO> dto, String entityName, Object example, Sort sort) {
      StringBuilder sb = new StringBuilder(buildQueryByDto(dto, entityName))
              .append(buildQueryConditonByExample(example))
              .append(sort.build());
      return sb.toString();
    }

    public static String findAllByIds(Class<? extends BaseDTO> dto, String entityName) {
      StringBuilder sb = new StringBuilder(buildQueryByDto(dto, entityName))
              .append(WHERE + ALIAS_ENTITY + ID + IN + "?1");
      return sb.toString();
    }

    public static String findById(Class<? extends BaseDTO> dto, String entityName) {
      StringBuilder sb = new StringBuilder(buildQueryByDto(dto, entityName))
              .append(WHERE).append(ALIAS_ENTITY).append(ID).append(EQUAL + "?1");
      return sb.toString();
    }

    public static String count(String entityName, Object example) {
      StringBuilder sb = new StringBuilder(SELECT + COUNT)
              .append(LEFT_PARENTHESIS + ENTITY + RIGHT_PARENTHESIS)
              .append(FROM + entityName + SPACE + ENTITY)
              .append(example != null ? buildQueryConditonByExample(example) : "");
      return sb.toString();
    }

    public static String deleteById(String entityName) {
      StringBuilder sb = new StringBuilder(DELETE + FROM + entityName)
              .append(WHERE + ALIAS_ENTITY + ID + EQUAL + "?1");
      return sb.toString();
    }

    public static String deleteByIds(String entityName) {
      StringBuilder sb = new StringBuilder(DELETE + FROM + entityName)
              .append(WHERE + ALIAS_ENTITY + ID + IN + "?1");
      return sb.toString();
    }

    public static String deleteAll(String entityName) {
      return DELETE + FROM + entityName;
    }

  }

  public static void main(String[] args) {
//    String query = getPropertiesNameByClazz(ConstructionRequestParams.class);
//    String query = QueryFactory.findAll(ConstructionDTO.class, "COns", new ConstructionRequestParams("a", StatusConstruction.COMPLETED));
//    String query = queryConcat("name");
//    String a = buildQuerySortOrderBy(CostDTO.class);
//    System.out.println(a);
  }

}
