package org.ragna.nomin.to;

/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class Education {

    private String description;
    private String name;

    public Education(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Education{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
