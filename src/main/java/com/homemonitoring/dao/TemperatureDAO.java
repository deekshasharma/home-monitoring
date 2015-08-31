package com.homemonitoring.dao;

import com.homemonitoring.model.Temperature;

import java.util.List;

public interface TemperatureDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param reading reading from temperature sensor
     */
     void insert(String moduleId, String reading);

    /**
     *Get all the temperature readings
     * @return List of all readings from temperature Sensor
     */
     List<Temperature> findAll();

    /**
     * Get the most recent temperature readings
     * @return
     */
    List<Temperature> findRecent();
}
