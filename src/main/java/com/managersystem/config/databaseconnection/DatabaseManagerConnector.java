package com.managersystem.config.databaseconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Manager that make connection to SQL Data Base
 */
public class DatabaseManagerConnector implements Connector {
    private String url;
    private Properties data;
    private static DatabaseManagerConnector instance;

    /**
     * Pattern Singelton make just one object this class
     *
     * @return connection manager
     */
    public static synchronized DatabaseManagerConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseManagerConnector();
            instance.loadProperties();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        loadProperties();
        return DriverManager.getConnection(url, data);
    }

    /**
     * Load information that help make connection to Data Base
     */
    private void loadProperties() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties keysDataBase = new Properties();
            keysDataBase.load(stream);
            url = keysDataBase.getProperty("url");
            String username = keysDataBase.getProperty("username");
            String password = keysDataBase.getProperty("password");
            String driverClassName = keysDataBase.getProperty("driverClassName");
            data = new Properties();
            data.setProperty("user", username);
            data.setProperty("password", password);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
