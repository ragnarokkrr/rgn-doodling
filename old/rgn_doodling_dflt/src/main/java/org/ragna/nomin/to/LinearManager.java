package org.ragna.nomin.to;

/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class LinearManager extends Employee {

    private String characteristics;

    public LinearManager() {
    }

    public LinearManager(Details details, String last, String name) {
        super(details, last, name);
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "LinearManager{" +
                "characteristics='" + characteristics + '\'' +
                '}';
    }
}
