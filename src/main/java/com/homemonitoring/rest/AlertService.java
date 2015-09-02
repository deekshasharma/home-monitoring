package com.homemonitoring.rest;

import com.homemonitoring.business.AlertLogic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("alert")
public class AlertService {

    private static final String ALERT_TEMPERATURE = "temperature";
    private static AlertLogic alertLogic = new AlertLogic();

    @GET
    @Path("temperature")
    public Response sendAlert() {
        String response;
            if (checkHeatAlert()) {
                response = "You forgot to switch off some electronic appliance at home!";
            }
        else {
            response = "Everything is okay at Home ";
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }


    /**
     * Should heat alert be issued
     *
     * @return true if Heat alert should be sent
     */
    private boolean checkHeatAlert() {
        if (alertLogic.sendHeatAlert()) {
            return true;
        } else {
            return false;
        }
    }

}
