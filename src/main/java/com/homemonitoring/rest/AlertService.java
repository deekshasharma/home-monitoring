package com.homemonitoring.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("alert")
public class AlertService {

    private static final String ALERT_TEMPERATURE = "temperature";

    @GET
    @Path("{moduleId}/{alertType}")
    public Response sendAlert(@PathParam("moduleId") String moduleId,@PathParam("alertType") String alertType){
        String response = null;
        if (alertType.equalsIgnoreCase(ALERT_TEMPERATURE)){
//            if (checkHeatAlert(moduleId))
            {
                response = "You forgot to switch off some electronic appliance at home!";
            }
        }else {
            response = "Everything is okay at Home ";
        }
            return Response.status(Response.Status.OK).entity(response).build();
    }

    /**
     *
     * @param moduleId
     * @return true if Heat alert should be sent
     */
//    private boolean checkHeatAlert(String moduleId){
//        AlertLogic alertLogic = new AlertLogic();
//        if (alertLogic.sendHeatAlert(moduleId)){
//            return true;
//        }else {
//            return false;
//        }
//    }

}
