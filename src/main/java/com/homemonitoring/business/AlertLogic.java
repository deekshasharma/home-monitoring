package com.homemonitoring.business;

import com.google.common.base.Preconditions;
import com.homemonitoring.dao.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class AlertLogic {

    private static final int THRESHOLD_TEMPERATURE = 200;

    private TemperatureDAO temperatureDAO = new TemperatureDAOImpl();
    private MotionDAO motionDAO = new MotionDAOImpl();
    private SoundDAO soundDAO = new SoundDAOImpl();

    /**
     * @param moduleId
     * @return true if Heat Alert should be triggered and false otherwise
     */
    public boolean sendHeatAlert(String moduleId) {
        if (isHeatAboveThreshold(moduleId) && isNoMotionDetected(moduleId)) {
            return true;
        }
        return false;
    }

    /**
     * @param moduleId
     * @return true if the last 5 temperature readings exceeds threshold
     */
    private boolean isHeatAboveThreshold(String moduleId) {
        List<Integer> temperatureReadings = temperatureDAO.getTemperatureReadings(moduleId);
        if (CollectionUtils.isEmpty(temperatureReadings)) {
            throw new NullPointerException("temperature readings must not be null or empty");
        }else {
            int counter = 1;
            int size = temperatureReadings.size();
            while (counter <= 5) {
                int temperature = temperatureReadings.get(size);
                if (temperature < THRESHOLD_TEMPERATURE) {
                    return false;
                }
                size--;
                counter++;
            }
        }
        return true;
    }

    /**
     * @param moduleId
     * @return true if no motion is detected at home
     */
    private boolean isNoMotionDetected(String moduleId) {
        List<Integer> motionReadings = motionDAO.getMotionReadings(moduleId);
        if (motionReadings.get(motionReadings.size() - 1) == 0) {
            return true;
        }
        return false;
    }
}
