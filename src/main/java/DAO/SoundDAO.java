package DAO;


import java.util.List;

public interface SoundDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param value reading from sound sensor
     */
    public void saveSoundValue(String moduleId, String value);

    /**
     *
     * @param moduleId Unique Id of the module
     * @return List of all readings from sound Sensor
     */
    public List<String> getSoundReadings(String moduleId);
}
