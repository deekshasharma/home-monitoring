package com.homemonitoring.business;

import com.google.gson.Gson;
import com.homemonitoring.dao.TemperatureDAOImpl;
import com.homemonitoring.model.Temperature;

import java.sql.SQLException;
import java.util.List;

public class GetSensorData {


    private static TemperatureDAOImpl temperatureDAO;
    private Gson gson = new Gson();

    static {
        try {
            temperatureDAO = new TemperatureDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return JSON of temperature
     */
    public String getTemperatureData() {
        List<Temperature> temperatures = temperatureDAO.findAll();
        return (gson.toJson(temperatures));
    }
}
