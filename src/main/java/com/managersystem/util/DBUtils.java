package com.managersystem.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBUtils {

    public DBUtils() {
    }

    public static void close(AutoCloseable closeable) {
        if(closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
