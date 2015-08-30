package com.homemonitoring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static DBConnection ourInstance;

    static {
        try {
            ourInstance = new DBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String FILENAME = "/tmp/dbfile.txt";
    private  Connection connection;


    /**
     * Get the singleton instance of DBConnection
     *
     * @return
     */
    public static DBConnection getInstance() {
        return ourInstance;
    }

    /**
     * Private Constructor for the DBConnection singleton class
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
//        connection = DriverManager.getConnection("jdbc:hsqldb:" + FILENAME+";ifexists=true", "SA", "");
        connection = DriverManager.getConnection("jdbc:hsqldb:" + FILENAME, "SA", "");
    }

    public void shutDown() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SHUTDOWN");
        connection.close();
    }

    public Connection getConnection(){
        return this.connection;
    }

}
