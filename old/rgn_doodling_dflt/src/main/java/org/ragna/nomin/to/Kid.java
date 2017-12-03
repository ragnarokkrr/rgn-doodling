package org.ragna.nomin.to;

/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class Kid {

    private String kidName;

    public Kid() {
    }

    public Kid(String kidName) {
        this.kidName = kidName;
    }

    public String getKidName() {
        return kidName;
    }

    public void setKidName(String kidName) {
        this.kidName = kidName;
    }

    @Override
    public String toString() {
        return "Kid{" +
                "kidName='" + kidName + '\'' +
                '}';
    }
}
