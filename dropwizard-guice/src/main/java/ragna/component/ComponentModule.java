package ragna.component;

import com.google.inject.AbstractModule;

public class ComponentModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(ComponentI.class).to(Component.class);
    }
}
