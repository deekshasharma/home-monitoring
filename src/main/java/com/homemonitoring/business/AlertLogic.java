package com.homemonitoring.business;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.homemonitoring.dao.*;
import com.homemonitoring.model.Motion;
import com.homemonitoring.model.Sound;
import com.homemonitoring.model.Temperature;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AlertLogic {

    private static final int THRESHOLD_TEMPERATURE = 200;
    private static final int THRESHOLD_SOUND = 100;

    private static TemperatureDAO temperatureDAO;
    private static MotionDAO motionDAO;
    private static SoundDAO soundDAO;
    private static Gson gson = new Gson();

    static {
        try {
            temperatureDAO = new TemperatureDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            motionDAO = new MotionDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            soundDAO = new SoundDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return true if Sound Alert should be triggered and false otherwise
     */
    public boolean sendSoundAlert() {
        List<Sound> sounds = soundDAO.findRecent();
        String moduleId = sounds.get(0).getModuleId();
        if (isSoundAboveThreshold(sounds) && isNoMotionDetected(moduleId)) {
            return true;
        }
        return false;
    }

    /**
     * @return true if the last 5 sound readings exceeds threshold
     */
    private boolean isSoundAboveThreshold(List<Sound> sounds) {
        if (CollectionUtils.isEmpty(sounds)) {
            throw new NullPointerException("No temperature readings pulled from DataBase");
        } else {
            for (Sound sound : sounds) {
                if ((Integer.parseInt(sound.getReading())) <= THRESHOLD_SOUND) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * @return true if Heat Alert should be triggered and false otherwise
     */
    public boolean sendHeatAlert() {
        List<Temperature> temperatures = temperatureDAO.findRecent();
        String moduleId = temperatures.get(0).getModuleId();
        if (isHeatAboveThreshold(temperatures) && isNoMotionDetected(moduleId)) {
            return true;
        }
        return false;
    }

    /**
     * @return true if the last 5 temperature readings exceeds threshold
     */
    private boolean isHeatAboveThreshold(List<Temperature> temperatures) {
        if (CollectionUtils.isEmpty(temperatures)) {
            throw new NullPointerException("No temperature readings pulled from DataBase");
        } else {
            for (Temperature temperature : temperatures) {
                if ((Integer.parseInt(temperature.getReading())) <= THRESHOLD_TEMPERATURE) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * @return true if no motion is detected at home
     */
    private boolean isNoMotionDetected(String moduleId) {
        List<Motion> motionReadings = motionDAO.findRecent(moduleId);
        if (CollectionUtils.isEmpty(motionReadings)) {
            throw new NullPointerException("Motion Readings for " + moduleId + " not found in database");
        }
        if ((Integer.parseInt(motionReadings.get(0).getReading()) == 0)) {
            return true;
        } else
            return false;
    }
}
