package com.endodog.timekeeping_management.constant;

public enum Sort {
  ASC(" ASC"),
  DESC(" DESC");

  private final static String ORDER_BY = " ORDER BY ";
  private final static String COMMA_SPACE = ", ";
  private final static String EMPTY = "";
  private final static String ALIAS = "e.";
  private static StringBuilder sortQuery = new StringBuilder(EMPTY);
  private String sort;

  Sort(String sort) {
    this.sort = sort;
  }

  public String getSort() {
    return sort;
  }

  public static Sort byDesc(String fieldName) {
    sortQuery.append(sortQuery.length() > 0 ? EMPTY : ORDER_BY)
            .append(ALIAS + fieldName + DESC.getSort());

    return DESC;
  }

  public Sort and() {
    sortQuery.append(COMMA_SPACE);
    return this;
  }

  public static Sort byAsc(String fieldName) {
    sortQuery.append(sortQuery.length() > 0 ? EMPTY : ORDER_BY)
            .append(ALIAS + fieldName + ASC.getSort());

    return ASC;
  }

  public String build() {
    String result = sortQuery.toString();
    sortQuery = new StringBuilder(EMPTY);
    return result;
  }

}
