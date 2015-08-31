package com.homemonitoring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTablesDAO {

    private Statement statement;
    private Connection connection;
    private static final String TEMP_TABLE = "temperature";
    private static final String SOUND_TABLE = "sound";
    private static final String MOTION_TABLE = "motion";

    public DBTablesDAO(DBConnection dbConnection) throws SQLException {
        connection = dbConnection.getConnection();
        statement = connection.createStatement();
    }

    public synchronized void create() throws SQLException{
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +TEMP_TABLE+" (module_id char(50), reading varchar(10), create_date TIMESTAMP)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+SOUND_TABLE+" (module_id nvarchar(50), reading varchar(10), create_date TIMESTAMP)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+MOTION_TABLE+" (module_id nvarchar(50), reading varchar(10), create_date TIMESTAMP)");
    }

//    public void insertSound(String moduleId, String reading) throws SQLException {
//        statement.executeUpdate("INSERT INTO "+ SOUND_TABLE+" VALUES(" +moduleId+","+reading+",now())");
//    }
//
}
