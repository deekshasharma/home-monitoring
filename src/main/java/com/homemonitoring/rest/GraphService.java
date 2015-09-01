package com.homemonitoring.rest;

import com.homemonitoring.business.GetSensorData;

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
        return Response.ok().entity(getSensorData.getTemperatureData()).build();
    }

    @GET
    @Path("motion")
    public Response getMotionResponse() throws SQLException {
        return Response.ok().entity(getSensorData.getMotionData()).build();
    }

    @GET
    @Path("sound")
    public Response getSoundResponse(){
        return Response.ok().entity(getSensorData.getSoundData()).build();
    }
}
