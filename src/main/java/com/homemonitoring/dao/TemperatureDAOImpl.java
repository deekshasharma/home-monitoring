package com.homemonitoring.dao;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemperatureDAOImpl implements TemperatureDAO {

    private Map<String, List<Integer>> moduleIdToTemp = new HashMap<String, List<Integer>>();

    /**
     *
     * @param moduleId Unique Id of the module
     * @param reading reading from temperature sensor
     */
    public void saveTemperatureReading(String moduleId, int reading) {
        Preconditions.checkArgument(moduleId != null);
        if (moduleIdToTemp.containsKey(moduleId)) {
            List<Integer> readings = moduleIdToTemp.get(moduleId);
            readings.add(reading);
            moduleIdToTemp.put(moduleId, readings);
        } else {
            List<Integer> readings = new ArrayList<Integer>();
            readings.add(reading);
            moduleIdToTemp.put(moduleId, readings);
        }
        System.out.println("TempMap => "+moduleIdToTemp);
    }

    /**
     *
     * @param moduleId Unique Id of the module
     * @return List of all readings for a given moduleId
     */
    public List<Integer> getTemperatureReadings(String moduleId) {
        return moduleIdToTemp.get(moduleId);
    }
}
