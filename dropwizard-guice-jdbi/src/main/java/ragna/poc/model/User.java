package ragna.poc.model;

/**
 * Created by ragnarokkrr on 12/21/17.
 */
public class User extends IdEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
