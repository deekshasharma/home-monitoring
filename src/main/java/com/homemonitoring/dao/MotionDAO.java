package com.homemonitoring.dao;

import com.homemonitoring.model.Motion;

import java.util.List;

public interface MotionDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param reading reading from motion sensor
     */
    void insert(String moduleId, String reading);

    /**
     *Get all the motion readings
     * @return List of all readings from temperature Sensor
     */
    List<Motion> findAll();

    /**
     * Get the most recent motion readings
     * @return
     */
    List<Motion> findRecent();

}
