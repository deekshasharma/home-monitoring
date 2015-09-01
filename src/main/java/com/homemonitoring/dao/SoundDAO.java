package com.homemonitoring.dao;


import com.homemonitoring.model.Sound;

import java.util.List;

public interface SoundDAO {

    /**
     *
     * @param moduleId Unique Id of the module
     * @param value reading from sound sensor
     */
     void insert(String moduleId, int value);

    /**
     *
     * @return List of all readings from sound Sensor
     */
     List<Sound> findAll();

    List<Sound> findRecent();



}
