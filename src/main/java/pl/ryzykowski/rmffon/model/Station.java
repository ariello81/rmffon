package pl.ryzykowski.rmffon.model;

import java.util.Objects;

public class Station {

    private String id;
    private String name;

    public Station() {
    }

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
