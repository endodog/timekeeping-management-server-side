package com.endodog.timekeeping_management.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {

  public static String fDateVN() {
    Instant timestamp = Instant.now();
    ZonedDateTime zonedDateTime = timestamp.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
    String formattedDateTime = zonedDateTime.format(formatter);

    return formattedDateTime;
  }

}
