package com.homemonitoring.dao;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotionDAOImpl implements MotionDAO {

    private Map<String, List<Integer>> moduleIdToMotion = new HashMap<String, List<Integer>>();

    /**
     * @param moduleId Unique Id of the module
     * @param reading  reading from sound sensor
     */
    public void saveMotionValue(String moduleId, int reading) {
        Preconditions.checkArgument(moduleId != null);
        if (moduleIdToMotion.containsKey(moduleId)) {
            List<Integer> readings = moduleIdToMotion.get(moduleId);
            readings.add(reading);
        } else {
            List<Integer> readings = new ArrayList<Integer>();
            readings.add(reading);
            moduleIdToMotion.put(moduleId, readings);
        }
    }

    /**
     * @param moduleId Unique Id of the module
     * @return List of all motion readings from the sensor
     */
    public List<Integer> getMotionReadings(String moduleId) {
        return moduleIdToMotion.get(moduleId);
    }
}
