package com.homemonitoring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQueries {

    private Statement statement;
    private Connection connection;
    private static final String TEMP_TABLE = "temperature";
    private static final String SOUND_TABLE = "sound";
    private static final String MOTION_TABLE = "motion";

    public DBQueries(DBConnection dbConnection) throws SQLException {
        connection = dbConnection.getConnection();
        statement = connection.createStatement();
    }

    public synchronized void createTables() throws SQLException{
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +TEMP_TABLE+" (module_id varchar(10), reading varchar(10), create_date TIMESTAMP)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+SOUND_TABLE+" (module_id varchar(10), reading varchar(10), create_date TIMESTAMP)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+MOTION_TABLE+" (module_id varchar(10), reading varchar(10), create_date TIMESTAMP)");
    }

    public void insertTemperature(String moduleId, String reading) throws SQLException {
        String insertStatement = "INSERT INTO " + TEMP_TABLE + " (module_id,reading,create_date)" + " VALUES(" + moduleId + "," + reading + ",now())";
        statement.executeUpdate(insertStatement);
    }

    public void insertSound(String moduleId, String reading) throws SQLException {
        statement.executeUpdate("INSERT INTO "+ SOUND_TABLE+" VALUES(" +moduleId+","+reading+",now())");
    }

    public void insertMotion(String moduleId, String reading) throws SQLException {
        statement.executeUpdate("INSERT INTO "+ MOTION_TABLE+" VALUES(" +moduleId+","+reading+",now())");
    }

    public String getTemperature() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM "+TEMP_TABLE);
        while (rs.next()) {
            System.out.println(rs.getString("reading"));
        }
        return "Need to fill this value";
    }
}
