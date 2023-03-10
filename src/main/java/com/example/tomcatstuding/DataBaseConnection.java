package com.example.tomcatstuding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DataBaseConnection {

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQLDataBase", "olejik", "ZehIDy");
    }
}
