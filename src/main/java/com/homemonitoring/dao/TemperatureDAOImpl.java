package com.homemonitoring.dao;

import com.homemonitoring.Attributes.DBAttributes;
import com.homemonitoring.model.Temperature;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

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
    private static final String SELECT_ALL_QUERY = "SELECT * FROM " + TEMP_TABLE;
    private static final String SELECT_RECENT = "SELECT * FROM "+TEMP_TABLE+ " ORDER BY "+ DBAttributes.CREATE_DATE.getColumnName()+" DESC LIMIT 0,2;";
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
        String insertStatement = "INSERT INTO " + TEMP_TABLE + " VALUES(" +"'"+ moduleId + "'"+"," + "'"+reading + "'"+",now())";
        System.out.println("Inserting this => "+insertStatement);
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
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(SELECT_ALL_QUERY);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return getTemperaturesFromResultSet(rs);
    }

    /**
     * Creates the list of Temperature objects from the ResultSet
     * @param resultSet Result Set retrieved from Database
     * @return List of Temperature objects
     */
    private List<Temperature> getTemperaturesFromResultSet(ResultSet resultSet){
        List<Temperature> temperatures = new ArrayList<Temperature>();
        try {
            while (resultSet.next()) {
                Temperature temperature = new Temperature(resultSet.getString(DBAttributes.MODULE_ID.getColumnName()),
                        resultSet.getString(DBAttributes.READING.getColumnName()),
                        resultSet.getString(DBAttributes.CREATE_DATE.getColumnName()));
                temperatures.add(temperature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temperatures;
    }

    /**
     *
     * @return List of last 10 temperatures
     */
    public List<Temperature> findRecent() {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(SELECT_RECENT);
            System.out.println("ResultSet looks like this: "+rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return getTemperaturesFromResultSet(rs);
    }
}
