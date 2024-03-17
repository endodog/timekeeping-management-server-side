package com.endodog.timekeeping_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TimekeepingManagementApplication {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(TimekeepingManagementApplication.class, args);
  }

}
