package ragna;


import com.google.inject.Injector;
import com.netflix.governator.InjectorBuilder;
import com.netflix.governator.guice.servlet.GovernatorServletContextListener;
import netflix.adminresources.resources.KaryonWebAdminModule;
import ragna.modules.RagnaServiceModule;
import ragna.modules.SwaggerModule;

public class StartServer extends GovernatorServletContextListener{


    @Override
    protected Injector createInjector() throws Exception {
        return InjectorBuilder.fromModules(
                new RagnaServiceModule(),
                new SwaggerModule(),
                new KaryonWebAdminModule()
        ).createInjector();
    }
}
