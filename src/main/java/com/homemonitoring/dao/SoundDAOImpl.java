package com.homemonitoring.dao;

import com.homemonitoring.Attributes.DBAttributes;
import com.homemonitoring.model.Motion;
import com.homemonitoring.model.Sound;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SoundDAOImpl implements SoundDAO {


    private Statement statement;
    private Connection connection;
    private static final String SOUND_TABLE = "sound";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM " + SOUND_TABLE;
    private static final String SELECT_RECENT = "SELECT * FROM " + SOUND_TABLE + " ORDER BY " + DBAttributes.CREATE_DATE.getColumnName() + " DESC LIMIT 0,1;";
    private DBConnection dbConnection = DBConnection.getInstance();

    public SoundDAOImpl() throws SQLException {
        connection = dbConnection.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public void insert(String moduleId, int reading) {
        String insertStatement = "INSERT INTO " + SOUND_TABLE + " VALUES(" + "'" + moduleId + "'" + "," + "'" + reading + "'" + ",now())";
        System.out.println("Inserting sound => " + insertStatement);
        try {
            statement.executeUpdate(insertStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Sound> findAll() {

        ResultSet rs = null;
        try {
            rs = statement.executeQuery(SELECT_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getSoundFromResultSet(rs);
    }

    /**
     * Creates the list of Sound objects from the ResultSet
     *
     * @param resultSet Result Set retrieved from Database
     * @return List of Sound objects
     */
    private List<Sound> getSoundFromResultSet(ResultSet resultSet) {
        List<Sound> sounds = new ArrayList<Sound>();
        try {
            while (resultSet.next()) {
                Sound sound = new Sound(resultSet.getString(DBAttributes.MODULE_ID.getColumnName()),
                        resultSet.getString(DBAttributes.READING.getColumnName()),
                        resultSet.getString(DBAttributes.CREATE_DATE.getColumnName()));
                sounds.add(sound);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sounds;
    }

    @Override
    public List<Sound> findRecent() {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(SELECT_RECENT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getSoundFromResultSet(rs);
    }
}
