package com.homemonitoring.dao;

import com.google.common.base.Preconditions;
import com.homemonitoring.Attributes.DBAttributes;
import com.homemonitoring.model.Motion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotionDAOImpl implements MotionDAO {

    private Statement statement;
    private Connection connection;
    private static final String MOTION_TABLE = "motion";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM " + MOTION_TABLE;
    private static final String SELECT_RECENT = "SELECT * FROM " + MOTION_TABLE + " ORDER BY " + DBAttributes.CREATE_DATE.getColumnName() + " DESC LIMIT 0,1;";
    private DBConnection dbConnection = DBConnection.getInstance();

    public MotionDAOImpl() throws SQLException {
        connection = dbConnection.getConnection();
        statement = connection.createStatement();
    }

    public void insert(String moduleId, String reading) {
        String insertStatement = "INSERT INTO " + MOTION_TABLE + " VALUES(" + "'" + moduleId + "'" + "," + "'" + reading + "'" + ",now())";
        System.out.println("Inserting motion => " + insertStatement);
        try {
            statement.executeUpdate(insertStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return List of all Motion objects
     */
    public List<Motion> findAll() {

        ResultSet rs = null;
        try {
            rs = statement.executeQuery(SELECT_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getMotionFromResultSet(rs);
    }

    /**
     * Creates the list of Motion objects from the ResultSet
     *
     * @param resultSet Result Set retrieved from Database
     * @return List of Motion objects
     */
    private List<Motion> getMotionFromResultSet(ResultSet resultSet) {
        List<Motion> motions = new ArrayList<Motion>();
        try {
            while (resultSet.next()) {
                Motion motion = new Motion(resultSet.getString(DBAttributes.MODULE_ID.getColumnName()),
                        resultSet.getString(DBAttributes.READING.getColumnName()),
                        resultSet.getString(DBAttributes.CREATE_DATE.getColumnName()));
                motions.add(motion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motions;
    }

    /**
     * Find the most recent Motion readings
     * @return List of Motion Objects from result set
     */
    public List<Motion> findRecent() {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(SELECT_RECENT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getMotionFromResultSet(rs);
    }
}
