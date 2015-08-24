package com.homemonitoring.dao;

import java.util.List;

public interface TemperatureDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param reading reading from temperature sensor
     */
    public void saveTemperatureReading(String moduleId, int reading);

    /**
     *
     * @param moduleId Unique Id of the module
     * @return List of all readings from temperature Sensor
     */
    public List<Integer> getTemperatureReadings(String moduleId);
}
