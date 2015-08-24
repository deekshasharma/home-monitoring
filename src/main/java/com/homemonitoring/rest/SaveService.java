package com.homemonitoring.rest;

import com.homemonitoring.dao.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("save")
public class SaveService {

    private TemperatureDAO temperatureDAO = new TemperatureDAOImpl();
    private SoundDAO soundDAO = new SoundDAOImpl();
    private MotionDAO motionDAO = new MotionDAOImpl();

    @GET
    @Path("{moduleId}")
    public Response responseMessage(@PathParam("moduleId") String moduleId, @QueryParam("reading") String reading, @QueryParam("type") String type) {
        if (type.equalsIgnoreCase("temp")){
            saveTemperatureReading(moduleId,reading);
        }else if (type.equalsIgnoreCase("motion")){
            saveMotionReading(moduleId,reading);
        }else {
            saveSoundReading(moduleId,reading);
        }
        String response = type + " saved";
        return Response.status(200).entity(response).build();
    }

    /**
     * @param moduleId
     * @param temperatureReading
     */
    private void saveTemperatureReading(String moduleId, String temperatureReading) {
        temperatureDAO.saveTemperatureReading(moduleId, Integer.parseInt(temperatureReading));
    }

    /**
     * @param moduleId
     * @param soundReading
     */
    private void saveSoundReading(String moduleId, String soundReading) {
        soundDAO.saveSoundValue(moduleId, Integer.parseInt(soundReading));
    }

    /**
     * @param moduleId
     * @param motionReading
     */
    private void saveMotionReading(String moduleId, String motionReading) {
        motionDAO.saveMotionValue(moduleId, Integer.parseInt(motionReading));
    }
}
