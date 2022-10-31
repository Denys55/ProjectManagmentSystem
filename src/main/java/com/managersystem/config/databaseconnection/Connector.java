package com.managersystem.config.databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connector to Data Base
 */
public interface Connector {

    /**
     * Make connection to database
     * @return Connection to Data Base
     * @throws SQLException if can not make connection to data base
     */
    Connection getConnection() throws SQLException;
}
