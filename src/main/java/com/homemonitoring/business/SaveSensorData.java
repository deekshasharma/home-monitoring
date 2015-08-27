package com.homemonitoring.business;

import com.homemonitoring.dao.*;

public class SaveSensorData {

    private static TemperatureDAO temperatureDAO = new TemperatureDAOImpl();
    private static SoundDAO soundDAO = new SoundDAOImpl();
    private static MotionDAO motionDAO = new MotionDAOImpl();


    /**
     * @param moduleId
     * @param temperatureReading
     */
    public void saveTemperatureReading(String moduleId, String temperatureReading) {
        temperatureDAO.saveTemperatureReading(moduleId, Integer.parseInt(temperatureReading));
    }

    /**
     * @param moduleId
     * @param soundReading
     */
    public void saveSoundReading(String moduleId, String soundReading) {
        soundDAO.saveSoundValue(moduleId, Integer.parseInt(soundReading));
    }

    /**
     * @param moduleId
     * @param motionReading
     */
    public void saveMotionReading(String moduleId, String motionReading) {
        motionDAO.saveMotionValue(moduleId, Integer.parseInt(motionReading));
    }
}
