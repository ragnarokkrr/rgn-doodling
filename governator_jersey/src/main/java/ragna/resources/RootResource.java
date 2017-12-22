package ragna.resources;


import io.swagger.annotations.*;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Api("ragna")
@Path("/api/1.0")
@Produces(MediaType.APPLICATION_JSON)
@SwaggerDefinition(
        info = @Info(
                title="Ragna Sample",
                version="1.0.0",
                description = "Example ragna service for Guice, Jersey, NetFlix governator"
        ),
        tags = {
            @Tag(name= "sample", description="inner operations")
        },
        produces =  {MediaType.APPLICATION_JSON},
        consumes =  {MediaType.APPLICATION_JSON}
)
public class RootResource {
    private SampleResource sampleResource;

    @Inject
    public RootResource(@Nonnull SampleResource sampleResource) {
        this.sampleResource = sampleResource;
    }

    @GET
    @ApiOperation(value= "Saying hello", response = String.class)
    public String hello() {
        return String.format("{\"%s\":\"%s\"}", "hello", "world");
    }

    @Path("/sample")
    public SampleResource getSampleResource(){

        return sampleResource;
    }

}
