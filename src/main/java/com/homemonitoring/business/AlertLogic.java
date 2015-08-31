package com.homemonitoring.business;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.homemonitoring.dao.*;
import com.homemonitoring.model.Motion;
import com.homemonitoring.model.Temperature;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AlertLogic {

    private static final int THRESHOLD_TEMPERATURE = 200;

    private static TemperatureDAO temperatureDAO;
    private static MotionDAO motionDAO;
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
    }




    public String sendAlert() {
        return gson.toJson(temperatureDAO.findRecent());
    }

    /**
     * @return true if Heat Alert should be triggered and false otherwise
     */
    public boolean sendHeatAlert() {
        if (isHeatAboveThreshold() && isNoMotionDetected()) {
            return true;
        }
        return false;
    }

    /**
     * @return true if the last 5 temperature readings exceeds threshold
     */
    private boolean isHeatAboveThreshold() {
        List<Temperature> temperatures = temperatureDAO.findRecent();
        if (CollectionUtils.isEmpty(temperatures)) {
            throw new NullPointerException("No temperature readings are present in DB");
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
    private boolean isNoMotionDetected() {
        List<Motion> motionReadings = motionDAO.findRecent();
        if ((Integer.parseInt(motionReadings.get(0).getReading()) == 0)){
            return true;
        }else
            return false;
    }
}
