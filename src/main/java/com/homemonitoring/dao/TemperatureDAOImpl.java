package com.homemonitoring.dao;

import com.homemonitoring.model.Temperature;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TemperatureDAOImpl implements TemperatureDAO {

    private Statement statement;
    private Connection connection;
    private static final String TEMP_TABLE = "temperature";
    private DBConnection dbConnection = DBConnection.getInstance();
    private CreateDBTables dbTables;

    public TemperatureDAOImpl() throws SQLException {
        dbTables = new CreateDBTables(dbConnection);
        dbTables.create();
        connection = dbConnection.getConnection();
        statement = connection.createStatement();
    }

    /**
     * @param moduleId Unique Id of the module
     * @param reading  reading from temperature sensor
     */
    public void insert(String moduleId, String reading) {
        String insertStatement = "INSERT INTO " + TEMP_TABLE + " (module_id,reading,create_date)" + " VALUES(" + moduleId + "," + reading + ",now())";
        try {
            statement.executeUpdate(insertStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return List of all Temperatures
     */
    public List<Temperature> findAll() {
        ResultSet rs;
        List<Temperature> temperatures = new ArrayList<Temperature>();
        try {
            rs = statement.executeQuery("SELECT * FROM " + TEMP_TABLE);
            while (rs.next()) {
                Temperature temperature = new Temperature(rs.getString("module_id"),rs.getString("reading"),rs.getString("create_date"));
                temperatures.add(temperature);
                System.out.println(rs.getString("reading"));
                System.out.println(rs.getString("module_id"));
                System.out.println(rs.getDate("create_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temperatures;
    }
}
