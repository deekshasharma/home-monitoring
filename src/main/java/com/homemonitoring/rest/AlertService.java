package com.homemonitoring.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("alert")
public class AlertService {

    private static final String ALERT_TEMPERATURE = "temperature";
    private static final String ALERT_SOUND = "sound";
    private static final int THRESHOLD_TEMP = 200;

    @GET
    @Path("{alertType}")
    public Response sendAlert(@PathParam("alertType") String alertType){
        String response = null;
        if (alertType.equals(ALERT_TEMPERATURE)){

            response = "Polling temperature";
        }else {
            response = "Polling "+ alertType;
        }
            return Response.status(Response.Status.OK).entity(response).build();
    }

}
