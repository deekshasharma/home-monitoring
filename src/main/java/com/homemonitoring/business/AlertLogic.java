package com.homemonitoring.business;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.homemonitoring.dao.*;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AlertLogic {

    private static final int THRESHOLD_TEMPERATURE = 200;

    private static TemperatureDAO temperatureDAO;
    private static Gson gson = new Gson();

    static {
        try {
            temperatureDAO = new TemperatureDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String sendAlert(){
        return gson.toJson(temperatureDAO.findRecent());
    }
//    private static MotionDAO motionDAO = new MotionDAOImpl();
//    private static SoundDAO soundDAO = new SoundDAOImpl();

    /**
     * @param moduleId
     * @return true if Heat Alert should be triggered and false otherwise
     */
//    public boolean sendHeatAlert(String moduleId) {
//        if (isHeatAboveThreshold(moduleId) && isNoMotionDetected(moduleId)) {
//            return true;
//        }
//        return false;
//    }

    /**
     * @param moduleId
     * @return true if the last 5 temperature readings exceeds threshold
     */
//    private boolean isHeatAboveThreshold(String moduleId) {
//        List<Integer> temperatureReadings = temperatureDAO.getTemperatureReadings(moduleId);
//        if (CollectionUtils.isEmpty(temperatureReadings)) {
//            throw new NullPointerException("temperature readings must not be null or empty");
//        }else {
//            int counter = 1;
//            int lastIndex = temperatureReadings.size() - 1;
//            while (counter <= 5) {
//                int temperature = temperatureReadings.get(lastIndex);
//                if (temperature < THRESHOLD_TEMPERATURE) {
//                    return false;
//                }
//                lastIndex--;
//                counter++;
//            }
//        }
//        return true;
//    }
//
    /**
     * @param moduleId
     * @return true if no motion is detected at home
     */
//    private boolean isNoMotionDetected(String moduleId) {
//        Preconditions.checkArgument(moduleId != null);
//        List<Integer> motionReadings = motionDAO.getMotionReadings(moduleId);
//        if (motionReadings.get(motionReadings.size() - 1) == 0) {
//            return true;
//        }
//        return false;
//    }
}
