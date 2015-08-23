import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("data")
public class RestEndPoint {

    private static final int THRESHOLD_TEMP = 250;

    @GET
    @Path("{moduleId}")
    public Response responseMessage(@PathParam("moduleId") String moduleId, @QueryParam("value") String value,@QueryParam("type") String type){
        String response = "BreadBoardId = "+moduleId+ " & Temperature = " + value+ " & Type = "+type;
        return Response.status(200).entity(response).build();
    }
}
