package ragna;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ragna.component.ComponentI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloResource {
    private final String message;
    private final ComponentI componentI;

    @Inject
    public HelloResource(@Named("message") String message, ComponentI componentI){
        this.message = message;
        this.componentI = componentI;
    }

    @GET
    public String hello(){
        return message;
    }

    @GET
    @Path("from-component")
    public String helloComponent(){
        return componentI.test();
    }


}
