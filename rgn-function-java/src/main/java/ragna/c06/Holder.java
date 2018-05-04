package ragna.c06;

import java.util.function.Supplier;

public class Holder {
    private Supplier<Heavy> heavySupplier = () -> createAndCacheHeavy();

    public Holder() {
        System.out.println("Holder Created");
    }

    public Heavy getHeavy(){
        return heavySupplier.get();
    }

    private synchronized Heavy createAndCacheHeavy() {
        class HeavyFactory implements Supplier<Heavy> {
            private final Heavy heavyInstance = new Heavy();

            public Heavy get() { return heavyInstance; }
        }

        if (!HeavyFactory.class.isInstance(heavySupplier)) {
            heavySupplier = new HeavyFactory();
        }
        return heavySupplier.get();
    }
}
