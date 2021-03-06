package com.homemonitoring.rest;

import com.homemonitoring.business.CreateDatabase;
import com.homemonitoring.business.SaveSensorData;
import com.homemonitoring.dao.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("save")
public class SaveService {

    private static SaveSensorData saveSensorData = new SaveSensorData();
    private static CreateDatabase createDatabase = new CreateDatabase();


//    @GET
//    @Path("{moduleId}")
//    public Response responseMessage(@PathParam("moduleId") String moduleId, @QueryParam("reading") String reading, @QueryParam("type") String type) throws SQLException {
//        createDatabase.createDbTables();
//        if (type.equalsIgnoreCase("temperature")) {
//            System.out.println("Sending temperature " + reading);
//            saveSensorData.saveTemperatureReading(moduleId, reading);
//        } else if (type.equalsIgnoreCase("motion")) {
//            saveSensorData.saveMotionReading(moduleId, reading);
//        } else {
//            saveSensorData.saveSoundReading(moduleId, reading);
//        }
//        String response = type + " saved";
//        return Response.status(200).entity(response).build();
//    }


    @GET
    @Path("{moduleId}/{reading}/{type}")
    public Response responseMessage(@PathParam("moduleId") String moduleId, @PathParam("reading") String reading, @PathParam("type") String type) throws SQLException {
        createDatabase.createDbTables();
        if (type.equalsIgnoreCase("temperature")) {
            System.out.println("Sending temperature " + reading);
            saveSensorData.saveTemperatureReading(moduleId, reading);
        } else if (type.equalsIgnoreCase("motion")) {
            saveSensorData.saveMotionReading(moduleId, reading);
        } else {
            saveSensorData.saveSoundReading(moduleId, reading);
        }
        String response = type + " saved";
        return Response.status(200).entity(response).build();
    }
}