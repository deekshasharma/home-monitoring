package com.homemonitoring.rest;

import com.homemonitoring.business.GetSensorData;
import com.homemonitoring.dao.DBConnection;
import com.homemonitoring.dao.CreateDBTables;
import com.homemonitoring.dao.TemperatureDAOImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("graph")
public class GraphService {

    GetSensorData getSensorData = new GetSensorData();

    @GET
    @Path("temperature")
    public Response getTemperatureResponse() throws SQLException {
        String temperatureJson = getSensorData.getTemperatureData();
        return Response.ok().entity(temperatureJson).build();
    }

}
