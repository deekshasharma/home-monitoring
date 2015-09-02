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
        boolean response;
        if (alertLogic.sendHeatAlert()) {
            response = true;
        } else {
            response = false;
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("sound")
    public Response alertSound() {
        boolean response;
        if (alertLogic.sendSoundAlert()) {
            response = true;
        } else {
            response = false;
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
