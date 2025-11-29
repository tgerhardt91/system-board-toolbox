package org.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:common/main/resources/data/tension_dump.db"; // or path to your .db file

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
