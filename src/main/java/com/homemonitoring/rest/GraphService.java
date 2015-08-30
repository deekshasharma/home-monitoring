package com.homemonitoring.rest;

import com.homemonitoring.dao.DBConnection;
import com.homemonitoring.dao.DBQueries;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("graph")
public class GraphService {

    private DBConnection dbConnection = DBConnection.getInstance();
    private DBQueries dbQueries;

    public GraphService() throws SQLException {
        dbQueries = new DBQueries(dbConnection);
    }

    @GET
    @Path("temperature")
    public Response getTemperatureResponse() throws SQLException {
        String temp = dbQueries.getTemperature();
        return Response.ok().entity(temp).build();
    }

}
