package com.homemonitoring.dao;

import com.homemonitoring.model.Temperature;

import java.util.List;

public interface TemperatureDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param reading reading from temperature sensor
     */
    public void insert(String moduleId, String reading);

    /**
     *
     * @return List of all readings from temperature Sensor
     */
    public List<Temperature> findAll();
}
