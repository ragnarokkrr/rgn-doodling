package ragna.resources;

import com.fasterxml.jackson.core.JsonParseException;
import ragna.exceptions.ApiException;
import ragna.exceptions.BadRequestException;
import ragna.model.ApiResponse;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SampleExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof WebApplicationException) {
            WebApplicationException e = (WebApplicationException) exception;
            return  Response
                    .status(e.getResponse().getStatus())
                    .entity (new ApiResponse (e.getResponse ().getStatus (),
                            exception.getMessage ())).build ();
        } else if (exception instanceof JsonParseException) {
            return Response.status (400)
                    .entity (new ApiResponse(400, "bad input")).build ();
        } else if (exception instanceof BadRequestException) {
            return Response.status (Response.Status.BAD_REQUEST)
                    .entity (new ApiResponse (ApiResponse.ERROR, exception.getMessage ())).build ();
        } else if (exception instanceof ApiException) {
            return Response
                    .status (Response.Status.BAD_REQUEST)
                    .entity (new ApiResponse (ApiResponse.ERROR, exception.getMessage ())).build ();
        }



        return Response.status(500)
                .entity(new ApiResponse(500, "something bad happened")).build ();
    }
}
