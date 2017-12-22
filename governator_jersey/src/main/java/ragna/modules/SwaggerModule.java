package ragna.modules;

import com.google.inject.AbstractModule;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.annotation.PostConstruct;

public class SwaggerModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(ApiListingResource.class).asEagerSingleton();
        bind(SwaggerSerializers.class).asEagerSingleton();
    }

    @PostConstruct
    public void init(){
        BeanConfig swaggerBeanConfig = new BeanConfig();
        swaggerBeanConfig.setHost("localhost:8081");
        swaggerBeanConfig.setSchemes(new String[]{"http"});
        swaggerBeanConfig.setScan();
    }
}
