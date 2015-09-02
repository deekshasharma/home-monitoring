package com.homemonitoring.business;

import com.google.gson.Gson;
import com.homemonitoring.dao.*;
import com.homemonitoring.model.Sound;
import com.homemonitoring.model.Temperature;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GetSensorData {


    private static TemperatureDAOImpl temperatureDAO;
    private Gson gson = new Gson();
    private static MotionDAO motionDAO;
    private static SoundDAO soundDAO;

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
     * @return JSON of all temperatures for Graph
     */
    public String getTemperatureData() {
        List<Temperature> temperatures = temperatureDAO.findRecent();
        Collections.reverse(temperatures);
        return (gson.toJson(temperatures));
    }

    /**
     * @return JSON of all motions for Graph
     */
    public String getMotionData() {
        return (gson.toJson(motionDAO.findAll()));
    }

    /**
     *
     * @return JSON of all sounds for graph
     */
    public String getSoundData() {
        List<Sound> sounds = soundDAO.findRecent();
        Collections.reverse(sounds);
        return (gson.toJson(sounds));
    }
}
