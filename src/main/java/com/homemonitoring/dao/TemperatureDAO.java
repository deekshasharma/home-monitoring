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
     *
     * @return List of all readings from temperature Sensor
     */
     List<Temperature> findAll();

    List<Temperature> findRecent();
}
