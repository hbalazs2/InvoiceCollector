package com.example.InvoiceCollector.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector instance;
    private Connection connection;

    public static synchronized DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
            return instance;
        }
        return instance;
    }

    private DBConnector() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/invoicecollector",
                    "admin",
                    "password"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
