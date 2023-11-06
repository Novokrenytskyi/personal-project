package com.novo.personalproject.util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@UtilityClass
public final class ConnectionManager {
    private static final String USERNAME_KEY = "spring.datasource.username";
    private static final String PASSWORD_KEY = "spring.datasource.password";
    private static final String URL_KEY = "spring.datasource.url";


    public static Connection open() {
       try {
         return DriverManager.getConnection(PropertiesUtil.get(URL_KEY),
                   PropertiesUtil.get(USERNAME_KEY),
                   PropertiesUtil.get(PASSWORD_KEY));
       } catch (SQLException ex) {
           throw new RuntimeException(ex);
       }
    }
}
