package pl.ryzykowski.rmffon.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class OpenFm {

    @JsonAlias("channels")
    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
