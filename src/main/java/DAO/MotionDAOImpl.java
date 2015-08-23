package DAO;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotionDAOImpl implements MotionDAO {

    private Map<String, List<Integer>> moduleIdToMotion = new HashMap<String, List<Integer>>();

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

    public List<Integer> getMotionReadings(String moduleId) {
        return moduleIdToMotion.get(moduleId);
    }
}
