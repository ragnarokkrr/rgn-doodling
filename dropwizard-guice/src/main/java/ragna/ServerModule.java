package ragna;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import ragna.component.ComponentModule;

import javax.inject.Named;

public class ServerModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.install(new ComponentModule());
    }

    @Provides
    @Named("message")
    public String provideMessage(ServerConfiguration serverConfiguration){
        return serverConfiguration.getMessage();
    }

}
