package pt.rafael.pwc.calculateroute.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Country {

    @JsonProperty(value = "cca3")
    private String name;

    @JsonProperty(value = "borders")
    private List<String> borders;

    public String getName() {
        return name;
    }

    public List<String> getBorders() {
        return borders;
    }

    @Override
    public String toString() {
        return name;
    }
}
