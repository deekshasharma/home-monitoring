package com.homemonitoring.rest;

import com.homemonitoring.dao.DBConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("close")
public class CloseConnectionService {

    private static DBConnection dbConnection = DBConnection.getInstance();

    @GET
    public Response close() throws SQLException {
        dbConnection.shutDown();
        return Response.ok().build();
    }
}
