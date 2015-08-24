package com.homemonitoring.dao;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundDAOImpl implements SoundDAO {

    private Map<String, List<Integer>> moduleIdToSound = new HashMap<String, List<Integer>>();

    public void saveSoundValue(String moduleId, int reading) {
        Preconditions.checkArgument(moduleId != null);
        if (moduleIdToSound.containsKey(moduleId)) {
            List<Integer> readings = moduleIdToSound.get(moduleId);
            readings.add(reading);
        } else {
            List<Integer> readings = new ArrayList<Integer>();
            readings.add(reading);
            moduleIdToSound.put(moduleId, readings);
        }

    }

    public List<Integer> getSoundReadings(String moduleId) {
        return moduleIdToSound.get(moduleId);
    }
}
