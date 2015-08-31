package com.homemonitoring.business;

import com.homemonitoring.dao.*;

import java.sql.SQLException;

public class SaveSensorData {

    private static TemperatureDAO temperatureDAO;

    static {
        try {
            temperatureDAO = new TemperatureDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MotionDAO motionDAO;

    static {
        try {
            motionDAO = new MotionDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SoundDAO soundDAO = new SoundDAOImpl();

    /**
     * @param moduleId
     * @param temperatureReading
     */
    public void saveTemperatureReading(String moduleId, String temperatureReading) throws SQLException {
        temperatureDAO.insert(moduleId, temperatureReading);
    }

//    /**
//     * @param moduleId
//     * @param soundReading
//     */
//    public void saveSoundReading(String moduleId, String soundReading) {
//        soundDAO.saveSoundValue(moduleId, Integer.parseInt(soundReading));
//    }

    /**
     * @param moduleId
     * @param motionReading
     */
    public void saveMotionReading(String moduleId, String motionReading) {
        motionDAO.insert(moduleId, motionReading);
    }

}
