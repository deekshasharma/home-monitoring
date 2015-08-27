package com.homemonitoring.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    Connection connection;

    public DbConnection(String fileName) throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:"+fileName,"sa","");
    }

    public void shutDown() throws SQLException {
        Statement statement = connection.createStatement();
    }

}
