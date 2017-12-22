package ragna.poc.service;

/**
 * Created by ragnarokkrr on 12/21/17.
 */
public class RandomNameGenerator {

    public String generateName() {
        // implementation doesn't matter
        return "test" + Math.round(1000 * Math.random());
    }
}