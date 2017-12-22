package ragna.resources;


import io.swagger.annotations.*;
import ragna.model.Sample;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Api(tags="sample", description = "Sample operations")
@Produces({MediaType.APPLICATION_JSON})
public class SampleResource {

    @GET
    @Path("/{sampleName}")
    @ApiOperation(value = "Sample api usage",
        response = Sample.class)
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid Input")
    })
    public Response getSample(
            @ApiParam(value="a sample name", allowableValues = "non null strings", required = true)
            @PathParam("sampleName")
            String sampleName){
        Sample sample = new Sample(sampleName);
        return Response.ok().entity(sample).build();
    }

}
