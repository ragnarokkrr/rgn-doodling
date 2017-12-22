package ragna.modules;

import com.google.common.collect.Maps;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import ragna.filters.ApiOriginCorsFilter;
import ragna.resources.RootResource;

import java.util.Map;

public class RagnaServiceModule extends JerseyServletModule{
    @Override
    protected void configureServlets() {
        bind(RootResource.class).asEagerSingleton();

        //make  servlet context available for the swaggermodule
        bind(ServletConfigProvider.class).toInstance(new ServletConfigProvider(getServletContext()));
        bind(GuiceContainer.class).asEagerSingleton();

        filter("/*").through(ApiOriginCorsFilter.class);
        filter("/*").through(GuiceContainer.class, createServletParams());
    }

    private Map<String, String> createServletParams() {
        Map<String, String> servletParams = Maps.newHashMapWithExpectedSize(2);

        servletParams.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "/(docs|js)/.*");
        return servletParams;
    }
}
