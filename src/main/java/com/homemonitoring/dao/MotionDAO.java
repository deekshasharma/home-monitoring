package com.homemonitoring.dao;

import java.util.List;

public interface MotionDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param reading reading from motion sensor
     */
    public void saveMotionValue(String moduleId, int reading);

    /**
     *
     * @param moduleId Unique Id of the module
     * @return List of all readings from Motion Sensor
     */
    public List<Integer> getMotionReadings(String moduleId);
}
