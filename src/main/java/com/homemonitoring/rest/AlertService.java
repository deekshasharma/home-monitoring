package com.homemonitoring.rest;

import com.homemonitoring.business.AlertLogic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("alert")
public class AlertService {

    private static AlertLogic alertLogic = new AlertLogic();

    @GET
    @Path("temperature")
    public Response alertTemperature() {
        String response;
        if (alertLogic.sendHeatAlert()) {
            response = "You forgot to switch off some electronic appliance at home!";
        } else {
            response = "Everything is okay at Home ";
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("sound")
    public Response alertSound() {
        String response;
        if (alertLogic.sendSoundAlert()) {
            response = "May be your faucet is running";
        } else {
            response = "Sound is okay at Home ";
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
