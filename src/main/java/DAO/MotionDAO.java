package DAO;

import java.util.List;

public interface MotionDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param value reading from motion sensor
     */
    public void saveMotionValue(String moduleId, String value);

    /**
     *
     * @param moduleId Unique Id of the module
     * @return List of all readings from Motion Sensor
     */
    public List<String> getMotionReadings(String moduleId);
}
